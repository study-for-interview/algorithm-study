import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj17135 {

    static int N, M, D, ans = 0;
    static int[] pick, dy = {0, -1, 0}, dx = {-1, 0, 1};
    static int[][] map, tmp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        D = sc.nextInt();

        map = new int[N][M];
        tmp = new int[N][M];
        pick = new int[3];

        for(int i = 0; i < N; i++)
            for(int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
                tmp[i][j] = map[i][j];
            }

        play(0, 0);
        System.out.println(ans);
    }

    static void play(int idx, int cnt) {
        if(cnt == 3) {
            ans = Math.max(ans, game());
            return;
        }
        if(idx == M) return;
        pick[cnt] = idx;
        play(idx + 1, cnt + 1);
        play(idx + 1, cnt);
    }

    static int game() {
        int sum = 0;
        for(int i = 0; i < N; i++) map[i] = tmp[i].clone();
        for(int i = N - 1; i >= 0; i--) {
            for(int a = 0; a < 3; a++) {
                int cur = pick[a];
                if (map[i][cur] != 0) map[i][cur]++;
                else bfs(i, cur);
            }
            sum += count(i);
        }
        return sum;
    }

    static void bfs(int y, int x) {
        Queue<Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        visited[y][x] = true;
        q.add(new Point(x, y));
        int cnt = 1;
        while (!q.isEmpty()) {
            int s = q.size();
            if (cnt > D) return;
            for (int c = 0; c < s; c++) {
                Point p = q.poll();
                if (map[p.y][p.x] != 0) {
                    map[p.y][p.x]++;
                    return;
                }
                for (int d = 0; d < 3; d++) {
                    int ny = p.y + dy[d], nx = p.x + dx[d];
                    if (ny < 0 || nx < 0 || nx >= M || visited[ny][nx]) continue;
                    visited[ny][nx] = true;
                    q.add(new Point(nx, ny));
                }
            }
            cnt++;
        }
    }

    static int count(int y) {
        int sum = 0;
        for(int j = y; j >= 0; j--)
            for(int k = 0; k < M; k++)
                if(map[j][k] > 1) {
                    sum++;
                    map[j][k] = 0;
                }
        return sum;
    }
}