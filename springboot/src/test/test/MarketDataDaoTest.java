package test;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.dao.MarketDataDao;
import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.junit.Before;
import org.junit.Test;

public class MarketDataDaoTest {
  MarketDataDao dao;
  @Before
  public void setUp(){
    PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
    cm.setMaxTotal(50);
    cm.setDefaultMaxPerRoute(50);
    MarketDataConfig marketDataConfig = new MarketDataConfig();
    marketDataConfig.setHost("https://cloud.iexapis.com/v1/");
    marketDataConfig.setToken(System.getenv("IEX_PUB_TOKEN"));
    dao = new MarketDataDao(cm,marketDataConfig);
  }
  @Test
  public void findById() throws Exception {
    List<IexQuote> quoteList = dao.findAllById(Arrays.asList("FB","AAPL"));
    assertEquals(2,quoteList.size());
    assertEquals("AAPL",quoteList.get(1).getSymbol());

    try{
     dao.findAllById(Arrays.asList("FB2","AAPL"));
     fail();
    }catch(IllegalArgumentException e) {
      assertTrue(true);
    }
  }

  @Test
  public void findAllById() throws Exception {
    String ticker = "AAPL";
    IexQuote iexQuote = dao.findById(ticker).get();
    assertEquals(ticker,iexQuote.getSymbol());
  }

}