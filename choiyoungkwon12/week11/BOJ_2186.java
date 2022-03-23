package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/2186 문자판
 */
public class BOJ_2186 {

    static int n, m, k;
    static char[][] map;
    static int[][][] dp;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int count = 0;
    static String keyword;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        keyword = br.readLine();
        dp = new int[n][m][keyword.length()];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int l = 0; l < keyword.length(); l++) {
                    dp[i][j][l] = -1;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                count += dfs(i, j, 0);
            }
        }

        System.out.println(count);
    }

    private static int dfs(int i, int j, int index) {
        if (index == keyword.length() - 1) {
            dp[i][j][index] = 1;
            return dp[i][j][index];
        }
        if (dp[i][j][index] != -1) {
            return dp[i][j][index];
        }
        if (map[i][j] != keyword.charAt(index)) {
            return dp[i][j][index] = 0;
        }

        dp[i][j][index] = 0;
        for (int d = 0; d < 4; d++) {
            for (int l = 1; l <= k; l++) {
                int nextX = j + dx[d] * l;
                int nextY = i + dy[d] * l;
                if (nextX >= 0 && nextY >= 0 && nextX < m && nextY < n && map[nextY][nextX] == keyword.charAt(
                    index + 1)) {
                    dp[i][j][index] += dfs(nextY, nextX, index + 1);
                }
            }
        }
        return dp[i][j][index];
    }
}
