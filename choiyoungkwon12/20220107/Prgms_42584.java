package Programmers;

import java.util.Arrays;
import java.util.Stack;

/**
 * https://programmers.co.kr/learn/courses/30/lessons/42584?language=java 코딩테스트 연습 스택/큐 주식가격
 */

public class Solution15 {

    public static void main(String[] args) {
        Solution15 solution = new Solution15();
        int[] solution1 = solution.solution(new int[]{1, 2, 3, 4, 5});
        System.out.println("solution = " + Arrays.toString(solution1));
    }

    public int[] solution(int[] prices) {
        Stack<Integer> stack = new Stack<>();
        int[] answer = new int[prices.length];

        for (int i = 0; i < prices.length; i++) {
            while (!stack.isEmpty() && prices[i] < prices[stack.peek()]) {
                answer[stack.peek()] = i - stack.peek();
                stack.pop();
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            answer[stack.peek()] = prices.length - stack.peek() - 1;
            stack.pop();
        }

        return answer;
    }
}
