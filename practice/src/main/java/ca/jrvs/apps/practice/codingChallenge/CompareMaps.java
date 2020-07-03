package ca.jrvs.apps.practice.codingChallenge;

import ca.jrvs.apps.practice.dataStructure.JMap;
import com.fasterxml.jackson.core.util.InternCache;
import java.util.Map;
import ca.jrvs.apps.practice.dataStructure.HashJMap;

/**
 *link : https://www.notion.so/b4a0204920ca4ea2a0f507e3d4eda4b5?v=1ae54ae9d0894f1fb0db44bed1409821&p=c6bf90d393e241708074d947d7cbf67e
 */
public class CompareMaps {

  /**
   * Big O(n)
   * @param mapOne the first map object.
   * @param mapTwo the second map object.
   * @return the true/false if two maps are same or not same.
   */
  public boolean collectionCompare(Map mapOne, Map mapTwo) {
    return mapOne.equals(mapTwo);
  }

  /**
   * Big O(n)
   * @param mapOne the first map
   * @param mapTwo the second map object.
   * @return the true/false if two maps are same or not the same.
   */
  public  boolean customCompare(HashJMap mapOne, HashJMap mapTwo) {
    return mapOne.equal(mapTwo);
  }

}
