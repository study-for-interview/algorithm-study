package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1240
 * 노드 사이의 거리
 */
public class BOJ_1240 {

    static int m, n;
    static int[][] map;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n + 1][n + 1];
        visited = new boolean[n + 1];
        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int point1 = Integer.parseInt(st.nextToken());
            int point2 = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());
            map[point1][point2] = distance;
            map[point2][point1] = distance;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int sum = 0;
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            visited[start] = true;
            dfs(start, end, sum);
            visited[start] = false;
        }
    }

    private static void dfs(int start, int end, int sum) {
        if (map[start][end] != 0) {
            sum += map[start][end];
            System.out.println(sum);
            return;
        }

        for (int j = 1; j < map[start].length; j++) {
            int i1 = map[start][j];
            if (i1 != 0 && !visited[j]) {
                visited[j] = true;
                dfs(j, end, sum + i1);
                visited[j] = false;
            }
        }
    }
}
