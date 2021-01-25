package ca.jrvs.apps.practice.codingChallenge;

import ca.jrvs.apps.practice.codingChallenge.ReverseLinkedList.ListNode;

/**
 *
 */
public class ReorderLinkedList {

    public void reorderList(ListNode head) {
      ListNode slower = head;
      ListNode faster = head;
      while(faster.next != null && faster.next.next != null) {
        faster = faster.next.next;
        slower = slower.next;
      }

      slower.next = reverse(slower.next);

      merge(head,slower.next);
    }


    public void merge (ListNode left, ListNode right) {
      ListNode leftNode = new ListNode();
      ListNode rightNode = new ListNode();
      while(left != null) {
       leftNode = left.next;
       if(right == null ) {
         break;
       }
       rightNode = right.next;
       left.next = right;
       right.next = leftNode;

       left = leftNode;
       right = rightNode;
      }


    }

    public ListNode reverse(ListNode head) {

      ListNode node = new ListNode();
      ListNode current = head;
      node.val = current.val;
      node.next = null;
      while(current.next != null) {
        current = current.next;
          ListNode nextHead = new ListNode();
          nextHead.val = current.val;
          nextHead.next = node;
          node = nextHead;
      }
      head = node;
      return head;

  }

  public static void main(String[] args) {
    ListNode listNode = new ListNode(0);
    listNode.next = new ListNode(1);
    listNode.next.next =  new ListNode(2);
    listNode.next.next.next = new ListNode(3);
//    listNode.next.next.next.next = new ListNode(4);

    ReorderLinkedList a = new ReorderLinkedList();
    a.reorderList(listNode);
    System.out.print("2");
  }
}
