package ca.jrvs.apps.trading.model.domain;

import java.util.Date;

/**
 * Created by melo45 on 2020-07-17.
 */
public class Trader implements Entity<Integer>{
  private Integer id;
  private String first_name;
  private String last_name;
  private Date  dob;
  private String country;
  private String email;
  @Override
  public Integer getId() {
    return this.id;
  }

  @Override
  public void setId(Integer integer) {
    this.id = integer;
  }

  public String getFirst_name() {
    return first_name;
  }

  public void setFirst_name(String first_name) {
    this.first_name = first_name;
  }

  public String getLast_name() {
    return last_name;
  }

  public void setLast_name(String last_name) {
    this.last_name = last_name;
  }

  public Date getDob() {
    return dob;
  }

  public void setDob(Date dob) {
    this.dob = dob;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Trader trader = (Trader) o;

    if (id != null ? !id.equals(trader.id) : trader.id != null) {
      return false;
    }
    if (first_name != null ? !first_name.equals(trader.first_name) : trader.first_name != null) {
      return false;
    }
    if (last_name != null ? !last_name.equals(trader.last_name) : trader.last_name != null) {
      return false;
    }
    if (dob != null ? !dob.equals(trader.dob) : trader.dob != null) {
      return false;
    }
    if (country != null ? !country.equals(trader.country) : trader.country != null) {
      return false;
    }
    return email != null ? email.equals(trader.email) : trader.email == null;
  }

}
