package ca.jrvs.apps.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Created by melo45 on 2020-08-23.
 */
public class PrintLetterWithDigitTest {

  @Test
  public void print() throws Exception {
    PrintLetterWithDigit print = new PrintLetterWithDigit();
    assertEquals(print.print("aAbBcCeEeE"),"a1A27b2B28c3C29e5E31e5E31");
  }

}