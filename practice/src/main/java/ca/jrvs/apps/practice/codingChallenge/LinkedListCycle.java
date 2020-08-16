package ca.jrvs.apps.practice.codingChallenge;

import ca.jrvs.apps.practice.codingChallenge.RemoveNthLinkedListFromEnd.ListNode;
import java.util.HashMap;
import java.util.Map;

/**
 * link:https://www.notion.so/LinkedList-Cycle-b924a2ed5c314fa78f1b007148b6a76b
 */
public class LinkedListCycle {

  /**
   * Runtime O(n)
   * we just iterate through the linked list and store the object into a map to check if we will
   * meet a same object twice to determine if there is a cycle.
   * @param head
   * @return
   */
  public boolean hasCycle(ListNode head) {
    if(head == null) {
      return false;
    }
    Map map = new HashMap<ListNode,Integer>();
    while(head.next !=null) {
      if(!map.containsKey(head)) {
        map.put(head,1);
      }else {
        return true;
      }
      head = head.next;
    }

    return false;
  }
}
