package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/2589 bfs 보물섬
 */
public class BOJ_2589 {

    private static final int[] dx = {-1, 0, 1, 0};
    private static final int[] dy = {0, 1, 0, -1};
    private static char[][] arr;
    private static boolean[][] visit;
    private static int[][] distance;

    private static ArrayList<Integer> result = new ArrayList<>();

    private static int n, m;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new char[n][m];
        visit = new boolean[n][m];
        distance = new int[n][m];

        for(int i=0; i<n; i++) {
            char[] tmp = br.readLine().toCharArray();
            if (m >= 0)
                System.arraycopy(tmp, 0, arr[i], 0, m);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 'L') {
                    bfs(new Dot(i, j));

                    //끝나고 초기화
                    for (int k = 0; k < n; k++) {
                        Arrays.fill(visit[k], false);
                        Arrays.fill(distance[k], 0);
                    }
                }
            }
        }

        System.out.println(Collections.max(result));
    }

    private static void bfs(Dot dot) {
        Queue<Dot> q = new LinkedList<>();
        q.add(dot);
        visit[dot.x][dot.y] = true;

        while (!q.isEmpty()) {
            Dot d = q.poll();
            int x = d.x;
            int y = d.y;

            for (int i = 0; i < 4; i++) {
                int x2 = x + dx[i];
                int y2 = y + dy[i];

                if (x2 >= 0 && x2 < n && y2 >= 0 && y2 < m
                    && arr[x2][y2] == 'L' && !visit[x2][y2]) {
                    q.add(new Dot(x2, y2));
                    visit[x2][y2] = true;
                    distance[x2][y2] = distance[x][y] + 1;
                }
            }
        }

        result.add(findMax(distance));
    }

    private static int findMax(int[][] arr) {
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (max <= arr[i][j]) {
                    max = arr[i][j];
                }
            }
        }

        return max;
    }

    static class Dot {

        int x;
        int y;

        public Dot(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


}
