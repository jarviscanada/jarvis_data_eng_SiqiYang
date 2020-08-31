package ca.jrvs.apps.practice.codingChallenge;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * link https://www.notion.so/b4a0204920ca4ea2a0f507e3d4eda4b5?v=1ae54ae9d0894f1fb0db44bed1409821&p=6794316122434f1c870c968fcb42d667
 */
public class DuplicateLinkedListNode {

  /**
   * I use the hash-map to store the linked list object along with a counter, if we found the
   * we have already stored the element, then we remove it.
   * the runtime will be O(n), it is because we only iterate through the linkedlist once.
   * @param linkedList
   */
  public void removeDuplicate(LinkedList linkedList) {
    Map map = new HashMap<Object,Integer>();
    for(int i = 0; i < linkedList.size(); i ++ ) {
      if(!map.containsKey(linkedList.get(i))) {
        map.put(linkedList.get(i),0);
      }else {
        linkedList.remove(i);
      }
    }
  }



}
