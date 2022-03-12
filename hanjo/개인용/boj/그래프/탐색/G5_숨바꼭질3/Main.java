package 그래프.탐색.G5_숨바꼭질3;

import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        System.out.println(solution(n, k));
    }


    public static final int MAX_SIZE = 100_000;
    public static int[] distance = new int[MAX_SIZE + 1];

    public static int solution(int n, int k) {
        
        for(int i=0; i <= MAX_SIZE; i++){
            distance[i] = Integer.MAX_VALUE;
        }

        // 우선순위큐의 정렬 기준이 큐 외부에 있음 -> 정렬 기준이 변경될때마다 정렬이 바뀐다.. (잘못된 풀이)
        // PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> distance[o1] - distance[o2]);

        Queue<Integer> queue = new LinkedList<>();
        distance[n] = 0;
        queue.offer(n);

        // 다익스트라 
        while (!queue.isEmpty()) {

            int curV = queue.poll();

            int nextV;
            int newDistance;
            
            if (curV - 1 >= 0) {
                nextV = curV - 1;
                newDistance = distance[curV] + 1;
                if(newDistance < distance[nextV]){
                    queue.offer(nextV);
                    distance[nextV] = newDistance;
                }
            }
            if (curV + 1 <= MAX_SIZE) {
                nextV = curV + 1;
                newDistance = distance[curV] + 1;
                if(newDistance < distance[nextV]){
                    queue.offer(nextV);
                    distance[nextV] = newDistance;
                }
            } 
            if (curV * 2 <= MAX_SIZE) {
                nextV = curV * 2;
                newDistance = distance[curV];
                if(newDistance < distance[nextV]){
                    queue.offer(nextV);
                    distance[nextV] = newDistance;
                }
            } 
        }

        return distance[k];
    }
}

/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/13549
 * 성공여부 : 실패
 * 풀이시간 : 2h
 * 
 * 시간복잡도 : O(엄청큼) / 다익스트라는 O(간선수 * log정점수)
 * 메모리 : 22640 KB
 * 소요 시간 : 244 ms
 * ================================================================================
 * 
 * 일단 이게 어떻게 그래프 문제인지 머릿속으로 그려져야 한다.
 * 이게 왜 그래프인지를 계속 이해하지 못해서 결국 구글링함.
 * 그리고 손으로 케이스들이 분기되는걸 그려보고, k정점까지의 경우의수들을 모두 생각해보니 그래프라는 것을 깨달음
 * 
 * 결국 소요시간이 간선 가중치가 되고 n -> k까지의 최단경로를 구하는 것이므로 다익스트라로 풀면됨
 * 
 */