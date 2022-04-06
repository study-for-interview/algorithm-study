package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/20058 마법사 상어와 파이어스톰
 */
public class BOJ_20058 {

    static int n, q;
    static int[][] map;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static boolean[][] visit;
    static int iceLump;
    static int sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        n = (int) Math.pow(2, n);
        map = new int[n][n];
        visit = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < q; i++) {
            int l = Integer.parseInt(st.nextToken());
            moveArr(l);
            fireStorm();
        }

        int sum = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                sum += map[i][j];
            }
        }

        boolean visited[][] = new boolean[n][n];
        int max = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(!visited[i][j] && map[i][j] > 0) {
                    visited[i][j] = true;
                    max = Math.max(max, answer(i, j, visited));
                }
            }
        }

        bfs();


        System.out.println(sum);
        System.out.println(max);
    }

    private static void bfs() {
        // 가장 큰 덩어리를 iceLump에 저장

    }

    private static void fireStorm() {
        // 주변 칸 얼음 갯수 확인 후 조건에 따라 -1
        int[][] temp = new int[n][n];
        for (int i = 0; i < n; i++) {
            temp[i] = Arrays.copyOf(map[i], n);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int count = 0;
                if (map[i][j] == 0) {
                    continue;
                }

                for (int k = 0; k < 4; k++) {
                    int ny = i + dy[k];
                    int nx = j + dx[k];
                    if ((nx >= 0 && ny >= 0 && nx < n && ny < n) && map[ny][nx] > 0) {
                        count++;
                    }

                }
                if (count < 3) {
                    temp[i][j]--;
                }
            }
        }

        map = temp;
    }
    public static int answer(int x, int y, boolean visit[][]) {
        int count = 1;
        sum += map[x][y];

        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if((nx >= 0 && ny >= 0 && nx < n && ny < n) && map[nx][ny] > 0 && !visit[nx][ny]) {
                visit[nx][ny] = true;
                count += answer(nx, ny, visit);
            }
        }

        return count;
    }
    private static void moveArr(int l) {
        l = (int) Math.pow(2, l);

        int[][] temp = new int[n][n];
        for (int i = 0; i < map.length; i += l) {
            for (int j = 0; j < map[i].length; j += l) {
                for (int k = 0; k < l; k++) {
                    for (int m = 0; m < l; m++) {
                        temp[i + m][j + l - k - 1] = map[i + k][j + m];
                    }
                }
            }
        }

        map = temp;
    }
}
