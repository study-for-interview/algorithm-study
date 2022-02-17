package Programmers;

import java.util.Arrays;

/**
 * https://programmers.co.kr/learn/courses/30/lessons/42842?language=java 완전 탐색 카펫
 */

public class Solution20 {

    public static void main(String[] args) {
        Solution20 s = new Solution20();
        int[] solution = s.solution(10, 2);
        Arrays.stream(solution).forEach(System.out::println);
    }

    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];

        int total = brown + yellow;

        for (int i = 1; i <= total; i++) {
            int row = i;
            int col = total / row;

            if (row > col) {
                continue;
            }

            if ((row - 2) * (col - 2) == yellow) {
                answer[0] = col;
                answer[1] = row;
                return answer;
            }
        }
        return answer;
    }
}
