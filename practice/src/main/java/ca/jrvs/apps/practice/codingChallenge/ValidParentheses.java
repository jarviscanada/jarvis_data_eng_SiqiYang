package ca.jrvs.apps.practice.codingChallenge;

import java.util.HashMap;
import java.util.Stack;

/**
 * link: https://www.notion.so/b4a0204920ca4ea2a0f507e3d4eda4b5?v=1ae54ae9d0894f1fb0db44bed1409821&p=28a5cbda565d48228d10a5265075d416
 */
public class ValidParentheses {
  private HashMap map = new HashMap();
  private Stack stack;


  /**
   * O(n)
   * we use the the stack data structure's characteristic to implement it. Because the problem has the
   * recursive structure, then if we can not pop up all the elements. Then it is the invalid string.
   * @param s
   * @return
   */
  public boolean isValid(String s) {
    stack = new Stack();
    map.put(')','(');
    map.put(']','[');
    map.put('}','{');
    for(int i =0; i<s.length(); i ++) {
      if(s.charAt(i) == '(' || s.charAt(i) == '['|| s.charAt(i) == '{') {
        stack.push(s.charAt(i));
      }else if (s.charAt(i) == ')' || s.charAt(i) == ']'|| s.charAt(i) == '}') {
        if(stack.peek() == map.get(s.charAt(i))) {
          stack.pop();
        }
      }
    }
    return stack.isEmpty();
  }


}
