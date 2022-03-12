package 구현.L3_디스크컨트롤러;

import java.util.*;

class Solution {

    public static int solution(int[][] jobs) {

        PriorityQueue<Job> jobQ = new PriorityQueue<>((o1, o2) -> {
            int result = o1.start - o2.start;
            if (result == 0) {
                result = o1.time - o2.time;
            }
            return result;
        });
        for (int[] job : jobs) {
            jobQ.offer(new Job(job[0], job[1]));
        }

        PriorityQueue<Job> disk = new PriorityQueue<>((o1, o2) -> o1.time - o2.time);

        int totalResponseTime = 0;
        int currentTime = jobQ.peek().start;

        disk.offer(jobQ.poll());

        while (!disk.isEmpty()) {
            Job curJob = disk.poll();
            currentTime += curJob.time;
            totalResponseTime += currentTime - curJob.start;

            while (!jobQ.isEmpty() && jobQ.peek().start <= currentTime) {
                disk.offer(jobQ.poll());
            }

            if (disk.isEmpty() && !jobQ.isEmpty()) {
                currentTime = jobQ.peek().start;
                disk.offer(jobQ.poll());
            }
        }

        return totalResponseTime / jobs.length;
    }

    public static class Job {
        public int start;
        public int time;

        public Job(int start, int time) {
            this.start = start;
            this.time = time;
        }
    }

    public static void main(String[] args) {

        // 9
        System.out.println(solution(new int[][] { { 0, 3 }, { 1, 9 }, { 2, 6 } }));

        // 72
        System.out.println(solution(new int[][] { 
            { 24, 10 }, { 28, 39 }, { 43, 20 }, { 37, 5 }, { 47, 22 }, { 20, 47 },
            { 15, 34 }, { 15, 2 }, { 35, 43 }, { 26, 1 } }));

        // 6
        System.out.println(solution(new int[][] { 
            { 0, 5 }, { 2, 10 }, { 100, 2 }}));
    }
}


/**
 * ================================================================================
 * 링크 : https://programmers.co.kr/learn/courses/30/lessons/42627
 * 성공여부 : 성공
 * 풀이시간 : 1h
 * 
 * 시간복잡도 : O(작업수)
 * 메모리(테케7) : 86.6 MB
 * 소요 시간 : 3.55 ms
 * ================================================================================
 * 
 * 우선순위큐(heap)을 어케 잘 사용하는지에 관한 문제
 * + 문제의 요구사항을 잘 읽고 엣지케이스에 대해서 꼼꼼히 생각해보자
 * 이번에는 우선순위큐의 우선순위 기준을 정할때 Comparator를 사용함
 * 
 * < 시행착오 1>
 * 맨처음 작업을 정렬시킬때 요청시간을 기준으로 정렬했었는데,
 * 만약 요청시간이 동일한 작업이 있다면 그 다음 기준으로 작업시간으로 정렬해야 했음
 * 
 * < 시행착오 2>
 * 만약 작업이 끊길 경우 disk 큐에 작업을 임의로 넣어줘야했음
 * (문제를 제대로 안읽어서 삽질함;)
 * 
 */