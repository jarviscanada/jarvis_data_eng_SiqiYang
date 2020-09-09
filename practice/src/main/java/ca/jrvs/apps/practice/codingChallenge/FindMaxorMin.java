package ca.jrvs.apps.practice.codingChallenge;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/**
 * link:https://www.notion.so/Find-Largest-Smallest-4984a44b26284218b0d986b14ba8d3f3
 */
public class FindMaxorMin {

  public Integer findMaxOne(List<Integer> array) {
    Integer max = new Integer(-1);
    for (Integer o : array) {
      if(o.compareTo(max) > 0) {
        max = o;
      }
    }
    return max;
  }


  /**
   * use Stream API.
   * runtime O(n)
   * @param array
   * @return
   */
  public Integer findMaxTwo(List<Integer> array) {
    Integer innteger = array.stream().max(Comparator.comparing(Integer::intValue)).get();
    return innteger;
  }
  /**
   * use the built in java method to do.
   * run time O(n)
   * @param array
   * @return
   */
  public Integer findMaxThree(List<Integer> array) {
    return Collections.max(array);
  }


}
