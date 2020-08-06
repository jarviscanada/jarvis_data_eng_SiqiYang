package ca.jrvs.apps.trading.dao;
import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.swing.text.html.Option;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public class MarketDataDao implements CrudRepository<IexQuote, String>{
  private static final String IEX_BATCH_PATH = "stock/market/batch?symbols=%s&types=quote&token=";
  private final String IEX_BATCH_URL;

  private Logger logger = LoggerFactory.getLogger(MarketDataDao.class);
  private HttpClientConnectionManager httpClientConnectionManager;

  @Autowired
  public MarketDataDao(HttpClientConnectionManager httpClientConnectionManager,
      MarketDataConfig marketDataConfig) {
    this.httpClientConnectionManager = httpClientConnectionManager;
    IEX_BATCH_URL = marketDataConfig.getHost() + IEX_BATCH_PATH + marketDataConfig.getToken();
  }

  /**
   * Retrieves an entity by its id.
   *
   * @param ticker must not be {@literal null}.
   * @return the entity with the given id or {@literal Optional#empty()} if none found
   * @throws IllegalArgumentException if {@code id} is {@literal null}.
   */
  @Override
  public Optional<IexQuote> findById(String ticker) {
    Optional<IexQuote> iexQuote;
    List<IexQuote> quotes = findAllById(Collections.singletonList(ticker));
    if(quotes.size() == 0) {
      return Optional.empty();
    }else if(quotes.size() == 1) {
      iexQuote = Optional.of(quotes.get(0));
    } else {
      throw new DataRetrievalFailureException("Unexpected number of quotes");
    }
    return iexQuote;
  }



  /**
   * Returns all instances of the type with the given IDs.
   */
  @Override
  public List<IexQuote> findAllById(Iterable<String> tickers) {
    List<IexQuote> iexQuotes = new ArrayList<>();
    tickers.forEach(element -> {
      Optional<String> responseJson = executeHttpGet(IEX_BATCH_URL.replace("%s",element));
      ObjectMapper mapper = new ObjectMapper();
      try {
        JSONObject json = new JSONObject(responseJson.get());
        iexQuotes.add(mapper.readValue(json.getJSONObject(element).getJSONObject("quote").toString(), IexQuote.class));
      } catch (IOException e) {
        logger.error("can not convert the json to object");
        throw new RuntimeException("parse json error");
      }
    });
    return iexQuotes;
  }


  /**
   * use the http client to execute the request and get the response String.
   * @param url
   * @return
   */
  private Optional<String> executeHttpGet(String url) {
    CloseableHttpClient closeableHttpClient = getHttpClient();
    HttpGet request = new HttpGet(url);

    try {
      CloseableHttpResponse response = closeableHttpClient.execute(request);
      int statusCode = response.getStatusLine().getStatusCode();
      if (statusCode == 200) {
        return Optional.of(EntityUtils.toString(response.getEntity()));
      }else if (statusCode == 404) {
        logger.error("there is not content to show");
        throw new IllegalArgumentException("no content to show");
      }else {
        throw new RuntimeException("Unknown symbol");
      }
    } catch (IOException e) {
      logger.error("can not execute the http request.");
      throw new RuntimeException("unable to proceed the url");
    }
  }


  /**
   * generate the Httpclient.
   * @return
   */
  private CloseableHttpClient getHttpClient() {
    return HttpClients.custom()
        .setConnectionManager(httpClientConnectionManager).setConnectionManagerShared(true).build();
  }



  /**
   * Saves a given entity. Use the returned instance for further operations as the save operation
   * might have changed the entity instance completely.
   *
   * @param entity must not be {@literal null}.
   * @return the saved entity will never be {@literal null}.
   */
  @Override
  public <S extends IexQuote> S save(S entity) {
    throw new UnsupportedOperationException("Not implemented");
  }

  /**
   * Saves all given entities.
   *
   * @param entities must not be {@literal null}.
   * @return the saved entities will never be {@literal null}.
   * @throws IllegalArgumentException in case the given entity is {@literal null}.
   */
  @Override
  public <S extends IexQuote> Iterable<S> saveAll(Iterable<S> entities) {
    throw new UnsupportedOperationException("Not implemented");
  }


  /**
   * Returns whether an entity with the given id exists.
   *
   * @param s must not be {@literal null}.
   * @return {@literal true} if an entity with the given id exists, {@literal false} otherwise.
   * @throws IllegalArgumentException if {@code id} is {@literal null}.
   */
  @Override
  public boolean existsById(String s) {
    throw new UnsupportedOperationException("Not implemented");
  }

  /**
   * Returns all instances of the type.
   *
   * @return all entities
   */
  @Override
  public Iterable<IexQuote> findAll() {
    throw new UnsupportedOperationException("Not implemented");
  }



  /**
   * Returns the number of entities available.
   *
   * @return the number of entities
   */
  @Override
  public long count() {
    throw new UnsupportedOperationException("Not implemented");
  }

  /**
   * Deletes the entity with the given id.
   *
   * @param s must not be {@literal null}.
   * @throws IllegalArgumentException in case the given {@code id} is {@literal null}
   */
  @Override
  public void deleteById(String s) {
    throw new UnsupportedOperationException("Not implemented");
  }

  /**
   * Deletes a given entity.
   *
   * @throws IllegalArgumentException in case the given entity is {@literal null}.
   */
  @Override
  public void delete(IexQuote entity) {
    throw new UnsupportedOperationException("Not implemented");
  }

  /**
   * Deletes the given entities.
   *
   * @throws IllegalArgumentException in case the given {@link Iterable} is {@literal null}.
   */
  @Override
  public void deleteAll(Iterable<? extends IexQuote> entities) {
    throw new UnsupportedOperationException("Not implemented");
  }

  /**
   * Deletes all entities managed by the repository.
   */
  @Override
  public void deleteAll() {
    throw new UnsupportedOperationException("Not implemented");
  }
}
