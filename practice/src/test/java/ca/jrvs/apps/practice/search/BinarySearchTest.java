package ca.jrvs.apps.practice.search;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class BinarySearchTest {
  BinarySearch binarySearch;
  Integer[] integers;
  @Before
  public void setUp(){
    binarySearch = new BinarySearch();
    integers = new Integer[]{1,2,3,4,5,6};

  }
  @Test
  public void binarySearchRecursion() throws Exception {
    assertEquals(0,binarySearch.binarySearchRecursion(integers,1).get(),0);
    assertEquals(1,binarySearch.binarySearchRecursion(integers,2).get(),0);
    assertEquals(2,binarySearch.binarySearchRecursion(integers,3).get(),0);
    assertEquals(3,binarySearch.binarySearchRecursion(integers,4).get(),0);
    assertEquals(4,binarySearch.binarySearchRecursion(integers,5).get(),0);
    assertEquals(5,binarySearch.binarySearchRecursion(integers,6).get(),0);
  }

  @Test
  public void binarySearchIteration() throws Exception {
    assertEquals(0,binarySearch.binarySearchIteration(integers,1).get(),0);
    assertEquals(1,binarySearch.binarySearchIteration(integers,2).get(),0);
    assertEquals(2,binarySearch.binarySearchIteration(integers,3).get(),0);
    assertEquals(3,binarySearch.binarySearchIteration(integers,4).get(),0);
    assertEquals(4,binarySearch.binarySearchIteration(integers,5).get(),0);
    assertEquals(5,binarySearch.binarySearchIteration(integers,6).get(),0);
  }

}