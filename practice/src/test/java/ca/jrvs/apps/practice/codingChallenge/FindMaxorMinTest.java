package ca.jrvs.apps.practice.codingChallenge;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by melo45 on 2020-08-23.
 */
public class FindMaxorMinTest {
  List list = new ArrayList<Integer>();
  FindMaxorMin find  = new FindMaxorMin();


  @Before public void setUp() {
    list.add(3);
    list.add(4);
    list.add(5);
  }
  @Test
  public void findMaxOne() throws Exception {
   assertEquals(find.findMaxOne(list).intValue(),5);


  }

  @Test
  public void findMaxTwo() throws Exception {
    assertEquals(find.findMaxTwo(list).intValue(),5);
  }

  @Test
  public void findMaxThree() throws Exception {
    assertEquals(find.findMaxThree(list).intValue(),5);
  }

}