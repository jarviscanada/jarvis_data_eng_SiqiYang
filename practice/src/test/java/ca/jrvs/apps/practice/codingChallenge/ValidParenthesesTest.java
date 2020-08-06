package ca.jrvs.apps.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Created by melo45 on 2020-07-12.
 */
public class ValidParenthesesTest {

  @Test
  public void isValid() throws Exception {
    ValidParentheses validParentheses = new ValidParentheses();
    assertEquals(false,validParentheses.isValid("([)]"));
    assertEquals(true,validParentheses.isValid("{[]}"));
    assertEquals(false,validParentheses.isValid("(((((((((("));
    assertEquals(true,validParentheses.isValid("((()(())))"));

  }

}