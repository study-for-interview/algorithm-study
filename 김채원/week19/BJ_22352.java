import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {

    static int N,M;
    static int[][] start;
    static int[][] end;
    static boolean[][] ch;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int startVal,endValue,cnt;
    static boolean isAnswer;

    static void bfs(int x, int y) {
        Queue<Point> Q = new LinkedList<>();
        Q.add(new Point(x, y));
        ch[x][y] = true;

        while (!Q.isEmpty()) {
            Point now = Q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if(ch[nx][ny]) continue;
                if(startVal != start[nx][ny]) continue;
                if(endValue != end[nx][ny]) isAnswer = false;
                ch[nx][ny] = true;
                Q.add(new Point(nx, ny));
            }
        }
    }

    public String solution() {
        cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!ch[i][j]) {
                    startVal = start[i][j];
                    endValue = end[i][j];
                    if(startVal != endValue) cnt++;
                    bfs(i,j);
                }
            }
        }

        if(!isAnswer) return "NO";
        if(cnt <= 1) return "YES";
        else return "NO";
    }

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        start = new int[N][M];
        end = new int[N][M];
        ch = new boolean[N][M];
        isAnswer = true;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                start[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                end[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        System.out.println(T.solution());

    }
}