package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Quote;
import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import ca.jrvs.apps.trading.model.domain.Trader;
import java.util.Date;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class SecurityOrderDaoTest {
  @Autowired
  private SecurityOrderDao securityOrderDao;
  private SecurityOrder securityOrder;
  @Autowired
  private AccountDao accountDao;
  private Account account;
  @Autowired
  private QuoteDao quoteDao;
  private Quote quote;
  @Autowired
  private TraderDao traderDao;
  private Trader trader;

  @Before
  public void setUp(){
    quote = new Quote();
    quote.setId(String.valueOf("AAPL"));
    quote.setAskPrice(10d);
    quote.setAskSize(10);
    quote.setBidPrice(10.2d);
    quote.setBidSize(10);
    quote.setLastPrice(10.1d);
    quoteDao.save(quote);

    trader = new Trader();
    trader.setCountry("china");
    trader.setDob(new Date(123,10,2));
    trader.setEmail("thisisfortest@gmail.com");
    trader.setFirst_name("this");
    trader.setLast_name("test");
    traderDao.save(trader);

    account = new Account();
    account.setAmount(300d);
    account.setTrader_id(trader.getId());
    accountDao.save(account);

    securityOrder = new SecurityOrder();
    securityOrder.setNotes("asdasd");
    securityOrder.setPrice(123);
    securityOrder.setSize(123);
    securityOrder.setStatus("open");
    securityOrder.setTicker(quote.getTicker());
    securityOrder.setAccount_id(account.getId());

    assertEquals(securityOrder,securityOrderDao.save(securityOrder));
  }

  @Test
  public void update(){
    securityOrder.setStatus("close");
    securityOrderDao.save(securityOrder);
    assertEquals(securityOrder,securityOrderDao.save(securityOrder));
  }
  @After
  public void clean(){
    securityOrderDao.deleteAll();
    quoteDao.deleteAll();
    accountDao.deleteAll();
    traderDao.deleteAll();
  }
}