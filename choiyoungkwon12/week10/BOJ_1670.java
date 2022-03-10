package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * https://www.acmicpc.net/problem/1670 정상 회담2
 */

public class BOJ_1670 {

    static int M = 987654321;
    static long[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        dp = new long[10001];

        dp[0] = 1;
        dp[2] = 1;

        for (int i = 4; i <= n; i += 2) {
            for (int j = 0; j <= i - 2; j += 2) {
                dp[i] += dp[j] * dp[i - j - 2];
                dp[i] %= M;
            }
        }

        System.out.println(dp[n]);
    }
}
