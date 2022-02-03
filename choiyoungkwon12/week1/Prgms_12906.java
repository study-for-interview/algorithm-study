package Programmers;

import java.util.Arrays;
import java.util.Stack;

/**
 * https://programmers.co.kr/learn/courses/30/lessons/12906?language=java
 * 같은 숫자는 싫어
 */

public class Solution14 {

    public static void main(String[] args) {
        Solution14 s = new Solution14();
        int[] solution = s.solution(new int[]{1, 1, 3, 3, 0, 1, 1});
        System.out.println("solution = " + Arrays.toString(solution));
    }

    public int[] solution(int[] arr) {
        Stack<Integer> stack = new Stack<>();

        stack.push(arr[0]);

        for (int i = 1; i < arr.length; i++) {
            if (stack.peek() != arr[i]) {
                stack.push(arr[i]);
            }
        }

        return stack.stream().mapToInt(value -> value).toArray();
    }
}
