// https://leetcode.com/problems/minimum-number-of-operations-to-move-all-balls-to-each-box/submissions/

import java.util.Arrays;

public class Solution {

    public static int[] minOperations(String boxes) {
        String[] boxArray = boxes.split("");
        int[] answer = new int[boxArray.length];

        for (int i = 0; i < boxArray.length; i++) {
            int count = 0;
            for (int j = 0; j < boxArray.length; j++) {
                // 자기 자신 제외 + 공이 담겨있을때만 연산
                if (i != j && !boxArray[j].equals("0")) {
                    count += Math.abs(i-j);
                }
            }
            // 정답 배열에 추가
            answer[i] = count;
        }

        return answer;
    }

    public static void main(String[] args) {
        String boxes = "110";
        System.out.println(Arrays.toString(minOperations(boxes)));
    }

}
