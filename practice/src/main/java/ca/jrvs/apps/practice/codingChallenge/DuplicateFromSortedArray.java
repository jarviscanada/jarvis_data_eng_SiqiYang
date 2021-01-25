package ca.jrvs.apps.practice.codingChallenge;

/**
 * link:https://www.notion.so/b4a0204920ca4ea2a0f507e3d4eda4b5?v=1ae54ae9d0894f1fb0db44bed1409821&p=d86c94f5d36448dc826c22e4fdbe1b92
 */
public class DuplicateFromSortedArray {

  /**
   * we have a sorted array and we want to remove the duplicate and return the unique
   * @param nums
   * @return
   */
  public int removeDuplicates(int[] nums) {
    if (nums.length == 0) return 0;
    int i = 0;
    for (int j = 1; j < nums.length; j++) {
      if (nums[j] != nums[i]) {
        i++;
        nums[i] = nums[j];
      }
    }
    return i + 1;
  }

  public static void main(String[] args) {
    DuplicateFromSortedArray a = new DuplicateFromSortedArray();
    int[] array = new int[]{1,1,2,2,3};
    a.removeDuplicates(array);
  }
}
