package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Position;
import ca.jrvs.apps.trading.model.domain.Quote;
import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import ca.jrvs.apps.trading.model.domain.Trader;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.swing.text.html.Option;
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
public class PositionDaoTest {

  @Autowired
  private SecurityOrderDao securityOrderDao;
  private SecurityOrder securityOrder;
  SecurityOrder securityOrder_two;
  @Autowired
  private PositionDao positionDao;
  private Position position;
  @Autowired
  private AccountDao accountDao;
  private Account account;
  Account account_one;
  @Autowired
  private QuoteDao quoteDao;
  private Quote quote;
  Quote quote_two;
  @Autowired
  private TraderDao traderDao;
  private Trader trader;
  Trader trader_new;

  @Before
  public void setUp() throws Exception {
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
    securityOrder.setPrice(123d);
    securityOrder.setSize(123);
    securityOrder.setStatus("FILLED");
    securityOrder.setTicker(quote.getTicker());
    securityOrder.setAccount_id(account.getId());
    securityOrderDao.save(securityOrder);


    quote_two = new Quote();
    quote_two.setId(String.valueOf("FB"));
    quote_two.setAskPrice(10d);
    quote_two.setAskSize(10);
    quote_two.setBidPrice(10.2d);
    quote_two.setBidSize(10);
    quote_two.setLastPrice(10.1d);
    quoteDao.save(quote_two);

    trader_new = new Trader();
    trader_new.setCountry("china");
    trader_new.setDob(new Date(123,10,2));
    trader_new.setEmail("ytyty@gmail.com");
    trader_new.setFirst_name("lolo");
    trader_new.setLast_name("lolol");
    trader_new = traderDao.save(trader_new);

    account_one = new Account();
    account_one.setAmount(300d);
    account_one.setTrader_id(trader_new.getId());
    accountDao.save(account_one);

    securityOrder_two = new SecurityOrder();
    securityOrder_two.setNotes("asdasd");
    securityOrder_two.setPrice(123d);
    securityOrder_two.setSize(124);
    securityOrder_two.setStatus("FILLED");
    securityOrder_two.setTicker(quote.getTicker());
    securityOrder_two.setAccount_id(account_one.getId());
    System.out.print(account_one.getId());
    securityOrderDao.save(securityOrder_two);
  }

  @Test
  public void findTickerAndId(){
    position = new Position();
    account.getId();
    quote.getTicker();
    position.setAccount_id(account.getId());
    position.setTicker(quote.getTicker());
    position.setPosition(securityOrder.getSize());
    Optional<Position> returnOne = positionDao.findByIdAndTicker(account.getId(),quote.getTicker());
    assertEquals(position,returnOne.get());

  }

  @Test
  public void findTicker(){
    position = new Position();
    account.getId();
    quote.getTicker();
    position.setAccount_id(account.getId());
    position.setTicker(quote.getTicker());
    position.setPosition(securityOrder.getSize());
    List<Position> returnOne = (List<Position>) positionDao.findByTicker(quote.getTicker());
    assertEquals(position,returnOne.get(0));

  }

  @Test
  public void findId(){
    position = new Position();
    account.getId();
    quote.getTicker();
    position.setAccount_id(account.getId());
    position.setTicker(quote.getTicker());
    position.setPosition(securityOrder.getSize());
    List<Position> returnOne = (List<Position>) positionDao.findById(account.getId());
    assertEquals(position,returnOne.get(0));

  }

  @After
  public void tearDown() throws Exception {
    securityOrderDao.deleteAll();
    quoteDao.deleteAll();
    accountDao.deleteAll();
    traderDao.deleteAll();
}
}