import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Point {
    int x;
    int y;
    int d;
    int mask;

    public Point(int x, int y, int d, int mask) {
        this.x = x;
        this.y = y;
        this.d = d;
        this.mask = mask;
    }
}

public class Main {
    static int n,m;
    static boolean[][][] visited;
    static int[][] map;
    static Queue<Point> Q;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};


    public int bfs(int idx) {

        while (!Q.isEmpty()) {
            Point now = Q.poll();
            int x = now.x;
            int y = now.y;
            int mask = now.mask;
            int d = now.d;

            if (mask == (1 << idx) - 1) {
                return d;
            }
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                int nd = d+1;
                int nMask = now.mask;

                if(nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if(map[nx][ny] == -1) continue; // 벽이면 갈수 없다.
                if (map[nx][ny] > 0) { //쓰레기가 있는 칸이라면 마스크값 업데이트
                    nMask |= (1 << (map[nx][ny]-1));
                }
                if(visited[nx][ny][nMask]) continue;
                Q.add(new Point(nx, ny, nd, nMask));
                visited[nx][ny][nMask] = true;
            }
        }
        return -1;
    }


    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            map = new int[n][m];
            Q = new LinkedList<>();
            visited = new boolean[20][20][1<<11];

            if (n + m == 0) break;
            int idx = 0 ;
            for (int i = 0; i < n; i++) {
                String s = br.readLine();
                for (int j = 0; j < m; j++) {
                    char now = s.charAt(j);
                    if(now == '.') map[i][j] = 0;
                    else if (now == '*') {
                        map[i][j] = ++idx;
                    } else if (now == 'x') {
                        map[i][j] = -1;
                    } else {
                        Q.add(new Point(i, j,0,0));
                    }

                }
            }
            System.out.println(T.bfs(idx));
        }

    }
}