package ca.jrvs.apps.practice.codingChallenge;

import static org.junit.Assert.*;

import ca.jrvs.apps.practice.codingChallenge.RemoveNthLinkedListFromEnd.ListNode;
import org.junit.Test;

/**
 * Created by melo45 on 2020-08-16.
 */
public class MiddleOfLinkedListTest {

  @Test
  public void middleNode() throws Exception {
    ListNode listNode = new ListNode(0);
    listNode.next = new ListNode(1);
    listNode.next.next = new ListNode(2);
    listNode.next.next.next = new ListNode(3);

    MiddleOfLinkedList middleOfLinkedList = new MiddleOfLinkedList();
    listNode = middleOfLinkedList.middleNode(listNode);
    assertEquals(listNode.val,2);
  }

}