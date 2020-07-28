package ca.jrvs.apps.practice.codingChallenge;


import java.util.AbstractQueue;
import java.util.LinkedList;
import java.util.Queue;

/**
 * link:https://www.notion.so/b4a0204920ca4ea2a0f507e3d4eda4b5?v=1ae54ae9d0894f1fb0db44bed1409821&p=3df69627f0bc44b3b7553ff6e4b84e09
 */
public class StackUsingQueue {
  private  Queue queueOne;
  private  Queue queueTwo;
  int pop;

  public StackUsingQueue() {
    this.queueOne = new LinkedList();
    this.queueTwo = new LinkedList();
  }

  /** Push element x onto stack. */
  /**
   * O(1)
   * just push the element into the slack.
   * @param x
   */
  public void push(int x) {
    pop = x;
    queueTwo.add(x);
  }

  /** Removes the element on top of the stack and returns that element. */
  /**
   * O(n)
   * we have two queue, one queue for recording the delete element. The other for replace.
   * @return
   */
  public int pop() {
    int returnOne;
    for(int i = 0; i < queueTwo.size()-1; i ++ ) {
      pop = (Integer) queueTwo.remove();
      queueOne.add(pop);
    }
    returnOne = (Integer) queueTwo.remove();
    Queue exchange = queueTwo;
    queueTwo = queueOne;
    queueOne = exchange;
    return returnOne;
  }

  /** Get the top element. */
  public int top() {
   return pop;
  }

  /** Returns whether the stack is empty. */
  /**
   * O(1)
   * @return
   */
  public boolean empty() {
    if(queueTwo.size() == 0) {
      return true;
    }else {
      return false;
    }
  }

  public static class Onequeue {
    private Queue queue;
    private int top;

    public Onequeue() {
      this.queue = new LinkedList();
    }

    /** Push element x onto stack. */
    /**
     * O(1)
     * just add the element into the stack.
     * @param x
     */
    public void push(int x) {
      queue.add(x);
      top = x;
    }

    /** Removes the element on top of the stack and returns that element. */
    /**
     * O(n)
     * iterate through the one queue and re add the element to the element except for the last one.
     * @return
     */
    public int pop() {
      int result = 0;
      for(int i =0; i< queue.size()-1; i ++) {
        result = (Integer) queue.remove();
        top = result;
        queue.add(result);
      }
      result = (Integer) queue.remove();
      top = result;
      return result;
    }

    /** Get the top element. */
    /**
     * just return the top one;
     * O(1)
     * @return
     */
    public int top() {
      return top;
    }

    /** Returns whether the stack is empty. */
    /**
     * just return the queue size;
     * O(1)
     * @return
     */
    public boolean empty() {
      if(queue.size() == 0) {
        return true;
      }else {
        return false;
      }
    }
  }

}


