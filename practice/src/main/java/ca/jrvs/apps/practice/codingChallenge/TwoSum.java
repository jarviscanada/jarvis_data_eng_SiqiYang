package ca.jrvs.apps.practice.codingChallenge;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by melo45 on 2020-06-29.
 */
public class TwoSum {

  /**
   * O(n^2)
   * @param nums the given array.
   * @param value the target value.
   * @return the corresponding indices of the array.
   */
  public int[] bruteForce(int[] nums, int value) {
    int[] answer = new int[2];
    for(int i = 0; i < nums.length; i ++) {
      for(int j = i+1; j < nums.length; j ++) {
        if(nums[i] + nums[j] == value) {
          answer[0]= i;
          answer[1] = j;
          return answer;
        }
      }
    }
    return answer;
  }

  /**
   * O(nlogn)
   * @param nums the given array.
   * @param value the target value.
   * @return the corresponding indices of the array.
   */
  public int[] sortVersion(int[] nums, int value) {
    int [] cloneOne = nums.clone();
    int[] answer = new int[2];
    Arrays.sort(cloneOne);
    int left = 0;
    int right = cloneOne.length-1;
    while (cloneOne[left] + cloneOne[right] != value) {
      if (cloneOne[left] + cloneOne[right] > value) {
        right --;
      }else if (cloneOne[left] + cloneOne[right] < value) {
        left ++;
      }
    }

    for(int i = 0; i < nums.length; i ++) {
      if(nums[i] == cloneOne[left]) {
        answer[0] = i;
        cloneOne[left] = -1;
      }else if (nums[i] ==cloneOne[right]) {
        answer[1] = i;
      }
    }

    return answer;
  }



    /**
     * O(n)
     * @param nums the given array.
     * @param value the target value.
     * @return the corresponding indices of the array.
     */
  public int[] linerTime(int[] nums, int value) {
    int[] answer = new int[2];
    Map map = new HashMap<Integer,Integer>();
    for(int i = 0; i < nums.length; i ++) {
      if(map.containsKey(value - nums[i]) && i != (int) map.get(value - nums[i])) {
        answer[1] = i;
        answer[0] = (int) map.get(value - nums[i]);
      }
      map.put(nums[i],i);

    }
    return answer;


  }










}
