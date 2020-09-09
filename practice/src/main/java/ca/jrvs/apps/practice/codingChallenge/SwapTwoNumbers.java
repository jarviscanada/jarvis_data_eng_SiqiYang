package ca.jrvs.apps.practice.codingChallenge;

/**
 * link:https://www.notion.so/Swap-two-numbers-bdba2e47976547c59893b87c1c6f8c70
 */
public class SwapTwoNumbers {

  /**
   * we use bit manipulation to do this XOR.
   * run time is O(n)
   * @param arr
   * @return
   */
  public int[] approachOne(int[] arr) {
    arr[0] = arr[0] ^ arr[1];
    arr[1] =  arr[0] ^ arr[1];
    arr[0] =  arr[0] ^ arr[1];
    return arr;
  }


  /**
   * we will use the sum this two first and then minus the one element once to the second one
   * and minus again to get first one.
   * @param arr
   * @return
   */
  public int[] approachTwo(int[] arr) {
    arr[0] = arr[0] + arr[1];
    arr[1] = arr[0] - arr[1];
    arr[0] = arr[0] -arr[1];
    return arr;
  }


}
