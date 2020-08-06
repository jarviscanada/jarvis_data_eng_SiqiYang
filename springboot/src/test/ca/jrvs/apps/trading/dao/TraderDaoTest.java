package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.TestConfig;
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
public class TraderDaoTest {
  @Autowired
  private TraderDao traderDao;
  private Trader trader;

  @Before
  public void InsertOne() {
    trader = new Trader();
    trader.setCountry("china");
    trader.setDob(new Date(123,10,2));
    trader.setEmail("thisisfortest@gmail.com");
    trader.setFirst_name("this");
    trader.setLast_name("test");

    Trader traderReturn = traderDao.save(trader);
    assertEquals(traderReturn,trader);
  }

  @After
  public void cleanUp(){
    traderDao.deleteAll();
  }

  @Test
  public void findAllById() {
    List<Trader> traders = Lists.newArrayList(traderDao.findAllById(Arrays.asList(trader.getId())));
    assertEquals(1,traders.size());
    assertEquals(trader.getCountry(),traders.get(0).getCountry());
  }

  @Test
  public void updateOne() {
    trader.setCountry("asdasdasdasdasd");
    Trader traderReturn = traderDao.save(trader);
    System.out.print(trader.getId());
    assertEquals(traderReturn,trader);
  }
}