package ca.jrvs.apps.practice.codingChallenge;

import static org.junit.Assert.*;

import java.util.Arrays;
import org.junit.Test;

/**
 * Created by melo45 on 2020-06-29.
 */
public class TwoSumTest {

  @Test
  public void sortVersion() throws Exception {
    TwoSum a = new TwoSum();
    assertEquals(a.sortVersion(new int[]{3,3},6)[0],0);
    assertEquals(a.sortVersion(new int[]{3,3},6)[1],1);
    assertEquals(a.sortVersion(new int[]{3,2,5,3},6)[0],0);
    assertEquals(a.sortVersion(new int[]{3,2,5,3},6)[1],3);
  }

  @Test
  public void bruteForce() throws Exception {
    TwoSum a = new TwoSum();
    assertEquals(a.bruteForce(new int[]{3,3},6)[0],0);
    assertEquals(a.bruteForce(new int[]{3,3},6)[1],1);
    assertEquals(a.bruteForce(new int[]{3,2,5,3},6)[0],0);
    assertEquals(a.bruteForce(new int[]{3,2,5,3},6)[1],3);
  }

  @Test
  public void linerTime() throws Exception {
    TwoSum a = new TwoSum();
    assertEquals(a.linerTime(new int[]{3,3},6)[0],0);
    assertEquals(a.linerTime(new int[]{3,3},6)[1],1);
    assertEquals(a.linerTime(new int[]{3,2,5,3},6)[0],0);
    assertEquals(a.linerTime(new int[]{3,2,5,3},6)[1],3);
  }

}