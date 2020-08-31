package ca.jrvs.apps.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Created by melo45 on 2020-07-28.
 */
public class RotateStringTest {

  @Test
  public void check() throws Exception {
    RotateString rotateString = new RotateString();
    assertEquals(false,rotateString.check("ohbrwzxvxe","uornhegseo"));
    assertEquals(true,rotateString.check("",""));
    assertEquals(false,rotateString.check("asd","a"));
    assertEquals(true,rotateString.check("abcde","cdeab"));

  }

}