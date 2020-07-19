package ca.jrvs.apps.trading.model.domain;




public class SecurityOrder implements Entity<Integer>{
  private Integer id;
  private String status;
  private String ticker;
  private Integer size;
  private Integer price;
  private String notes;

  public Integer getAccount_id() {
    return account_id;
  }

  public void setAccount_id(Integer account_id) {
    this.account_id = account_id;
  }

  private Integer account_id;

  @Override
  public Integer getId() {
    return this.id;
  }

  @Override
  public void setId(Integer integer) {
    this.id = integer;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getTicker() {
    return ticker;
  }

  public void setTicker(String ticker) {
    this.ticker = ticker;
  }

  public Integer getSize() {
    return size;
  }

  public void setSize(Integer size) {
    this.size = size;
  }

  public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    SecurityOrder that = (SecurityOrder) o;

    if (id != null ? !id.equals(that.id) : that.id != null) {
      return false;
    }
    if (status != null ? !status.equals(that.status) : that.status != null) {
      return false;
    }
    if (ticker != null ? !ticker.equals(that.ticker) : that.ticker != null) {
      return false;
    }
    if (size != null ? !size.equals(that.size) : that.size != null) {
      return false;
    }
    if (price != null ? !price.equals(that.price) : that.price != null) {
      return false;
    }
    return notes != null ? notes.equals(that.notes) : that.notes == null;
  }

}
