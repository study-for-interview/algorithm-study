package BOJ;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1445 일요일 아침의 데이트
 */

public class BOJ_1445 {

    private static final int[] dr = {1, -1, 0, 0};
    private static final int[] dc = {0, 0, 1, -1};
    private static List<Point> points;
    private static int n, m;
    private static char[][] map;
    private static Node start;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        boolean[][] visited = new boolean[n][m];

        points = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == 'S') {
                    start = new Node(i, j, 0, 0);
                }
                if (map[i][j] == 'g') {
                    points.add(new Point(i, j));
                }
            }
        }

        checkedAround();
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(start);
        visited[start.x][start.y] = true;
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            for (int i = 0; i < 4; i++) {
                int row = now.x + dr[i];
                int col = now.y + dc[i];
                int g = now.g;
                int b = now.b;
                if (row < 0 || col < 0 || row >= n || col >= m) {
                    continue;
                }
                if (visited[row][col]) {
                    continue;
                }
                if (map[row][col] == 'F') {
                    System.out.println(g + " " + b);
                    return;
                }
                if (map[row][col] == 'g') {
                    g++;
                }
                if (map[row][col] == 'b') {
                    b++;
                }
                visited[row][col] = true;
                pq.offer(new Node(row, col, g, b));
            }

        }
        System.out.println(0 + " " + 0);

    }

    private static void checkedAround() {
        for (Point point : points) {
            for (int d = 0; d < 4; d++) {
                int row = point.x + dr[d];
                int col = point.y + dc[d];
                if (row < 0 || col < 0 || row >= n || col >= m) {
                    continue;
                }
                if (map[row][col] == '.') {
                    map[row][col] = 'b';
                }
            }
        }
    }

    static class Node implements Comparable<Node> {

        int x;
        int y;
        int g;
        int b;

        public Node(int x, int y, int g, int b) {
            this.x = x;
            this.y = y;
            this.g = g;
            this.b = b;
        }

        @Override
        public int compareTo(Node o) {
            if (this.g == o.g) {
                return this.b - o.b;
            }

            return this.g - o.g;
        }

    }
}
