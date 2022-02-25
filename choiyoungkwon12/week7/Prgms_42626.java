package Programmers;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * https://programmers.co.kr/learn/courses/30/lessons/42626?language=java
 * 프로그래머스 - 코딩테스트 연습- 힙(Heap) - 더 맵게
 */

public class Prgms_42626 {

    public int solution(int[] scoville, int K) {
        Arrays.sort(scoville);

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        int cnt = 0;
        for (int i = scoville.length - 1; i >= 0; i--) {

            int i1 = scoville[i];
            priorityQueue.add(i1);
        }

        while (!priorityQueue.isEmpty()) {
            int su1 = priorityQueue.poll();
            int su2 = priorityQueue.poll();
            int result = su1 + (su2 * 2);
            priorityQueue.add(result);
            cnt++;
            if (priorityQueue.size() == 1 && priorityQueue.peek() < K) {
                return -1;
            }
            if (priorityQueue.peek() >= K) {
                break;
            }

        }

        int answer = cnt;
        return answer;
    }

    public static void main(String[] args) {
        //int scoville[] = {1, 2, 3, 9, 10, 12};
        int scoville[] = {1, 1};
        int k = 7;
        Solution3 solution3 = new Solution3();
        int result = solution3.solution(scoville, k);
        System.out.println(result);
    }
}
