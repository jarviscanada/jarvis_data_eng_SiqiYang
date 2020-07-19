package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Trader;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.assertj.core.util.Lists;
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
public class AccountDaoTest {
  @Autowired
  private AccountDao accountDao;
  @Autowired
  private TraderDao traderDao;
  private Account account;
  private Trader trader;


  @Before
  public void setUp() throws Exception {
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
    assertEquals(account,accountDao.save(account));
  }

  @Test
  public void update() {
    account.setAmount(1000d);
    accountDao.save(account);
    assertEquals(account,accountDao.save(account));
    assertEquals(1000,accountDao.save(account).getAmount(),0);
  }

  @Test
  public void findAllById() {
    List<Account> accounts = Lists.newArrayList(accountDao.findAllById(Arrays.asList(account.getId())));
    assertEquals(1,accounts.size());
    assertEquals(account.getAmount(),accounts.get(0).getAmount());
  }

  @After
  public void tearDown() throws Exception {
    accountDao.deleteAll();
  }

}