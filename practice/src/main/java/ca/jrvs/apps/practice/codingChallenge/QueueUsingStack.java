package ca.jrvs.apps.practice.codingChallenge;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * link: https://www.notion.so/b4a0204920ca4ea2a0f507e3d4eda4b5?v=1ae54ae9d0894f1fb0db44bed1409821&p=63657da834bf4349bba352fdd572edb1
 */
public class QueueUsingStack {
  public static class ApprouchOne {
    private Stack stackOne = new Stack();
    private Stack stackTwo = new Stack();
    private int top;

    public void push(int x) {
      while(!stackOne.empty()) {
       stackTwo.push(stackOne.pop());

      }
      stackOne.push(x);
      while(!stackTwo.empty()) {
        stackOne.push(stackTwo.pop());
      }
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
      return (Integer) stackOne.pop();
    }

    /** Get the front element. */
    public int peek() {
      return (Integer) stackOne.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
      return stackOne.empty()?true:false;
    }

  }


  public static class ApprouchTwo {
    private Stack stackOne = new Stack();
    private Stack stackTwo = new Stack();
    private int top;

    public void push(int x) {
      if(stackOne.empty()) {
        top = x;
      }
      stackOne.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
      if(stackTwo.empty()) {
        while(!stackOne.empty()) {
          stackTwo.push(stackOne.pop());
        }
      }
      int returnOne = (Integer) stackTwo.pop();
      if(!stackTwo.empty()) {
        top = (Integer) stackTwo.peek();
      }else {
        top = returnOne;
      }

      return returnOne;
    }

    /** Get the front element. */
    public int peek() {
      return top;
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
      return stackTwo.empty()?true:false;
    }

  }

  public static void main(String[] args) {
//    ApprouchOne a = new ApprouchOne();
//    a.push(1);
//    a.push(2);
//    a.push(3);
//    System.out.println(a.peek());
//    System.out.println(a.pop());
//    System.out.println(a.peek());
//    System.out.println(a.pop());
//    System.out.println(a.peek());
//    System.out.println(a.pop());
//    System.out.println(a.empty());

    ApprouchTwo b = new ApprouchTwo();
    b.push(1);
    b.push(2);
    b.push(3);
    System.out.println(b.peek());
    System.out.println(b.pop());
    System.out.println(b.peek());
    System.out.println(b.pop());
    System.out.println(b.peek());
    System.out.println(b.pop());
    System.out.println(b.empty());


  }
}
