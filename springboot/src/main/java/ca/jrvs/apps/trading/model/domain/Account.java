package ca.jrvs.apps.trading.model.domain;


public class Account implements Entity<Integer>{
  private Integer id;
  private Integer trader_id;
  private Double amount;

  @Override
  public Integer getId() {
    return this.id;
  }

  @Override
  public void setId(Integer integer) {
    this.id = integer;
  }

  public Integer getTrader_id() {
    return trader_id;
  }

  public void setTrader_id(Integer trader_id) {
    this.trader_id = trader_id;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Account account = (Account) o;

    if (id != null ? !id.equals(account.id) : account.id != null) {
      return false;
    }
    if (trader_id != null ? !trader_id.equals(account.trader_id) : account.trader_id != null) {
      return false;
    }
    return amount != null ? amount.equals(account.amount) : account.amount == null;
  }

}
