package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/16234 인구이동
 */
public class BOJ_16234 {

    static int N, L, R;
    static int[][] map;
    static boolean[][] visited;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static Queue<Point> q;
    static List<Point> group;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        group = new LinkedList<>();
        q = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(process());

    }

    private static int process() {
        int cnt = 0;
        boolean isMove = false;

        while (true) {
            visited = new boolean[N][N];
            isMove = false;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {

                    if (visited[i][j]) {
                        continue;
                    }
                    if (open(i, j)) {
                        isMove = true;
                    }
                }
            }

            if (isMove) {
                cnt++;
            } else {
                return cnt;
            }
        }
    }

    private static boolean open(int r, int c) {

        q.clear();
        group.clear();

        q.add(new Point(r, c));
        group.add(new Point(r, c));
        visited[r][c] = true;

        int sum = map[r][c];

        while (!q.isEmpty()) {
            Point now = q.poll();

            for (int d = 0; d < 4; d++) {
                int nextR = now.r + dr[d];
                int nextC = now.c + dc[d];

                if (nextR < 0 || nextC < 0 || nextR >= N || nextC >= N || visited[nextR][nextC]) {
                    continue;
                }

                int diff = Math.abs(map[now.r][now.c] - map[nextR][nextC]);
                if (diff < L || diff > R) {
                    continue;
                }

                sum += map[nextR][nextC];
                q.add(new Point(nextR, nextC));
                group.add(new Point(nextR, nextC));
                visited[nextR][nextC] = true;
            }
        }

        if (group.size() == 1) {
            return false;
        } else {
            int temp = sum / group.size();
            for (Point point : group) {
                map[point.r][point.c] = temp;
            }

            return true;
        }
    }

    static class Point {

        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}

