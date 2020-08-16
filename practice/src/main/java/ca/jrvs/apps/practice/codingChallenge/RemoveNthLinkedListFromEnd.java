package ca.jrvs.apps.practice.codingChallenge;


/**
 * link https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 */



public class RemoveNthLinkedListFromEnd {

  public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

  public ListNode removeNthFromEnd(ListNode head, int n) {
    int count = 0;
    ListNode listnode = head;
    ListNode node = new ListNode(0);
    node.next = head;
    while(listnode.next !=null) {
      count ++;
      listnode = listnode.next;
    }
    listnode = node;
    int position = count -n;
    while(position >= 0) {
      position --;
      listnode = listnode.next;
    }
    listnode.next = listnode.next.next;
    return node.next;
  }


}
