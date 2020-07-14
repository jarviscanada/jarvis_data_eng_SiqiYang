package ca.jrvs.apps.trading;


import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AppConfig {
  private Logger logger = LoggerFactory.getLogger(AppConfig.class);


  @Bean
  public MarketDataConfig marketDataConfig() {
   MarketDataConfig marketDataConfig = new MarketDataConfig();
   marketDataConfig.setToken(System.getenv("IEX_PUB_TOKEN"));
   marketDataConfig.setHost("https://cloud.iexapis.com/v1/");
   return marketDataConfig;
  }

  @Bean
  public HttpClientConnectionManager httpClientConnectionManager() {
    PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
    cm.setMaxTotal(50);
    cm.setDefaultMaxPerRoute(50);
    return cm;
  }

}
