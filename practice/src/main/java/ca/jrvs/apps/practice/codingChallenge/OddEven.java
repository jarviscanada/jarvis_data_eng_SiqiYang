package ca.jrvs.apps.practice.codingChallenge;

/**
 * https://www.notion.so/b4a0204920ca4ea2a0f507e3d4eda4b5?v=1ae54ae9d0894f1fb0db44bed1409821&p=e58464798bcd4aa3b6f89c1f11a57b58
 */
public class OddEven {

  /**
   * Big-O: O(1)
   * Justification: it's an arithmetic operation
   */
  public String oddEvenMod(int i){
    return i % 2 == 0 ? "even" : "odd";
  }

  /**
   * Big-O: O(1)
   * Justification: determine if the give int is odd or even.
   */
  public String oddEvenBit(int i){
    return (i^1) == i+1 ? "even":"odd";

  }

  public static void main(String[] args) {
    int a = 4;
    OddEven b = new OddEven();
    System.out.println(b.oddEvenBit(a));
    System.out.print(a^1);
  }

}