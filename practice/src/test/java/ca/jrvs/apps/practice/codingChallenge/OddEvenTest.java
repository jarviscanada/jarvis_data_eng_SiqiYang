package ca.jrvs.apps.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Created by melo45 on 2020-06-29.
 */
public class OddEvenTest {

  @Test
  public void oddEvenMod() throws Exception {
    OddEven oddEven = new OddEven();
    assertEquals(oddEven.oddEvenMod(1231234),"even");
    assertEquals(oddEven.oddEvenMod(1234567),"odd");

  }

  @Test
  public void oddEvenBit() throws Exception {
    OddEven oddEven = new OddEven();
    assertEquals(oddEven.oddEvenBit(1231234),"even");
    assertEquals(oddEven.oddEvenBit(1234567),"odd");
  }

}