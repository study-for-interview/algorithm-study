package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/4485 녹색 옷 입은 애가 젤다지?
 */
public class BOJ_4485 {

    static int N;
    static int[] dx = new int[]{1, -1, 0, 0};
    static int[] dy = new int[]{0, 0, 1, -1};
    static int[][] map;
    static int[][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int cnt = 0;
        while (N != 0) {
            cnt++;
            map = new int[N][N];
            dist = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }

            int result = dijkstra();
            sb.append("Problem ").append(cnt).append(": ").append(result).append("\n");
            N = Integer.parseInt(br.readLine());
        }

        System.out.println(sb);
    }

    private static int dijkstra() {
        PriorityQueue<Point> pq = new PriorityQueue<>();
        pq.add(new Point(0, 0, map[0][0]));
        dist[0][0] = map[0][0];
        while (!pq.isEmpty()) {
            Point p = pq.poll();

            for (int i = 0; i < 4; i++) {
                int nextCol = dx[i] + p.col;
                int nextRow = dy[i] + p.row;

                if (nextCol < N && nextCol >= 0 && nextRow < N && nextRow >= 0) {
                    if (dist[nextRow][nextCol] > dist[p.row][p.col] + map[nextRow][nextCol]) {
                        dist[nextRow][nextCol] = dist[p.row][p.col] + map[nextRow][nextCol];
                        pq.offer(new Point(nextRow, nextCol, dist[nextRow][nextCol]));
                    }
                }
            }
        }
        return dist[N - 1][N - 1];
    }

    static class Point implements Comparable<Point> {

        int row;
        int col;
        int cost;

        public Point(int row, int col, int cost) {
            this.row = row;
            this.col = col;
            this.cost = cost;
        }

        @Override
        public int compareTo(Point p) {
            return this.cost - p.cost;
        }
    }
}
