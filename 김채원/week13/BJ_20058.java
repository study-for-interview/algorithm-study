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
    static int n, q, maxIce;
    static int[] rotationSize;
    static int[][] map;
    static int[][] copy_map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] ch;

    static void fireStorm(int l) {
        //배열 돌리기
        for (int i = 0; i < n; i += l) {
            for (int j = 0; j < n; j += l) {
                rotationArray(i, j, l);
            }
        }
        copy();

        //얼음 녹이기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int cnt = 0;
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                    if(map[nx][ny] > 0) cnt++;
                }

                if (map[i][j] == 0) {
                    copy_map[i][j] = 0;
                    continue;
                }
                if(cnt < 3 ) copy_map[i][j] = map[i][j]-1;
                else copy_map[i][j] = map[i][j];
            }
        }
        copy();
        //  printMap();

    }


    static void dfs(int r, int c) {
        Queue<Point> Q = new LinkedList<>();
        Q.add(new Point(r, c));
        int cnt = 0;

        while (!Q.isEmpty()) {
            Point nowQ = Q.poll();
            int x = nowQ.x;
            int y = nowQ.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                if (ch[nx][ny] || map[nx][ny] == 0) continue;
                ch[nx][ny] = true;
                Q.add(new Point(nx, ny));
                cnt++;

            }
        }

        maxIce = Math.max(maxIce,cnt);
    }

    static void copy() {
        for (int i = 0; i <n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = copy_map[i][j];
            }
        }
    }

    static void rotationArray(int x, int y, int l) {
        for (int i = x, b = y; i < x + l; i++, b++) {
            for (int j = y, a = x + l - 1; j < y + l; j++, a--) {
                copy_map[i][j] = map[a][b];
            }
        }
    }



    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n =  Integer.parseInt(st.nextToken());
        n = 1 << n;
        q = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        copy_map = new int[n][n];
        rotationSize = new int[q];
        maxIce = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
//        for (int i = 0; i < 1; i++) {
//            rotationSize[i] =  Integer.parseInt(st.nextToken());
//            rotationSize[i] = 1 << rotationSize[i];
//        }

        for (int i = 0; i < rotationSize.length; i++) {
            //fireStorm(rotationSize[i]);
            int aaa= 1 << Integer.parseInt(st.nextToken());
            fireStorm(aaa);
        }

        ch = new boolean[n][n];
        int sum = 0 ;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                //   if(map[i][j] == 0) ch[i][j] = true;
                sum += map[i][j];
                if(!ch[i][j]) dfs(i,j);
            }
        }

        System.out.println(sum);
        System.out.println(maxIce);

        for (int i = 0, y = n - 1; i < n; i++, y--) {
            for (int j = 0, x = n - 1; j < n; j++, x--) {
                copy_map[i][j] = map[x][y];
            }
        }
    }
}