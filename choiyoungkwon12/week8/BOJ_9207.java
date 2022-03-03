package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * https://www.acmicpc.net/problem/9207 페그 솔리테어
 */
public class BOJ_9207 {

    static char[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int minPin, move;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int tc = 0; tc < n; tc++) {
            map = new char[5][9];
            int pin = 0;
            minPin = 0;
            move = 0;

            for (int i = 0; i < 5; i++) {
                String s = br.readLine();

                for (int j = 0; j < 9; j++) {
                    map[i][j] = s.charAt(j);
                    if (map[i][j] == 'o') {
                        pin++;
                    }
                }
            }

            minPin = pin;

            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 9; j++) {
                    if (map[i][j] == 'o') {
                        dfs(i, j, pin, 0);//현재위치와 pin,move
                    }
                }
            }

            br.readLine();

            sb.append(minPin).append(" ").append(move).append("\n");
        } // end of for

        System.out.println(sb);
    }

    private static void dfs(int x, int y, int pin, int m) {
        if (pin <= minPin) {
            minPin = pin;
            move = m;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 인접한 곳에 핀이 있어야 함.
            if (isValid(nx, ny) && map[nx][ny] == 'o') {

                // 건너편은 빈 공간이어야 함.
                int nx2 = nx + dx[i];
                int ny2 = ny + dy[i];
                if (isValid(nx2, ny2) && map[nx2][ny2] == '.') {
                    map[x][y] = '.';
                    map[nx][ny] = '.';
                    map[nx2][ny2] = 'o';

                    for (int j = 0; j < 5; j++) {
                        for (int k = 0; k < 9; k++) {
                            if (map[j][k] == 'o') {
                                dfs(j, k, pin - 1, m + 1);
                            }
                        }
                    }

                    map[x][y] = 'o';
                    map[nx][ny] = 'o';
                    map[nx2][ny2] = '.';
                }
            } // end of if

        }
    } // end of dfs

    private static boolean isValid(int nx, int ny) {
        return nx >= 0 && nx < 5 && ny >= 0 && ny < 9;
    }
}
