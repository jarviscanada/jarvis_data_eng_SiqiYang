package ca.jrvs.apps.practice.codingChallenge;

import static org.junit.Assert.*;

import ca.jrvs.apps.practice.dataStructure.HashJMap;
import java.util.HashMap;
import org.junit.Test;

/**
 *
 */
public class CompareMapsTest {

  @Test
  public void collectionCompare() throws Exception {
    HashMap a = new HashMap<String,Integer>();
    HashMap b = new HashMap<String,Integer>();
    CompareMaps compareMaps = new CompareMaps();
    a.put("2",1);
    a.put("2",3);
    b.put("2",1);
    a.put("3",1);
    b.put("3",1);
    assertEquals(compareMaps.collectionCompare(a,b),false);
    HashMap c = new HashMap<String,Integer>();
    HashMap d = new HashMap<String,Integer>();
    c.put("2",1);
    d.put("2",1);
    c.put("3",1);
    d.put("3",1);
    assertEquals(true,compareMaps.collectionCompare(c,d));

  }

  @Test
  public void customCompare() throws Exception {
    HashJMap a = new HashJMap<String,Integer>();
    HashJMap b = new HashJMap<String,Integer>();
    CompareMaps compareMaps = new CompareMaps();
    a.put("2",1);
    a.put("2",3);
    b.put("2",1);
    a.put("3",1);
    b.put("3",1);
    assertEquals(compareMaps.customCompare(a,b),false);
    HashJMap c = new HashJMap<String,Integer>();
    HashJMap d = new HashJMap<String,Integer>();
    c.put("2",1);
    d.put("2",1);
    c.put("3",1);
    d.put("3",1);
    assertEquals(compareMaps.customCompare(c,d),true);
  }

}