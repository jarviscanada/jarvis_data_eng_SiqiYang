package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.MarketDataDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import org.slf4j.Logger;
import javax.transaction.Transactional;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class QuoteService {
  private static final Logger logger = LoggerFactory.getLogger(QuoteService.class);
//  private QuoteDao quoteDao;
  private MarketDataDao marketDataDao;

  @Autowired
  public QuoteService(MarketDataDao marketDataDao) {
    this.marketDataDao = marketDataDao;
//    this.quoteDao = quoteDao;
  }

  public IexQuote findIexQuoteByTicker(String ticker) {
    return marketDataDao.findById(ticker)
        .orElseThrow(()-> new IllegalArgumentException(ticker+ "is invalid"));
  }




}
