package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/2662 기업투자
 */
public class BOJ_2662 {

    static int[][] info, invest, dp;
    static int[] path;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        info = new int[N + 1][M + 1];
        invest = new int[N + 1][M + 1];
        dp = new int[N + 1][M + 1];
        path = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            for (int j = 1; j <= M; j++) {
                info[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        findMaxBenefit();
        getPath(N,M);

        StringBuilder sb = new StringBuilder();

        sb.append(dp[N][M]).append("\n");
        for (int i = 1; i <= M; i++) {
            sb.append(path[i]).append(" ");
        }

        System.out.println(sb);

        br.close();
    }

    public static void findMaxBenefit() {
        for (int i = 1; i <= M; i++) {
            for (int j = 0; j <= N; j++) {
                for (int k = N - j; k >= 0; k--) {
                    if (dp[k][i - 1] + info[j][i] > dp[j + k][i]) {
                        dp[j + k][i] = dp[k][i - 1] + info[j][i];
                        invest[j + k][i] = j;
                    }
                }
            }
        }
    }

    public static void getPath(int n, int m) {
        if (m == 0) {
            return;
        }
        path[m] = invest[n][m];
        getPath(n - path[m], m - 1);
    }

}
