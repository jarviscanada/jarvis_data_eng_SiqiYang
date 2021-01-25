package ca.jrvs.apps.practice.codingChallenge;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * reverse the vowels in the string
 */
public class ReverseVowels {

  /**
   * we have two pointers, one from the start the other from the end, and we check if current char
   * is vowel or not, if not we move to next, if start and end pointer both points to a vowel we exchange
   * until we meet the middle.
   * @param s
   * @return
   */
  public String reverseVowels(String s) {
    char[] arr = s.toCharArray();
    int left = 0, right = arr.length-1;


    while(left <= right) {
      if(isVowel(arr[left]) && isVowel(arr[right])) {
        swap(arr, left, right);
        left++;
        right--;
      } else if(!isVowel(arr[left])) {
        left++;
      } else {
        right--;
      }
    }

    return String.valueOf(arr);
  }

  private boolean isVowel(char c) {
    return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' ||
        c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
  }


  private void swap(char[] arr, int i, int j) {
    char temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  public static void main(String[] args) {
    ReverseVowels a= new ReverseVowels();
    String s = "eE";
    a.reverseVowels(s);
  }
}
