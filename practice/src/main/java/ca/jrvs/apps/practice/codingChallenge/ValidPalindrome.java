package ca.jrvs.apps.practice.codingChallenge;

import java.util.regex.Pattern;

/**
 * link:https://www.notion.so/Valid-Palindrome-a73927cfe1b64d6a8202a40b0bc45fb2
 *
 */
public class ValidPalindrome {

  /**
   * runtime O(n), it just iterate through the string from end to begin and begin to end.
   * and perform the equation checks.
   * @param strings
   * @return
   */
  public boolean twoPointer(String strings) {
    if(strings.length() == 0) {
      return true;
    }
    String newOne = strings.toLowerCase();
    int end = strings.length() -1;
    int begin = 0;
    while(begin != strings.length()-1) {
     if(!Character.isLetterOrDigit(newOne.charAt(begin))) {
       begin ++;
     }else if(!Character.isLetterOrDigit(newOne.charAt(end))) {
        end --;
      }else {
       if(newOne.charAt(begin) != newOne.charAt(end)) {
         return false;
       }
       begin ++;
       end --;
     }
    }
    return true;
  }


  /**
   * it uses the same idea for two pointer but as a recursive manner.
   * time complexity will be O(n), because once we reach the number of the length steps, we stop
   * and each time we only move for one index.
   * @param string
   * @param begin
   * @param end
   * @return
   */
  public boolean recursive(String string,int begin, int end) {
    if(begin <= end) {

      if (string.length() == 0) {
        return true;
      }

      if (!Character.isLetterOrDigit(string.charAt(begin))) {

        return recursive(string, begin+1, end);
      } else if (!Character.isLetterOrDigit(string.charAt(end))) {
        return recursive(string, begin, end-1);
      }
      if(Character.isLetterOrDigit(string.charAt(begin))
          && Character.isLetterOrDigit(string.charAt(end))) {
        if (string.charAt(begin) != string.charAt(end)) {
          return false;
        }
      }



      return recursive(string, begin+1, end -1 );
    }else {
      return true;
    }


  }


  public static void main(String[] args) {
    ValidPalindrome validPalindrome = new ValidPalindrome();
    String string = "A man, a plan, a canal: Panama";

    Boolean bollean = validPalindrome.recursive(string.toLowerCase(),0,string.length()-1);
    Boolean bollea2 = validPalindrome.twoPointer(string.toLowerCase());

    System.out.print(bollean);
    System.out.print(bollea2);
  }
}
