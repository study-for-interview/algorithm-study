package Programmers;

import java.util.Arrays;

/**
 * https://programmers.co.kr/learn/courses/30/lessons/42862?language=java
 * 코딩테스트 연습
 * 탐욕법(Greedy)
 * 체육복문제
 */
public class Solution5 {
    public static void main(String[] args) {
        Solution5 solution = new Solution5();
        int n = 7;
        int[] lost = {1, 2, 3, 4};
        int[] reserve = {5, 6, 7};
        int result = solution.solution(n, lost, reserve);
        System.out.println(result);
    }

    public int solution(int n, int[] lost, int[] reserve) {
        int stu[] = new int[n]; // 학생별 체육복 소지 갯수
        for (int i = 1; i <= stu.length; i++) {
            stu[i - 1] = 1;
            if (contains(lost, i)) {
                stu[i - 1] -= 1;
            }
            if (contains(reserve, i)) {
                stu[i - 1] += 1;
            }
        }

        for (int i = 0; i < stu.length; i++) {
            if (stu[i] >= 2) { // 2개 이상 소지한 경우

                if (i == 0) { // 첫번째 학생의 경우 2번 학생이 0개일 경우
                    if (stu[i + 1] == 0) {
                        stu[i] -= 1;
                        stu[i + 1] += 1;
                    }
                } else if (i == stu.length - 1) { // 마지막 학생이 2개일경우 마지막 전 학생이 0개일 경우
                    if (stu[i - 1] == 0) {
                        stu[i] -= 1;
                        stu[i - 1] += 1;
                    }
                } else { // 2개일 경우 앞 뒤 번호 학생 체육복 0개인지 확인
                    if (stu[i -1] == 0) {
                        stu[i] -= 1;
                        stu[i - 1] += 1;
                    } else if (stu[i + 1] == 0) {
                        stu[i] -= 1;
                        stu[i + 1] += 1;
                    }
                }

            }
        }

        int answer = 0;
        for (int i = 0; i < stu.length; i++) {
            if (stu[i] > 0) {
                answer += 1;
            }
        }
        return answer;
    }

    public static boolean contains(final int[] arr, int key) {
        return Arrays.stream(arr).anyMatch(i -> i == key);
    }
}
