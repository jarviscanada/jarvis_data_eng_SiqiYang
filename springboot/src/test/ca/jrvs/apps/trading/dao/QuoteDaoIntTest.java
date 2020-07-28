package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.assertEquals;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.model.domain.Quote;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
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
public class QuoteDaoIntTest {

  @Autowired
  private QuoteDao quoteDao;
  private Quote savedQuote;


  @Before
  public void insertOne() {
    savedQuote = new Quote();
    savedQuote.setId(String.valueOf("AAPL"));
    savedQuote.setAskPrice(10d);
    savedQuote.setAskSize(10);
    savedQuote.setBidPrice(10.2d);
    savedQuote.setBidSize(10);
    savedQuote.setLastPrice(10.1d);
    quoteDao.save(savedQuote);
    Optional<Quote> quote = quoteDao.findById("AAPL");
    assertEquals(quote.get(),savedQuote);
  }


  @After
  public void deleteOne() {
    quoteDao.deleteById(savedQuote.getId());
  }





  @Test
  public void saveAll() throws Exception {
    Quote quote = new Quote();
    quote.setId(String.valueOf("abc"));
    quote.setAskPrice(10d);
    quote.setAskSize(10);
    quote.setBidPrice(10.2d);
    quote.setBidSize(10);
    quote.setLastPrice(10.1d);

    Quote quote_two = new Quote();
    quote_two.setId(String.valueOf("LOL"));
    quote_two.setAskPrice(10d);
    quote_two.setAskSize(10);
    quote_two.setBidPrice(10.2d);
    quote_two.setBidSize(10);
    quote_two.setLastPrice(10.1d);
    List quoteList = new ArrayList<Quote>();
    quoteList.add(quote);
    quoteList.add(quote_two);
    List returnQuote = quoteDao.saveAll(quoteList);
    assertEquals(returnQuote,quoteList);
    quoteDao.deleteAll();
  }

//
  @Test
  public void existsById() throws Exception {
    assertEquals(true,quoteDao.existsById(savedQuote.getId()));
  }

  @Test
  public void updateOne() throws  Exception {
    Quote quote = new Quote();
    quote.setId(String.valueOf("AAPL"));
    quote.setAskPrice(900d);
    quote.setAskSize(900);
    quote.setBidPrice(10.2d);
    quote.setBidSize(10);
    quote.setLastPrice(10.1d);
    quoteDao.updateOne(quote);
    assertEquals(1,quoteDao.updateOne(quote));
  }
//
  @Test
  public void findAll() throws Exception {
    Quote quote = new Quote();
    quote.setId(String.valueOf("abc"));
    quote.setAskPrice(10d);
    quote.setAskSize(10);
    quote.setBidPrice(10.2d);
    quote.setBidSize(10);
    quote.setLastPrice(10.1d);

    Quote quote_two = new Quote();
    quote_two.setId(String.valueOf("LOL"));
    quote_two.setAskPrice(10d);
    quote_two.setAskSize(10);
    quote_two.setBidPrice(10.2d);
    quote_two.setBidSize(10);
    quote_two.setLastPrice(10.1d);
    List quoteList = new ArrayList<Quote>();
    quoteList.add(quote);
    quoteList.add(quote_two);
    quoteList.add(savedQuote);
    quoteDao.saveAll(quoteList);
    List<Quote> returnQuote = (List)quoteDao.findAll();
    assertEquals(returnQuote,quoteList);
  }


  @Test
  public void count() throws Exception {
    Quote quote = new Quote();
    quote.setId(String.valueOf("abc"));
    quote.setAskPrice(10d);
    quote.setAskSize(10);
    quote.setBidPrice(10.2d);
    quote.setBidSize(10);
    quote.setLastPrice(10.1d);

    Quote quote_two = new Quote();
    quote_two.setId(String.valueOf("LOL"));
    quote_two.setAskPrice(10d);
    quote_two.setAskSize(10);
    quote_two.setBidPrice(10.2d);
    quote_two.setBidSize(10);
    quote_two.setLastPrice(10.1d);
    List quoteList = new ArrayList<Quote>();
    quoteList.add(quote);
    quoteList.add(quote_two);
    quoteList.add(savedQuote);
    quoteDao.saveAll(quoteList);
    assertEquals(quoteDao.count(),3);
  }

  @Test
  public void deleteById() throws Exception {
    Quote quote = new Quote();
    quote.setId(String.valueOf("abc"));
    quote.setAskPrice(10d);
    quote.setAskSize(10);
    quote.setBidPrice(10.2d);
    quote.setBidSize(10);
    quote.setLastPrice(10.1d);

    Quote quote_two = new Quote();
    quote_two.setId(String.valueOf("LOL"));
    quote_two.setAskPrice(10d);
    quote_two.setAskSize(10);
    quote_two.setBidPrice(10.2d);
    quote_two.setBidSize(10);
    quote_two.setLastPrice(10.1d);
    List quoteList = new ArrayList<Quote>();
    quoteList.add(quote);
    quoteList.add(quote_two);
    quoteList.add(savedQuote);
    quoteDao.saveAll(quoteList);
    assertEquals(3,quoteDao.count());
    quoteDao.deleteById("abc");
    assertEquals(2,quoteDao.count());
    assertEquals(false,quoteDao.existsById("abc"));
  }



}