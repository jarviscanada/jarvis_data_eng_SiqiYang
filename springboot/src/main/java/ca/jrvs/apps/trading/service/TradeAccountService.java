package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.PositionDao;
import ca.jrvs.apps.trading.dao.SecurityOrderDao;
import ca.jrvs.apps.trading.dao.TraderDao;
import ca.jrvs.apps.trading.model.TraderAccountView;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Position;
import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import ca.jrvs.apps.trading.model.domain.Trader;
import io.swagger.models.auth.In;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TradeAccountService {
  private TraderDao traderDao;
  private SecurityOrderDao securityOrderDao;
  private PositionDao positionDao;
  private AccountDao accountDao;


  @Autowired
  public TradeAccountService(TraderDao traderDao,SecurityOrderDao securityOrderDao,PositionDao positionDao,
      AccountDao accountDao) {
    this.traderDao = traderDao;
    this.accountDao = accountDao;
    this.positionDao = positionDao;
    this.securityOrderDao = securityOrderDao;
  }


  /**
   * create a new trader and a new account
   * also verify the trader's each fields can not be null.
   * @param trader the trader we want to add.
   * @return the new traderAccount view.
   */
  public TraderAccountView createTraderAndAccount(Trader trader) {
    if(trader.getEmail() == null || trader.getCountry() == null ||
        trader.getDob() == null || trader.getLast_name() == null || trader.getFirst_name() == null
        || trader.getId() != null) {
      throw new IllegalArgumentException("this trader entity has some invalid fields");
    }
    Trader traderNew = traderDao.save(trader);
    Account account = new Account();
    account.setAmount(0d);
    account.setTrader_id(trader.getId());
    Account accountNew = accountDao.save(account);
    return new TraderAccountView(accountNew,traderNew);
  }

  /**
   *delete the trader and hes relevent entries in the tables.
   * @param traderId the trader identifier.
   */
  public void deleteTraderById(Integer traderId) {
    if(!traderDao.existsById(traderId) || traderId == null) {
      throw new IllegalArgumentException("can not find the trader id with a trader");
    }
    Optional<Account> account = accountDao.findById(traderId);

    if(account.get().getAmount() != 0) {
      throw new IllegalArgumentException("it's balance is not zero");
    }

    List<Position> positions = (List<Position>) positionDao.findById(account.get().getId());
    positions.forEach(element -> {
      if(element.getPosition() != 0) {
        throw new IllegalArgumentException("it still has open position for this");
      }
    });
    List<SecurityOrder> securityOrders = (List<SecurityOrder>) securityOrderDao.findAll();
    securityOrders.forEach(element -> {
      if(element.getAccount_id() == account.get().getId()) {
        securityOrderDao.delete(element);
      }
    });
    accountDao.delete(account.get());
    traderDao.deleteById(traderId);
  }

  /**
   * deposit the fund into the account, also use the trader id as the account id.
   * One trader can only have one account.
   * @param traderId the trader's identifier.
   * @param fund the fund needs to be applied.
   * @return the account.
   */
  public Account deposit(Integer traderId, Double fund) {
    if(!traderDao.existsById(traderId) || traderId == null) {
      throw new IllegalArgumentException("can not find the trader id with a trader");
    }
    if(fund < 0) {
      throw new IllegalArgumentException("fund should be greater or equal to zero");
    }
    Account account = accountDao.findById(traderId).get();
    account.setAmount(account.getAmount()+fund);
    return accountDao.save(account);
  }


  /**
   * withdraw the value from the account. use the trader id to verify the account id.
   * because one trader can only have one account.
   * @param traderId the trader that want to withdraw from the balance.
   * @param fund the fund that will be applied.
   * @return the account.
   */
  public Account withdraw(Integer traderId, Double fund) {
    if(!traderDao.existsById(traderId) || traderId == null) {
      throw new IllegalArgumentException("can not find the trader id with a trader");
    }
    Account account = accountDao.findById(traderId).get();

    if(fund < 0 || fund > account.getAmount()) {
      throw new IllegalArgumentException("fund should be greater or equal to zero "
          + "also it can not greater than current amount");
    }
    account.setAmount(account.getAmount()-fund);
    return accountDao.save(account);
  }


}
