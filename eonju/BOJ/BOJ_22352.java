import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] beforeMap;
    static int[][] afterMap;

    static int[] moveX = {1, -1, 0, 0};
    static int[] moveY = {0, 0, 1, -1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        beforeMap = new int[N][M];
        afterMap = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                beforeMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                afterMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        start();
        if (check()) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }

    }

    public static void start() {
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if (beforeMap[y][x] != afterMap[y][x]) {
                    BFS(x, y, afterMap[y][x]);
                    return;
                }
            }
        }
    }

    public static boolean check() {
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if (beforeMap[y][x] != afterMap[y][x]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void BFS(int x, int y, int val) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x, y));

        boolean[][] visited = new boolean[N][M];
        visited[y][x] = true;

        int lastVal = beforeMap[y][x];
        beforeMap[y][x] = val;

        while (!q.isEmpty()) {
            Point cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = cur.x + moveX[i];
                int nextY = cur.y + moveY[i];

                if (nextX < 0 || nextX >= M || nextY < 0 || nextY >= N) {
                    continue;
                }
                if (!visited[nextY][nextX] && beforeMap[nextY][nextX] == lastVal) {
                    q.add(new Point(nextX, nextY));
                    visited[nextY][nextX] = true;
                    beforeMap[nextY][nextX] = val;
                }
            }
        }
    }
}

class Point {

    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
