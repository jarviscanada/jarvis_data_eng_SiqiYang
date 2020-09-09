package ca.jrvs.apps.practice.codingChallenge;

import static org.junit.Assert.*;

import java.util.LinkedList;
import org.junit.Test;

/**
 * Created by melo45 on 2020-08-16.
 */
public class DuplicateLinkedListNodeTest {

  @Test
  public void removeDuplicate() throws Exception {
    LinkedList linkedList = new LinkedList();
    linkedList.add("a");
    linkedList.add("a");
    linkedList.add("b");

    DuplicateLinkedListNode duplicateLinkedListNode = new DuplicateLinkedListNode();
    duplicateLinkedListNode.removeDuplicate(linkedList);
    assertEquals(linkedList.get(1),"b");
  }

}