package ca.jrvs.apps.practice.codingChallenge;

import static org.junit.Assert.*;

import ca.jrvs.apps.practice.codingChallenge.StackUsingQueue.Onequeue;
import org.junit.Test;

/**
 * Created by melo45 on 2020-07-12.
 */
public class StackUsingQueueTest {
  private Onequeue onequeueTest;
  private StackUsingQueue stackUsingQueue;

  @Test
  public void testTwoQueue() {
    this.stackUsingQueue = new StackUsingQueue();
    stackUsingQueue.push(123);
    assertEquals(123,stackUsingQueue.top());
    assertEquals(123,stackUsingQueue.pop());
    stackUsingQueue.push(456);
    assertEquals(456,stackUsingQueue.top());
    assertEquals(456,stackUsingQueue.pop());
    stackUsingQueue.push(789);
    assertEquals(789,stackUsingQueue.top());
    assertEquals(789,stackUsingQueue.pop());
  }


  @Test
  public void setUp() {
    this.onequeueTest = new Onequeue();
    onequeueTest.push(123);
    assertEquals(123,onequeueTest.top());
    assertEquals(123,onequeueTest.pop());
    onequeueTest.push(456);
    assertEquals(456,onequeueTest.top());
    assertEquals(456,onequeueTest.pop());
    onequeueTest.push(789);
    assertEquals(789,onequeueTest.top());
    assertEquals(789,onequeueTest.pop());
  }


}