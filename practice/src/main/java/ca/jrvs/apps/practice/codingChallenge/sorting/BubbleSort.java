package ca.jrvs.apps.practice.codingChallenge.sorting;


public class BubbleSort {
  public void bubbleSort(int[] numbers){
    for (int i = 0; i < numbers.length; i ++ ){
      for (int j = 0; j < numbers.length-1; j ++) {
        if(numbers[j] > numbers[j+1]) {
          swap(numbers,j,j+1);
        }
      }
    }
  }


  public void swap(int[] array, int from, int to) {
    int swap = array[from];
    array[from] = array[to];
    array[to] = swap;
  }

  public static void main(String[] args) {
    BubbleSort bubbleSort = new BubbleSort();
    int[] array ={1,3,2,5,6,0,4};
    bubbleSort.bubbleSort(array);
    System.out.print(array);
  }
}
