package 그래프.최단경로.S2_특정거리도시찾기;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());   // 정점 수
        int m = Integer.parseInt(st.nextToken());   // 간선 수
        int k = Integer.parseInt(st.nextToken());   // 거리 정보
        int x = Integer.parseInt(st.nextToken());   // 출발 정점

        int[][] edges = new int[m][2];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            edges[i][0] = Integer.parseInt(st.nextToken());
            edges[i][1] = Integer.parseInt(st.nextToken());
        }

        solution(n, m, k, x, edges);
    }

    // 프로그래머스처럼 주어진다고 가정
    public static void solution(int n, int m, int k, int x, int[][] edges) {

        // 모든 인덱스는 정점에 맞춰 1부터 시작함

        // 인접리스트 생성
        HashMap<Integer, ArrayList<Integer>> edgeList = new HashMap<>();
        for(int i = 1; i < n+1; i++){
            edgeList.put(i, new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            edgeList.get(from).add(to);
        }

        // 정점 정보 초기화
        int[][] vertexInfo = new int[n+1][2];
        for(int i=1; i<n+1; i++){
            vertexInfo[i][0] = Integer.MAX_VALUE;   // 가중치 == 누적경로
            vertexInfo[i][1] = 0;   // 방문여부
        }

        // bfs를 위한 큐 생성
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(x);
        vertexInfo[x][0] = 0;
        vertexInfo[x][1] = 1;

        // 다익스트라 + bfs 
        ArrayList<Integer> correctVertex = new ArrayList<>();
        while(!queue.isEmpty()){
            int curV = queue.poll();

            for(int nextV : edgeList.get(curV)){
                if(vertexInfo[nextV][1] == 0 && vertexInfo[nextV][0] >= vertexInfo[curV][0] ){
                    queue.offer(nextV);
                    // 방문표시
                    vertexInfo[nextV][1] = 1;
                    // 누적거리 업데이트
                    vertexInfo[nextV][0] = vertexInfo[curV][0] + 1;
                    if(vertexInfo[nextV][0] == k){
                        correctVertex.add(nextV);
                    }
                }
            }
        }

        // 출력
        if(correctVertex.isEmpty()){
            System.out.println(-1);
        }
        else{
            Collections.sort(correctVertex);
            for(int v : correctVertex){
                System.out.println(v);
            }
        }
    }
}

/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/18352
 * 성공여부 : 실패 
 * 풀이시간 : 1h30m (다익스트라 공부 다시함)
 * 
 * 시간복잡도 : O(정점수*간선수)
 * 메모리 : 372108 KB
 * 소요 시간 : 1552 ms
 * ================================================================================
 * 
 * 최단경로 구하는 것 == 가중치가 1인 간선에서의 최소 가중치 -> 다익스트라를 사용하기.
 * 
 * 이 문제는 모든 간선의 가중치가 1이기 때문에 다음정점을 고를때 특별한 처리를 해주지 않았음
 * 하지만 각 간선의 가중치가 다르다면, 다음정점 선택시 가중치가 제일 작은 정점을 선택해주어야함
 * -> 이 때 힙을 사용해야함 (PriorityQueue)
 * 
 * 
 */