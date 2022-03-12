package 그래프.탐색.S2_DFS와BFS;

import java.io.*;
import java.util.*;

public class Main {

    static int n; // 정점 개수
    static int m; // 간선 개수
    static int v; // 시작 정점 번호
    static int[][] matrix; // 인접행렬
    static int[] visited; // 방문 정점 체크

    public static void dfs(int v) {
        visited[v - 1] = 1;
        System.out.print(String.format("%d ", v));

        for (int i = 0; i < n; i++) {
            if (visited[i] == 0 && matrix[v - 1][i] == 1) {
                dfs(i + 1);
            }
        }
    }

    public static void bfs(int v) {

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(v);
        visited[v - 1] = 1;

        while (!queue.isEmpty()) {

            int curV = queue.poll();
            System.out.print(String.format("%d ", curV));

            for (int i = 0; i < n; i++) {
                if (visited[i] == 0 && matrix[curV - 1][i] == 1) {
                    queue.offer(i + 1);
                    visited[i] = 1;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());

        // 인접 행렬 생성
        matrix = new int[n][n];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken()) - 1;
            int v2 = Integer.parseInt(st.nextToken()) - 1;
            matrix[v1][v2] = 1;
            matrix[v2][v1] = 1;
        }
        // 방문 리스트 초기화
        visited = new int[n];

        dfs(v);
        System.out.println();
        Arrays.fill(visited, 0);
        bfs(v);
    }
}

/**
 * 링크 : https://www.acmicpc.net/problem/1260
 * 성공여부 : X
 * 풀이시간 : 다시 공부하느라 2h
 * 
 * 시간복잡도 : dfs = O(n+m) / bfs - O(n^2)
 * 메모리 : 47628 KB
 * 소요 시간 : 780 ms
 * 
 * Arrays.fill() : 배열을 초기화시켜주는 메소드
 * 다음에 DFS 구현시 stack을 사용해보자. + 인접리스트도
 */