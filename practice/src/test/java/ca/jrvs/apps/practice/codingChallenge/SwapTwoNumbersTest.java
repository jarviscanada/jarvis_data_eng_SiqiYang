package ca.jrvs.apps.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Created by melo45 on 2020-08-23.
 */
public class SwapTwoNumbersTest {

  @Test
  public void approachOne() throws Exception {
    SwapTwoNumbers swapTwoNumbers = new SwapTwoNumbers();
    assertEquals(swapTwoNumbers.approachOne(new int[]{0,1})[0],1);
    assertEquals(swapTwoNumbers.approachOne(new int[]{0,1})[1],0);

  }

  @Test
  public void approachTwo() throws Exception {
    SwapTwoNumbers swapTwoNumbers = new SwapTwoNumbers();

   assertEquals(swapTwoNumbers.approachTwo(new int[]{0,1})[0],1);
    assertEquals(swapTwoNumbers.approachTwo(new int[]{0,1})[1],0);
  }

}