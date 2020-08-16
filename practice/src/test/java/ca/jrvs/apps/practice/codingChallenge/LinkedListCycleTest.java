package ca.jrvs.apps.practice.codingChallenge;

import static org.junit.Assert.*;

import ca.jrvs.apps.practice.codingChallenge.RemoveNthLinkedListFromEnd.ListNode;
import org.junit.Test;

/**
 * Created by melo45 on 2020-08-16.
 */
public class LinkedListCycleTest {

  @Test
  public void hasCycle() throws Exception {
    ListNode listNode = new ListNode(0);
    listNode.next = new ListNode(1);
    listNode.next.next = new ListNode(2);
    ListNode same = new ListNode(3);
    listNode.next.next.next = same;
    same.next = same;

    LinkedListCycle linkedListCycle = new LinkedListCycle();
    assertEquals(linkedListCycle.hasCycle(listNode),true);

  }

}