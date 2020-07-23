package ca.jrvs.apps.practice.codingChallenge.sorting;


public class InsertionSort {

  public void insertionSort(int[] array) {
    for(int i = 0;i < array.length; i ++) {
      int current = array[i];
      int j = i-1;
      while (j >= 0 && current < array[j]) {
        array[j+1] = array[j];
        j = j - 1;
      }
      array[j+1] = current;
    }
  }

  public static void main(String[] args) {
    InsertionSort insertionSort = new InsertionSort();
    int[] arr;
    arr = new int[]{3,2,4,6,5,1,10,7,8,9};
    insertionSort.insertionSort(arr);
    System.out.print(arr);

  }
}
