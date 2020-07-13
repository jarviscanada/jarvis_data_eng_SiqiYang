package ca.jrvs.apps.practice.codingChallenge;



/**
 * https://www.notion.so/String-to-Integer-atoi-1227699f4669471d9e0dcf138c5d650b
 */
public class StrToInt {

  /**
   * runtime: O(n)
   * This function is using the built in function to convert string to integer.
   * @param string
   * @return
   * @throws NumberFormatException
   */
  public Integer builtIn(String string) throws NumberFormatException{
    try{
      return Integer.valueOf(string);
    } catch (NumberFormatException e) {
      return null;
    }

  }

  /**
   * runtime O(n)
   * @param string
   * @return
   * @throws NumberFormatException
   */
  public Integer normalWay(String string) {
    int answer = 0;
    for (int i = 0; i < string.length(); i ++) {
      if (Character.isDigit(string.charAt(i))) {
        answer += (string.charAt(i) -'0') * Math.pow(10,string.length()-i-1);
      }else {
        throw new NumberFormatException("the number format is not correct.");
      }
    }
    return answer;
  }






}
