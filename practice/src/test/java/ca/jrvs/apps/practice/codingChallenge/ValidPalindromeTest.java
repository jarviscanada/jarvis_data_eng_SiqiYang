package ca.jrvs.apps.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Created by melo45 on 2020-07-28.
 */
public class ValidPalindromeTest {

  @Test
  public void twoPointer() throws Exception {
    ValidPalindrome validPalindrome = new ValidPalindrome();
    String string = "A man, a plan, a canal: Panama";
    String stringOne = "A an, a plan, a canal: Panama";
    String stringThree = "";
   assertEquals(true,validPalindrome.twoPointer(string.toLowerCase()));
   assertEquals(false,validPalindrome.twoPointer(stringOne.toLowerCase()));
   assertEquals(true,validPalindrome.twoPointer(stringThree.toLowerCase()));

  }

  @Test
  public void recursive() throws Exception {
    ValidPalindrome validPalindrome = new ValidPalindrome();
    String string = "A man, a plan, a canal: Panama";
    String stringOne = "A an, a plan, a canal: Panama";
    assertEquals(true,validPalindrome.recursive(string.toLowerCase(),0,string.length()-1));
    String stringThree = "";
    assertEquals(false,validPalindrome.recursive(stringOne.toLowerCase(),0,stringOne.length()-1));
    assertEquals(true,validPalindrome.recursive(stringThree.toLowerCase(),0,stringThree.length()-1));

  }

}