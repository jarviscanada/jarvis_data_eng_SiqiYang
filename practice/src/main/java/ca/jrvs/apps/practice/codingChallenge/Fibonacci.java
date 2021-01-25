package ca.jrvs.apps.practice.codingChallenge;

import java.util.Collections;

/**
 * https://www.notion.so/Fibonacci-Number-Climbing-Stairs-bab6e4beda1145418ce2e063679b0e5d
 */
public class Fibonacci {

  /**
   * O(2^n)
   * @param N
   * @return Fibonacci sum for the given integer.
   */
  public int fib(int N) {
    int answer = 0;
    if(N <2) {
      return N;
    }
    if(N >= 2) {
      answer =  fib(N-1) + fib(N-2);
    }

    return answer;
  }

  /**
   * O(N)
   * @param N the input size.
   * @return the fibonacci number for the given index.
   */
  public int dynamicVersion(int N) {
    if(N <2) {
      return N;
    }
    int[] memory = new int[N+1];
    memory[0] = 0;
    memory[1] = 1;
    for(int i = 2; i < N+1; i ++) {
      memory[i] = memory[i-1] + memory[i-2];
    }
    return memory[N];
  }


}
