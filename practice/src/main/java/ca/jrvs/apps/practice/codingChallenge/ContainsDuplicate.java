package ca.jrvs.apps.practice.codingChallenge;

import java.util.HashMap;
import java.util.Map;

/**
 * link:https://www.notion.so/b4a0204920ca4ea2a0f507e3d4eda4b5?v=1ae54ae9d0894f1fb0db44bed1409821&p=39a7976a08bc484fa27813e6995a615b
 */
public class ContainsDuplicate {

  /**
   * the main idea behind the algorithm is that we put the nums as the key into
   * the hash map and count it's show rate. if we find duplicate key then we know
   * we have duplicate numbers otherwise we dont have duplicate number.
   * @param nums
   * @return
   */
  public boolean check(int[] nums) {
    Map map = new HashMap<Integer,Integer>();
    for(int i = 0; i< nums.length; i ++) {
      if(map.containsKey(nums[i])) {
        return true;
      }else {
        map.put(nums[i],0);
      }
    }
    return false;
  }
}
