package ca.jrvs.apps.practice.codingChallenge;

import static org.junit.Assert.*;

import ca.jrvs.apps.practice.codingChallenge.QueueUsingStack.ApprouchOne;
import ca.jrvs.apps.practice.codingChallenge.QueueUsingStack.ApprouchTwo;
import org.junit.Test;

/**
 * Created by melo45 on 2020-07-12.
 */
public class QueueUsingStackTest {

 @Test
  public void approachOne() {
    ApprouchOne a = new ApprouchOne();
    a.push(1);
    a.push(2);
    a.push(3);
    assertEquals(1,a.peek());
    assertEquals(1,a.pop());
   assertEquals(2,a.peek());
   assertEquals(2,a.pop());
   assertEquals(3,a.peek());
   assertEquals(3,a.pop());
   assertEquals(true,a.empty());
 }

  @Test
  public void approachTwo() {
    ApprouchTwo b = new ApprouchTwo();
    b.push(1);
    b.push(2);
    b.push(3);
    assertEquals(1,b.peek());
    assertEquals(1,b.pop());
    assertEquals(2,b.peek());
    assertEquals(2,b.pop());
    assertEquals(3,b.peek());
    assertEquals(3,b.pop());
    assertEquals(true,b.empty());
  }

}