package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * https://www.acmicpc.net/problem/2688 줄어들지 않아
 */
public class BOJ_2688 {

    static long[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        dp = new long[65][10];

        preprocess();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            long sum = Arrays.stream(dp[num]).sum();
            sb.append(sum).append("\n");
        }

        System.out.print(sb);
    }

    private static void preprocess() {
        Arrays.fill(dp[1], 1);

        System.out.println(Arrays.toString(dp[1]));
        for (int i = 2; i < dp.length; i++) {
            for (int j = 0; j <= 9; j++) {
                for (int k = j; k <= 9; k++) {
                    dp[i][j] += dp[i - 1][k];
                }
            }
        }
    }

}
