package 코테.sk_2022.문제4;

import java.util.*;

class Solution {

    public static final int INF = Integer.MAX_VALUE;

    public static long solution(int n, int[][] edges) {
        // 인접리스트 생성
        HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        // 최단거리 배열 생성
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    dist[i][j] = 0;
                } else {
                    dist[i][j] = INF;
                }
            }
        }
        // 다익스트라
        for (int i = 0; i < n; i++) {
            dijkstra(i, dist, graph);
        }
        // 순서쌍 완전탐색
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (i == j || i == k || k == j) {
                        continue;
                    }
                    if (dist[i][k] == dist[i][j] + dist[j][k]) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public static void dijkstra(int start, int[][] dist, HashMap<Integer, ArrayList<Integer>> graph) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int next : graph.get(cur)) {
                int newDist = dist[start][cur] + 1;
                if (newDist < dist[start][next]) {
                    dist[start][next] = newDist;
                    queue.offer(next);
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(solution(5, new int[][]{
            {0, 1}, {0, 2}, {1, 3}, {1, 4}
        }));
        System.out.println(solution(4, new int[][]{
            {2, 3}, {0, 1}, {1, 2}
        }));
    }
}

// 다익스트라로 최단경로만 구하면 되는 문제