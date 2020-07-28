package ca.jrvs.apps.trading.service;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.dao.MarketDataDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.model.domain.Quote;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
public class QuoteServiceTest {
  @Autowired
  private QuoteService quoteService;
  @Autowired
  private QuoteDao quoteDao;
  @Autowired
  private MarketDataDao marketDataDao;

  @Before
  public void setup() {
    quoteDao.deleteAll();
  }

  @Test
  public void findIexQuoteByTicker() throws Exception {
    IexQuote iexQuote = quoteService.findIexQuoteByTicker("FB");
    assertEquals(iexQuote.getSymbol(),"FB");
  }

  @Test
  public void updateMarketData() throws Exception {
    Quote quote = new Quote();
    quote.setId(String.valueOf("FB"));
    quote.setAskPrice(10d);
    quote.setAskSize(10);
    quote.setBidPrice(10.2d);
    quote.setBidSize(10);
    quote.setLastPrice(10.1d);
    quoteDao.save(quote);
    quoteService.updateMarketData();
    assertEquals(quoteDao.existsById("FB"),true);
    assertEquals(marketDataDao.findById(quote.getTicker()).get().getLatestPrice(),
        quoteDao.findById(quote.getTicker()).get().getLastPrice());
  }


  @Test
  public void saveQuotes() throws Exception {
    List name = new ArrayList<String>();
    name.add("AAPL");
    name.add("FB");
    List<Quote> quotes = quoteService.saveQuotes(name);
    quotes.forEach(element -> {
      assertEquals(quoteDao.findById(element.getTicker()).get().getLastPrice(),marketDataDao.findById(element.getTicker()).get().getLatestPrice());
    });
  }


  @Test
  public void saveQuote1() throws Exception {
    Quote quote = new Quote();
    quote.setId(String.valueOf("FB"));
    quote.setAskPrice(10d);
    quote.setAskSize(10);
    quote.setBidPrice(10.2d);
    quote.setBidSize(10);
    quote.setLastPrice(10.1d);
    quoteService.saveQuote(quote);
    assertEquals(quote,quoteDao.findById(quote.getTicker()).get());

  }

  @Test
  public void findAllQuotes() throws Exception {
    Quote quote = new Quote();
    quote.setId(String.valueOf("FB"));
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
    List quotes = new ArrayList();
    quotes.add(quote);
    quotes.add(quote_two);
    quoteDao.saveAll(quotes);
    assertEquals(quotes,quoteService.findAllQuotes());
  }

}