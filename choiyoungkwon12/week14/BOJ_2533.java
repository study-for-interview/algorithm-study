package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/2533 사회망 서비스(SNS)
 */
public class BOJ_2533 {

    static int n;
    static int[][] dp;
    static ArrayList<ArrayList<Integer>> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        dp = new int[n + 1][2];
        StringTokenizer st;
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list.get(a).add(b);
            list.get(b).add(a);
        }
        dp(1, -1);

        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    private static void dp(int cur, int parent) {
        dp[cur][0] = 0;
        dp[cur][1] = 1;

        for (Integer integer : list.get(cur)) {
            if (integer != parent) {
                dp(integer, cur);
                dp[cur][0] += dp[integer][1];
                dp[cur][1] += Math.min(dp[integer][0], dp[integer][1]);
            }
        }
    }
}
