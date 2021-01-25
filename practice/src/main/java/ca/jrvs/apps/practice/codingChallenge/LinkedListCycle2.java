package ca.jrvs.apps.practice.codingChallenge;

/**
 * link:https://leetcode.com/problems/linked-list-cycle-ii/
 */

import ca.jrvs.apps.practice.codingChallenge.ReverseLinkedList.ListNode;
import java.util.HashMap;
import java.util.Map;

/**
 * this question is about detecting the cycle in the linked list and return the
 * start node for this cycle.
 */
public class LinkedListCycle2 {

  /**
   * for the first method we use the map to do it same as the linkedlist Cycle problem
   * if we dont meet the same node, we put the current node and it's position into the map
   * if we meet same node again, we return the current node.
   * in order to solve the two duplicate node value problem, we store the node itself into the map
   * rather than it's value.
   * @param head
   * @return
   */
  public ListNode MethodOne(ListNode head) {
    Map map = new HashMap();
    int pos = 0;
    if(head == null) {
      return head;
    }
    while(head.next != null) {
      if(!map.containsKey(head) ) {
        map.put(head,pos);
      }else {
        return head;
      }
      pos++;
      head = head.next;
    }
    return null;
  }

  /**
   * this method is using the floyd's method, we have two pointers the first one goes 1 step each time
   * the second one goes 2 steps each time
   * we set the meet position is m after the start of the cycle
   * the p is the distance from head to the start of the cycle
   * then 2* step = p + m + b*c
   * c is the cycle length
   * b is the number of the cycles
   *
   * step = p + m + a * c
   * so step = (b-a)*c = p + m + a*c
   * which means p + m is the n * length of the cycle
   * then we set another pointer from the head each time one step
   * then the third pointer meets the slower pointer in the start position of the cycle
   * because third pointer need goes p steps and slower need goes another p steps too, then the total length
   * for the slower pointer is m + p is the numbers of the cycle so they will meet at the start of the cycle.
   *
   * when they actually meet
   * @param head
   * @return
   */
  public ListNode detectCycle(ListNode head) {
    ListNode pre1 = head;
    ListNode pre2 = head;
    while(pre2 !=null && pre2.next!=null){
      pre1 = pre1.next;
      pre2 = pre2.next.next;
      if(pre1 ==pre2){
        while(head != pre1){
          head = head.next;
          pre1 = pre1.next;
        }
        return pre1;
      }
    }
    return null;
  }
}
