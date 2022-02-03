import java.io.*;
import java.util.*;

class dot {
    int x;
    int y;

    public dot(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class BJ_14502 {
    static int n;
    static int m;
    static int dy[] = {1,-1,0,0};
    static int dx[] = {0,0,-1,1};
    static int map[][], copy_map[][];
    static int answer;
    static Queue<dot> Q;

    public static void makeWall(int L, int now) {
        if (L == 3) {
            copy();
            spreadVirus();
            count_safe_area();
            return;
        }

        for(int i=now+1; i<n*m; i++){
            int cy = i/m;
            int cx = i%m;
            if(map[cy][cx]!=0) continue;
            map[cy][cx] = 1;
            makeWall(L+1, i);
            map[cy][cx] = 0 ;
        }
    }

    public static void spreadVirus() {
        while (!Q.isEmpty()) {
            dot poll_dot = Q.poll();
            for (int i = 0 ;i < 4; i++) {
                int nx = poll_dot.x + dx[i];
                int ny = poll_dot.y + dy[i];
                if (nx >= 0 && ny >= 0 && nx <n && ny < m && copy_map[nx][ny] == 0) {
                    copy_map[nx][ny] = 2;
                    Q.offer(new dot(nx, ny));
                }
            }
        }
    }

    public static void copy() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                copy_map[i][j] = map[i][j];
                if (map[i][j] == 2) {
                    Q.offer(new dot(i, j));
                }
            }
        }
    }

    public static void count_safe_area() {
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for(int j = 0 ; j < m ; j++){
                if(copy_map[i][j]==0) cnt++;
            }
        }

        answer = Math.max(answer, cnt);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        copy_map = new int[n][m];
        answer = 0;
        Q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < m; j++) {
                map[i][j] = Integer.parseInt(st2.nextToken());
            }
        }

        makeWall(0,-1);
        System.out.println(answer);

        bw.flush();
        bw.close();
        br.close();
    }

}