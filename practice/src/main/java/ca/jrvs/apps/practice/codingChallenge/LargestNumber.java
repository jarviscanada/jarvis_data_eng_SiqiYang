package ca.jrvs.apps.practice.codingChallenge;

import java.util.Arrays;
import java.util.Comparator;

/**
 * https://leetcode.com/problems/largest-number/
 */

/**
 * this question is about giving a array of the ints then we use these numbers to combine the largest number
 */
class LargestNumber {
  private class LargerNumberComparator implements Comparator<String> {
    @Override
    public int compare(String a, String b) {
      String order1 = a + b;
      String order2 = b + a;
      return order2.compareTo(order1);
    }
  }

  public String largestNumber(int[] nums) {
    // Get input integers as strings.
    String[] asStrs = new String[nums.length];
    for (int i = 0; i < nums.length; i++) {
      asStrs[i] = String.valueOf(nums[i]);
    }

    // Sort strings according to custom comparator.
    Arrays.sort(asStrs, new LargerNumberComparator());


    // If, after being sorted, the largest number is `0`, the entire number
    // is zero.
    if (asStrs[0].equals("0")) {
      return "0";
    }

    // Build largest number from sorted array.
    String largestNumberStr = new String();
    for (String numAsStr : asStrs) {
      largestNumberStr += numAsStr;
    }

    return largestNumberStr;
  }

  public static void main(String[] args) {
    LargestNumber a = new LargestNumber();
    int[] b = new int[]{11,23,3};
    System.out.print(a.largestNumber(b));
  }
}

