package ca.jrvs.apps.practice.codingChallenge;

import static org.junit.Assert.*;

import org.hamcrest.core.StringContains;
import org.junit.Test;

/**
 * Created by melo45 on 2020-08-23.
 */
public class StringContainsDigitsTest {
  StringContainsDigits object = new StringContainsDigits();


  @Test
  public void approachOne() throws Exception {
    assertTrue(object.approachOne("123"));
    assertFalse(object.approachOne("123.123"));
    assertFalse(object.approachOne("asd"));
  }

  @Test
  public void approachTwo() throws Exception {
    assertTrue(object.approachTwo("123"));
    assertFalse(object.approachTwo("123.123"));
    assertFalse(object.approachTwo("asd"));
  }

  @Test
  public void approachThree() throws Exception {
    assertTrue(object.approachThree("123"));
    assertFalse(object.approachThree("123.123"));
    assertFalse(object.approachThree("asd"));
  }

}