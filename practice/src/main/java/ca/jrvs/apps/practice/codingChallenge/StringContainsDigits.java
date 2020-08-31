package ca.jrvs.apps.practice.codingChallenge;

/**
 * link:https://www.notion.so/Check-if-a-String-contains-only-digits-48e6c0e2919d4170b32d34486b5828ba
 */
public class StringContainsDigits {

  /**
   * we use the Ascii number to check is the string contains only digits or not
   * runtime will be O(n)
   * @param string
   * @return
   */
  public boolean approachOne(String string) {
    for(int i = 0; i<string.length(); i ++) {
      if((int)string.charAt(i) < (int)'0' || (int)string.charAt(i) > (int) '9') {
        return false;
      }
    }
    return true;
  }

  /**
   * we use the built in function Integer.valueOf.
   * runtime will be O(n)
   * @param string
   * @return
   */
  public boolean approachTwo(String string) {
    try{
      Integer.valueOf(string);
    }catch (NumberFormatException e) {
      return false;
    }
    return true;
  }

  /**
   * we use the regular expression to check if this string contains only digits or not.
   * runtime will be O(n)
   * @param string
   * @return
   */
  public boolean approachThree(String string) {
    return string.matches("^\\d*$");
  }



}
