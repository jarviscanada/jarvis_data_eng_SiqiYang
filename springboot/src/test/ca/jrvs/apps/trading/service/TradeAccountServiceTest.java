package ca.jrvs.apps.trading.service;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.TraderDao;
import ca.jrvs.apps.trading.model.TraderAccountView;
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
public class TradeAccountServiceTest {

  private TraderAccountView traderAccountView;
  @Autowired
  private TradeAccountService tradeAccountService;
  @Autowired
  private TraderDao traderDao;
  @Autowired
  private AccountDao accountDao;

  @Before
  public void createTraderAndAccount() throws Exception {
    Trader trader = new Trader();
    trader.setCountry("china");
    trader.setDob(new Date(123,10,2));
    trader.setEmail("thisisfortest@gmail.com");
    trader.setFirst_name("this");
    trader.setLast_name("test");
    traderAccountView = tradeAccountService.createTraderAndAccount(trader);
    assertNotNull(traderAccountView.getAccount());
    trader.setId(traderAccountView.getTrader().getId());
    assertEquals(trader,traderAccountView.getTrader());
  }

  @After
  public void deleteTraderById() throws Exception {
    traderAccountView.getAccount().setAmount(0d);
    accountDao.save(traderAccountView.getAccount());
    tradeAccountService.deleteTraderById(traderAccountView.getTrader().getId());
  }

  @Test
  public void deposit() throws Exception {
    tradeAccountService.deposit(traderAccountView.getTrader().getId(),123.3);
    assertEquals(accountDao.findById(traderAccountView.getAccount().getId()).get().getAmount(),123.3,0);
    try{
      tradeAccountService.deposit(traderAccountView.getTrader().getId(),-123.3);
      fail();
    }catch (IllegalArgumentException e) {
      assertTrue(true);
    }
  }

  @Test
  public void withdraw() throws Exception {
    tradeAccountService.deposit(traderAccountView.getTrader().getId(),123.3);
    tradeAccountService.withdraw(traderAccountView.getTrader().getId(),123.3);
    assertEquals(accountDao.findById(traderAccountView.getAccount().getId()).get().getAmount(),0,0);
    try{
      tradeAccountService.deposit(traderAccountView.getTrader().getId(),123.3);
      tradeAccountService.withdraw(traderAccountView.getTrader().getId(),125.3);
      fail();
    }catch (IllegalArgumentException e) {
      assertTrue(true);
    }
    try{
      tradeAccountService.deposit(traderAccountView.getTrader().getId(),123.3);
      tradeAccountService.withdraw(traderAccountView.getTrader().getId(),-125.3);
      fail();
    }catch (IllegalArgumentException e) {
      assertTrue(true);
    }

    try{
      tradeAccountService.deposit(traderAccountView.getTrader().getId()+2,123.3);
      tradeAccountService.withdraw(traderAccountView.getTrader().getId(),-125.3);
      fail();
    }catch (IllegalArgumentException e) {
      assertTrue(true);
    }
  }

}