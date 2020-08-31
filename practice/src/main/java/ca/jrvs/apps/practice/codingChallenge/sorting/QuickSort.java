package ca.jrvs.apps.practice.codingChallenge.sorting;


import com.sun.xml.internal.bind.v2.model.annotation.Quick;

public class QuickSort {
  public void quickSort(int[] array,int start,int end) {
    if(start < end) {
      int pivot = partition(array,start,end);
      quickSort(array,start,pivot-1);
      quickSort(array,pivot+1,end);
    }
  }

  public int partition(int[] array, int start, int end) {
    int pivot = array[end];
    int i = start;
    for (int j=start; j < end; j ++) {
      if (array[j] <= pivot) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        i++;
      }
    }
    int tempNew = array[i];
    array[i] = array[end];
    array[end] = tempNew;
    return i;
  }

  public static void main(String[] args) {
    QuickSort quickSort = new QuickSort();
    int[] array= {10,1,5,4,3,2};
    quickSort.quickSort(array,0,5);
  }
}
