import java.io.*;
import java.util.*;

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class BJ_16234 {
    static int[][] arr;
    static boolean visited[][];
    static int n, L, R;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Queue<Point> Q;
    static List<Point> group;

    public static boolean BFS(int r, int c) {
        Q.clear();
        group.clear();

        Q.add(new Point(r, c));
        group.add(new Point(r, c));
        visited[r][c] = true;
        int sum = arr[r][c];

        while (!Q.isEmpty()) {
            Point point = Q.poll();
            int x = point.x;
            int y = point.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || ny < 0 || nx >= n || ny >= n || visited[nx][ny]) continue;
                int diff = Math.abs(arr[x][y] - arr[nx][ny]);
                if (diff < L || diff > R) continue;
                visited[nx][ny] = true;
                Q.add(new Point(nx, ny));
                group.add(new Point(nx, ny));
                sum += arr[nx][ny];


            }
        }

        // 연합국이 존재하지 않다면
        if (group.size() == 1) {
            return false;
        } else {
            int tmp = sum / group.size();
            for (Point p : group) {
                arr[p.x][p.y] = tmp;
            }

            return true;
        }
    }

    public static int solution() {
        int cnt = 0; // 인구 이동 발생 수
        boolean isMove = false;

        while (true) {

            visited = new boolean[n][n];
            isMove = false;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (visited[i][j]) continue;
                    if (BFS(i, j)) isMove = true;
                }
            }

            if (isMove) cnt++;
            else return cnt;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        arr = new int[n][n];
        group = new LinkedList<>();
        Q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solution());

        bw.flush();
        bw.close();
        br.close();
    }

}