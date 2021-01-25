package ca.jrvs.apps.practice.codingChallenge;

/**
 * Created by melo45 on 2021-01-07.
 */
public class ReverseLinkedList {

   public static class ListNode {

     int val;
     ListNode next;

     ListNode() {
     }

     ListNode(int val) {
       this.val = val;
     }

     ListNode(int val, ListNode next) {
       this.val = val;
       this.next = next;
     }
   }

  /**
   * we first check if the length is empty if the input is empty then we return null
   * if the input is not null
   * then we first create a current node which is used to iterate through the linked list.
   * then we initialize another node to represent the answer. first we define it's value as the head's value
   * then let it's next is null.
   * next we do the iteration, we initialize another node named next head and lets next head value equal's to the
   * current value and it's next equals to the node. update the node's value.
   * @param head
   * @return
   */
    public ListNode reverseList(ListNode head) {
      if(head == null) {
        return null;
      }
      ListNode node = new ListNode();
      ListNode current = head;
      node.val = current.val;
      node.next = null;
      while(current.next != null) {
        current = current.next;
        if(current != null) {
          ListNode nextHead = new ListNode();
          nextHead.val = current.val;
          nextHead.next = node;
          node = nextHead;
        }

      }
      return node;
    }

  /**
   * example 1->2->3->4->5->null
   * this method uses the recursive way to solve the problem, first we define the base case
   * which is then head = null which means the input is null or we reach the null position
   * the next base case is when we reach the 5 in the example. we should also return the head itself
   * then we back to the upper level 4. this case we have 4->5->null  and 5->null these two node
   * we can let 4 next next to itself. then 4 next equals null. this will break
   * @param head
   * @return
   */
  public ListNode reverseListRecursive(ListNode head) {
    if(head == null || head.next == null) {
      return head;
    }

    ListNode node = reverseList(head.next);

    // 4->5 -> null to 4 -> 5 ->4 -5...
    head.next.next = head;

    //break the loop 4 -> 5 -> 4 -> 5 to  5->4-> null
    head.next = null;

    return node;
  }

}
