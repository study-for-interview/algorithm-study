package week17.카카오_L3_합승택시요금;

import java.util.*;

class Solution {

    public static class Node implements Comparable<Node>{
        public int num;
        public int weight;

        public Node(int num, int weight){
            this.num = num;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o){
            return this.weight - o.weight;
        }
    }

    public static final int INF = Integer.MAX_VALUE;
    public int n;
    public Map<Integer, List<Node>> graph;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        this.n = n;
        
        // 인접리스트 초기화
        graph = new HashMap<>();
        for(int i=1; i<=n; i++){
            graph.put(i, new ArrayList<>());
        }
        for(int[] fare : fares){
            graph.get(fare[0]).add(new Node(fare[1], fare[2]));
            graph.get(fare[1]).add(new Node(fare[0], fare[2]));
        }

        // A,B,S 최단거리 배열 초기화
        int[] distS = new int[n+1];
        int[] distA = new int[n+1];
        int[] distB = new int[n+1];
        for(int i=1; i<=n; i++){
            distS[i] = INF;
            distA[i] = INF;
            distB[i] = INF;
        }

        // 다익스트라 
        dijkstra(s, distS);
        dijkstra(a, distA);
        dijkstra(b, distB);

        // 가장 효율적인 경로 찾기
        int min = INF;
        for(int i=1; i<=n; i++){
            min = Math.min(min, distS[i] + distA[i] + distB[i]);
        }

        return min;
    }

    public void dijkstra(int start, int[] dist){
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(start, 0));
        dist[start] = 0;

        while(!queue.isEmpty()){
            Node cur = queue.poll();

            for(Node next : graph.get(cur.num)){
                int nextDist = dist[cur.num] + next.weight;
                if(nextDist >= dist[next.num]){
                    continue;
                }
                dist[next.num] = nextDist;
                queue.offer(next);
            }
        }
    }


    public static void main(String[] args) {
        // 82
        System.out.println(new Solution().solution(6, 4, 6, 2, 
            new int[][]{
                {4,1,10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, 
                {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}
            }
        ));
        // 14
        System.out.println(new Solution().solution(7, 3, 4, 1,
            new int[][]{
                {5, 7, 9}, {4, 6, 4}, {3, 6, 1}, {3, 2, 3}, {2, 1, 6}
            }
        ));
        // 18
        System.out.println(new Solution().solution(6, 4, 5, 6,
            new int[][]{
                {2,6,6}, {6,3,7}, {4,6,7}, {6,5,11}, {2,5,12}, 
                {5,3,20}, {2,4,8}, {4,3,9}
            }
        ));
    }
}

/**
 * ================================================================================
 * 링크 : https://programmers.co.kr/learn/courses/30/lessons/72413
 * 날짜 : 220502
 * 성공여부 : 성공
 * 풀이시간 : 30m
 * 
 * 시간복잡도 : O(E*logV) 3번
 * 테케 7 : (60.23ms, 66.6MB)
 * ================================================================================
 * 
 * 기본적인 다익스트라 문제
 * 
 * 다익스트라를 통해 세 정점에서의 최단경로를 구하고, 가장 작은 값을 가지는 정점을 고르면 끝
 * 지금까지 풀던 다익스트라 방식을 사용했는데, 이보다 더 효율적으로 구현 가능 (Solution2)
 *  -> 앞으로는 효율적인 방법을 사용하자..
 * 
 */