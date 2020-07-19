package ca.jrvs.apps.trading.model;


import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Position;
import ca.jrvs.apps.trading.model.domain.Trader;
import java.util.List;

public class PortfolioView {
  private Account account;
  private List<Position> positions;

  public PortfolioView(Account account, List<Position> positionList) {
    this.positions = positionList;
    this.account = account;
  }


  public Account getTrader() {
    return account;
  }

  public void setTrader(Account trader) {
    this.account = trader;
  }

  public List<Position> getPositions() {
    return positions;
  }

  public void setPositions(List<Position> positions) {
    this.positions = positions;
  }
}
