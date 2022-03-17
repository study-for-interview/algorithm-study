import java.io.*;
import java.util.*;

class BJ_2240_dp {
    static int t, w;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Main T = new Main();
        StringTokenizer st = new StringTokenizer(br.readLine());
        t = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        dp = new int[t + 1][3][w + 1];
        int n = Integer.parseInt(br.readLine());

        if (n == 1) {
            dp[1][1][0] = 1;
            dp[1][2][1] = 0;
        }
        if (n == 2) {
            dp[1][1][0] = 0;
            dp[1][2][1] = 1;
        }

        for (int i = 2; i <= t; i++) {
            n = Integer.parseInt(br.readLine());

            if (n == 1) {
                dp[i][1][0] = dp[i - 1][1][0] + 1;
                dp[i][2][0] = dp[i - 1][2][0];


                for (int j = 1; j <= w; j++) {
                    dp[i][1][j] = Math.max(dp[i - 1][2][j - 1], dp[i - 1][1][j]) + 1;
                    dp[i][2][j] = Math.max(dp[i - 1][2][j], dp[i - 1][1][j - 1]);
                }
            } else if (n == 2) {
                dp[i][1][0] = dp[i - 1][1][0];
                dp[i][2][0] = dp[i - 1][2][0] + 1;

                for (int j = 1; j <= w; j++) {
                    dp[i][1][j] = Math.max(dp[i - 1][1][j], dp[i - 1][2][j - 1]);
                    dp[i][2][j] = Math.max(dp[i - 1][1][j - 1], dp[i - 1][2][j]) + 1;
                }
            }

        }

        int max = 0;
        for (int i = 0; i <= w; i++) {
            max = Math.max(max, Math.max(dp[t][1][i], dp[t][2][i]));
        }

        System.out.println(max);
    }
}