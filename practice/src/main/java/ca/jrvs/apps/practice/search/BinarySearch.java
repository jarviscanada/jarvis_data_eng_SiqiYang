package ca.jrvs.apps.practice.search;

import java.util.Arrays;
import java.util.Optional;

public class BinarySearch {

  /**
   * find the the target index in a sorted array
   *
   * @param arr input arry is sorted
   * @param target value to be searched
   * @return target index or Optional.empty() if not ound
   */
  public <E extends Comparable> Optional<Integer> binarySearchRecursion(E[] arr, E target) {
    int halfSize = (int)Math.floor(arr.length/2);
    Optional answer;
    if(arr.length >=1) {
      if (arr[halfSize] == target) {
        return Optional.of(halfSize);
      }
      if(arr[halfSize].compareTo(target)>0) {
        return binarySearchRecursion(Arrays.copyOfRange(arr,0,halfSize),target);
      }else {
        answer = binarySearchRecursion(Arrays.copyOfRange(arr,halfSize+1,arr.length),target);
        return Optional.of(Optional.of(arr.length/2 +1).get() + (Integer) answer.get());
      }



    }
    return Optional.empty();

  }

  /**
   * find the the target index in a sorted array
   *
   * @param arr input arry is sorted
   * @param target value to be searched
   * @return target index or Optional.empty() if not ound
   */
  public <E extends Comparable> Optional<Integer> binarySearchIteration(E[] arr, E target) {
    int left = 0;
    int right = arr.length;

    while (left < right) {
      int halfSize = (int)Math.floor((right+left)/2);
      if(arr[halfSize].compareTo(target)>0) {
        right = halfSize;
      }else if (arr[halfSize].compareTo(target) < 0) {
        left = halfSize;
      }else {
        return Optional.of(halfSize);
      }
    }
    return Optional.empty();
  }

  public static void main(String[] args) {
    BinarySearch a = new BinarySearch();
    Integer[] b = {1,2,3,4,5,6};
    System.out.print(a.binarySearchRecursion(b,6).get());
    System.out.print(a.binarySearchIteration(b,5).get());
  }
}