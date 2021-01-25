package ca.jrvs.apps.practice.codingChallenge.sorting;


public class MergeSort {

  public void mergeSort(int[] arr, int left, int right) {
    if (left < right) {
      int middle = ((left + right) / 2);
      mergeSort(arr, left, middle);
      mergeSort(arr, middle + 1, right);
      merge(arr, left, right, middle);
    }


  }
  public void merge(int[] arr,int left, int right,int middle) {
    int first_sub_size = middle - left +1;
    int second_sub_size = right - middle;

    int[] first_sub = new int[first_sub_size];
    int[] second_sub = new int[second_sub_size];


    int count_total = left;
    for(int i = 0; i < first_sub_size; i ++) {
      first_sub[i] = arr[left+i];
    }
    for(int j = 0; j < second_sub_size; j ++) {
      second_sub[j] = arr[middle+j+1];
    }

    int firstCount = 0;
    int secondCount = 0;
    while(firstCount < first_sub_size && secondCount < second_sub_size) {
      if(first_sub[firstCount]<= second_sub[secondCount]) {
        arr[count_total] = first_sub[firstCount];
        firstCount++;
      }else {
        arr[count_total] = second_sub[secondCount];
        secondCount++;
      }
      count_total++;
    }

    while(firstCount < first_sub_size) {
      arr[count_total] = first_sub[firstCount];
      firstCount++;
      count_total++;
    }

    while(secondCount < second_sub_size) {
      arr[count_total] = second_sub[secondCount];
      secondCount++;
      count_total++;
    }

  }

  public static void main(String[] args) {
    MergeSort mergeSort = new MergeSort();
    int[] a = {5,2,7,4,6,3,1,0};
    mergeSort.mergeSort(a,0,7);

  }
}
