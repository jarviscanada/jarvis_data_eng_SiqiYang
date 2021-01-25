package ca.jrvs.apps.practice.codingChallenge;

import java.util.Arrays;

/**
 * link:https://leetcode.com/problems/rotate-string/solution/
 */
public class RotateString {

  /**
   * complexity will be O(N^2)
   * we double the length of a string and to see if the doubled string contains the target string.
   * it will continues search the string length. in worst case, for every counter it will search the length of the target string.
   * So worst case will be O(N^2).
   * @param a
   * @param b
   * @return
   */
  public boolean check(String a,String b) {
    if(a.length() != b.length()) {
      return false;
    }
    if(a.length() == 0 && b.length() == 0) {
      return true;
    }
    String newOne = a + a;
    Boolean flag = false;
    for(int i = 0; i < newOne.length(); i ++) {
      if(newOne.charAt(i) == b.charAt(0)) {
        for(int j = i; j-i < b.length()&& j< newOne.length(); j ++) {
          if(newOne.charAt(j) != b.charAt(j-i)) {
            flag = false;
            break;
          }
          flag = true;
        }
        if(flag == true) {
          break;
        }
      }
    }
    return flag;
  }


  public static void main(String[] args) {
    RotateString rotateString = new RotateString();
    Boolean a = rotateString.check("ohbrwzxvxe","uornhegseo");
    System.out.print(a);

  }
}
