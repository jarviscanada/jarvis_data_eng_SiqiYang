package ca.jrvs.apps.twitter.model;

import java.util.List;

/**
 * Created by melo45 on 2020-06-30.
 */

import com.fasterxml.jackson.annotation.JsonInclude;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Coordinates {

  private double[] coordinates;
  private String type;

  public double[] getCoordinates() {
    return coordinates;
  }

  public void setCoordinates(double[] coordinates) {
    this.coordinates = coordinates;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }




}
