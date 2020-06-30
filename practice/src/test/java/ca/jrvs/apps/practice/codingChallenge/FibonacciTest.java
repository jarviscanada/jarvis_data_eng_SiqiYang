package ca.jrvs.apps.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Created by melo45 on 2020-06-29.
 */
public class FibonacciTest {

  @Test
  public void fib() throws Exception {
    Fibonacci a= new Fibonacci();
    assertEquals(a.fib(17),1597);
  }

  @Test
  public void dynamicVersion() throws Exception {
    Fibonacci a= new Fibonacci();
    assertEquals(a.dynamicVersion(17),1597);
  }

}