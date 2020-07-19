package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.PositionDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.dao.SecurityOrderDao;
import ca.jrvs.apps.trading.dto.MarketOrderDto;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Position;
import ca.jrvs.apps.trading.model.domain.Quote;
import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class OrderService {
  private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
  private AccountDao accountDao;
  private SecurityOrderDao securityOrderDao;
  private QuoteDao quoteDao;
  private PositionDao positionDao;

  @Autowired
  public OrderService(AccountDao accountDao, SecurityOrderDao securityOrderDao, QuoteDao quoteDao,
      PositionDao positionDao) {
    this.accountDao = accountDao;
    this.securityOrderDao = securityOrderDao;
    this.quoteDao = quoteDao;
    this.positionDao = positionDao;
  }


  public SecurityOrder executeMarketOrder(MarketOrderDto orderDto) {
    if(orderDto.getTicker() == null || orderDto.getSize() == 0 || orderDto.getAccountId() == null) {
      throw new IllegalArgumentException("the ticker is empty and the size is zero");
    }
    Account account;
    try{
      account = accountDao.findById(orderDto.getAccountId()).get();
    }catch (DataAccessException e) {
      throw new DataRetrievalFailureException("can not get the account from the accountDAO",e);
    }

    SecurityOrder securityOrder = new SecurityOrder();
    securityOrder.setAccount_id(orderDto.getAccountId());
    securityOrder.setTicker(orderDto.getTicker());
    if(orderDto.getSize() > 0) {
      handleBuyMarketOrder(orderDto,securityOrder,account);
    }else if(orderDto.getSize() < 0) {
      handleSellMarketOrder(orderDto,securityOrder,account);
    }
    return securityOrder;
  }


  /**
   * handle the buy operation for the specific account.
   * @param marketOrderDto the order that we pass to verify.
   * @param securityOrder the order that has been verified.
   * @param account the account that is associated with this operation.
   */
  public void handleBuyMarketOrder(MarketOrderDto marketOrderDto,
      SecurityOrder securityOrder,Account account) {
    Quote quote = quoteDao.findById(marketOrderDto.getTicker()).get();
    Double price = quote.getAskPrice();
    Double remaining = account.getAmount() - marketOrderDto.getSize()*price;
    if(remaining >= 0) {
      account.setAmount(remaining);
    }else {
      throw new IllegalArgumentException("can not proceed the transaction due to no enough remaining amount");
    }
    securityOrder.setPrice(quote.getAskPrice());
    securityOrder.setStatus("FILLED");
    securityOrder.setSize(marketOrderDto.getSize());
    accountDao.save(account);
    securityOrderDao.save(securityOrder);

  }


  /**
   * handle the sell operation and check is the current size is exceed the position for this order or not.
   *
   * @param marketOrderDto the order we proceed to check.
   * @param securityOrder the order we have verified.
   * @param account the account that is associated with this operation.
   * @throws IllegalArgumentException if the size exceeds the position.
   * @throws DataAccessException if we can not get the quote.
   */
  public void handleSellMarketOrder(MarketOrderDto marketOrderDto,
      SecurityOrder securityOrder, Account account) {
    Quote quote;
    try{
       quote = quoteDao.findById(marketOrderDto.getTicker()).get();
    }catch (DataAccessException e) {
      throw new DataRetrievalFailureException("can not get this quote");
    }
    Double price = quote.getBidPrice();
    Position position = positionDao.findByIdAndTicker(marketOrderDto.getAccountId(),marketOrderDto.getTicker()).get();
    if(position.getPosition() < -1* marketOrderDto.getSize()) {
      throw new IllegalArgumentException("the size exceed the position");
    }

    Double totalAmount =  price * marketOrderDto.getSize();
    account.setAmount(account.getAmount() - totalAmount);
    securityOrder.setSize(marketOrderDto.getSize());
    securityOrder.setStatus("FILLED");
    securityOrder.setPrice(price);
    accountDao.save(account);
    securityOrderDao.save(securityOrder);
  }
}
