package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.PositionDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.dao.TraderDao;
import ca.jrvs.apps.trading.model.PortfolioView;
import ca.jrvs.apps.trading.model.TraderAccountView;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Position;
import ca.jrvs.apps.trading.model.domain.Trader;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DashboardService {
  private TraderDao traderDao;
  private PositionDao positionDao;
  private AccountDao accountDao;
  private QuoteDao quoteDao;

  @Autowired
  public DashboardService(TraderDao traderDao, PositionDao positionDao,
      AccountDao accountDao, QuoteDao quoteDao) {
    this.traderDao = traderDao;
    this.positionDao = positionDao;
    this.accountDao = accountDao;
    this.quoteDao = quoteDao;
  }


  public TraderAccountView getTraderAccount(Integer traderId) {
    if(traderId == null) {
      throw new IllegalArgumentException("this traderId is null we can not");
    }
    Account account = accountDao.findById(traderId).get();
    Trader trader = traderDao.findById(traderId).get();
    TraderAccountView traderAccountView = new TraderAccountView(account,trader);
    return traderAccountView;
  }

  public PortfolioView getProfileViewByTraderView(Integer traderId) {
    if(traderId == null) {
      throw new IllegalArgumentException("this traderId is null we can not");
    }
    Account account = accountDao.findById(traderId).get();
    List<Position> positions = (List<Position>) positionDao.findById(traderId);
    PortfolioView portfolioView = new PortfolioView(account,positions);
    return portfolioView;
  }

  private Account findAccountByTraderId(Integer traderId) {
    return accountDao.findById(traderId).orElseThrow(() -> new IllegalArgumentException("Invalid traderId"));
  }


}
