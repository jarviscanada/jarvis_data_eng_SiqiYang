package ca.jrvs.apps.trading.model.domain;


public class Position {
  private Integer account_id;
  private Integer position;
  private String ticker;

  public Integer getAccount_id() {
    return account_id;
  }

  public void setAccount_id(Integer account_id) {
    this.account_id = account_id;
  }

  public Integer getPosition() {
    return position;
  }

  public void setPosition(Integer position) {
    this.position = position;
  }

  public String getTicker() {
    return ticker;
  }

  public void setTicker(String ticker) {
    this.ticker = ticker;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Position position1 = (Position) o;

    if (account_id != null ? !account_id.equals(position1.account_id)
        : position1.account_id != null) {
      return false;
    }
    if (position != null ? !position.equals(position1.position) : position1.position != null) {
      return false;
    }
    return ticker != null ? ticker.equals(position1.ticker) : position1.ticker == null;
  }


}
