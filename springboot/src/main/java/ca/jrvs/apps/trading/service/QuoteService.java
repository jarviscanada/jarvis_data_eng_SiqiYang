package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.MarketDataDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.model.domain.Quote;
import com.sun.org.apache.xpath.internal.operations.Quo;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import javax.transaction.Transactional;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class QuoteService {
  private static final Logger logger = LoggerFactory.getLogger(QuoteService.class);
  private QuoteDao quoteDao;
  private MarketDataDao marketDataDao;

  @Autowired
  public QuoteService(QuoteDao quoteDao,MarketDataDao marketDataDao) {
    this.marketDataDao = marketDataDao;
    this.quoteDao = quoteDao;
  }

  public IexQuote findIexQuoteByTicker(String ticker) {
    return marketDataDao.findById(ticker)
        .orElseThrow(()-> new IllegalArgumentException(ticker+ "is invalid"));
  }

  public void updateMarketData() {
    Iterable<Quote> quotes =  quoteDao.findAll();
    quotes.forEach(element -> quoteDao.save(buildQuoteFromIexquote(marketDataDao.findById(element.getTicker()).get())));
  }


  protected static Quote buildQuoteFromIexquote(IexQuote Iexquote) {
    Quote quote = new Quote();
    if (Iexquote.getIsUSMarketOpen() == false) {
      quote.setLastPrice(Iexquote.getLatestPrice());
      quote.setBidSize(0);
      quote.setBidPrice(0d);
      quote.setAskSize(0);
      quote.setAskPrice(0d);
      quote.setTicker(Iexquote.getSymbol());
    }else {
      quote.setTicker(Iexquote.getSymbol());
      quote.setAskPrice(Iexquote.getIexAskPrice());
      quote.setAskSize(Iexquote.getIexAskSize().intValue());
      quote.setBidSize(Iexquote.getIexBidSize().intValue());
      quote.setBidPrice(Iexquote.getIexBidPrice());
      quote.setLastPrice(Iexquote.getLatestPrice());
    }
    return quote;
  }

  public List<Quote> saveQuotes(List<String> tickers) {
    List quotes = new ArrayList<Quote>();
    tickers.forEach(elements -> quotes.add(saveQuote(elements)));
    return quotes;
  }


  public Quote saveQuote(String ticker) {
    Quote quote = quoteDao.save(buildQuoteFromIexquote(marketDataDao.findById(ticker).get()));
    return quote;
  }

  public Quote saveQuote(Quote quote) {
    return quoteDao.save(quote);
  }

  public List<Quote> findAllQuotes() {
    return (List<Quote>) quoteDao.findAll();
  }


}
