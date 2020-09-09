package ca.jrvs.apps.practice.codingChallenge;


import ca.jrvs.apps.practice.codingChallenge.RemoveNthLinkedListFromEnd.ListNode;

/**
 * link: https://www.notion.so/Middle-of-the-Linked-List-7c3812f9a4134bccb32f8eb5bff69241
 */
public class MiddleOfLinkedList {

//  public static class ListNode {
//    int val;
//    RemoveNthLinkedListFromEnd.ListNode next;
//    ListNode() {}
//    ListNode(int val) { this.val = val; }
//    ListNode(int val, RemoveNthLinkedListFromEnd.ListNode next) { this.val = val; this.next = next; }
//  }

  /**
   * runtime O(n), first time, we iterate through the linked list and get the length of the linked list
   * then we get the middle of the linked list.
   * @param head
   * @return
   */
  public ListNode middleNode(ListNode head) {
    int length = 0;
    ListNode node = head;
    ListNode copy = new ListNode(0);

    while(node.next != null) {
      length ++;
      node = node.next;
    }

    int half;
    if(length %2 ==0) {
      half = length / 2;
    }else {
      half = length /2 +1;
    }

    node = head;
    while(half > 0) {
      node = node.next;
      half --;
    }

    return node;
  }
}
