package ca.jrvs.apps.practice.codingChallenge;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by melo45 on 2020-07-28.
 */
public class ValidAnagram {
  public boolean solutionTwo(String s,String t) {
    if(s.length() != t.length()) {
      return false;
    }
    Map map = new HashMap<Character,Integer>();
    Map map_two = new HashMap<Character,Integer>();
    for(int i = 0; i < s.length(); i ++) {
      if(map.containsKey(s.charAt(i))) {
        map.put(s.charAt(i),(int)map.get(s.charAt(i)) + 1);
      }else {
        map.put(s.charAt(i),1);
      }
      if(map_two.containsKey(t.charAt(i))) {
        map_two.put(t.charAt(i),(int)map_two.get(t.charAt(i))+1);
      }else {
        map_two.put(t.charAt(i),1);
      }
    }

    return map.equals(map_two);
  }

  /**
   * O(nlogn), the main idea is about sorting. we convert string to char array first
   * then we sort it.
   * then we compare each element to see if there is same or not.
   * @param s
   * @param t
   * @return
   */
  public boolean sortVersion(String s, String t) {
    if(s.length()!=t.length()) {
      return false;
    }
    if(s.length() == 0 && t.length() == 0) {
      return true;
    }
    char[] first = s.toCharArray();
    char[] second = t.toCharArray();
    Arrays.sort(first);
    Arrays.sort(second);
    for(int i = 0; i < first.length; i ++) {
      if(first[i] != second[i]) {
        return false;
      }
    }
    return true;
  }


  public boolean hashTable(String s, String t) {
    if(s.length() != t.length()){
      return false;
    }
    int[] array = new int[26];
    for(int i = 0; i < s.length(); i ++) {
      array[s.charAt(i) - 'a'] ++;
      array[t.charAt(i)- 'a'] --;
    }

    for(int j = 0; j < s.length(); j ++) {
      if(array[j] != 0) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {

  }
}
