package ca.jrvs.apps.practice.codingChallenge;

/**
 * link:https://www.notion.so/Print-letter-with-number-d4c89d02d1d64f6380a66068f8b9a5ca
 */
public class PrintLetterWithDigit {

  /**
   * we use the ascii number to get the index of the characters.
   * runtime is O(n);
   * @param string
   * @return
   */
  public String print(String string){
   char[] chars = string.toCharArray();
   String answer = "";
   for(int i = 0; i < chars.length; i ++) {
     if(Character.isUpperCase(chars[i])) {
       answer = answer + chars[i];
       answer = answer + ((int)chars[i] - 38);
     }else {
       answer = answer + chars[i];
       answer = answer + ((int)chars[i] - 96);
     }
   }
   return answer.toString();
  }

  public static void main(String[] args) {
    PrintLetterWithDigit print = new PrintLetterWithDigit();
    System.out.print(print.print("aAbBcCeEeE"));

  }
}
