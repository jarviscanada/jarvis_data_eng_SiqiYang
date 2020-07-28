package ca.jrvs.apps.trading.model.domain;


public class Quote implements Entity<String>{
  private String ticker;
  private Double lastPrice;
  private Double bidPrice;
  private Integer bidSize;
  private Double askPrice;
  private Integer askSize;

  public String getTicker() {
    return ticker;
  }

  public void setTicker(String ticker) {
    this.ticker = ticker;
  }

  @Override
  public String getId() {
    return this.ticker;
  }

  @Override
  public void setId(String ticker) {
    this.ticker = ticker;
  }

  public Double getLastPrice() {
    return lastPrice;
  }

  public void setLastPrice(Double lastPrice) {
    this.lastPrice = lastPrice;
  }

  public Double getBidPrice() {
    return bidPrice;
  }

  public void setBidPrice(Double bidPrice) {
    this.bidPrice = bidPrice;
  }

  public Integer getBidSize() {
    return bidSize;
  }

  public void setBidSize(Integer bidSize) {
    this.bidSize = bidSize;
  }

  public Double getAskPrice() {
    return askPrice;
  }

  public void setAskPrice(Double askPrice) {
    this.askPrice = askPrice;
  }

  public Integer getAskSize() {
    return askSize;
  }

  public void setAskSize(Integer askSize) {
    this.askSize = askSize;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Quote quote = (Quote) o;

    if (ticker != null ? !ticker.equals(quote.ticker) : quote.ticker != null) {
      return false;
    }
    if (lastPrice != null ? !lastPrice.equals(quote.lastPrice) : quote.lastPrice != null) {
      return false;
    }
    if (bidPrice != null ? !bidPrice.equals(quote.bidPrice) : quote.bidPrice != null) {
      return false;
    }
    if (bidSize != null ? !bidSize.equals(quote.bidSize) : quote.bidSize != null) {
      return false;
    }
    if (askPrice != null ? !askPrice.equals(quote.askPrice) : quote.askPrice != null) {
      return false;
    }
    return askSize != null ? askSize.equals(quote.askSize) : quote.askSize == null;
  }


}
