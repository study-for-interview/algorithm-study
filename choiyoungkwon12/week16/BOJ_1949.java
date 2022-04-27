package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1949 우수 마을
 */
public class BOJ_1949 {

    static int[] person;
    static int[][] dp;
    static List<Integer>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        person = new int[n + 1];
        list = new List[n + 1];
        dp = new int[n + 1][2];

        for (int i = 0; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 1; i <= n; i++) {
            person[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].add(b);
            list[b].add(a);
        }
        dfs(1, 0);

        System.out.println(Math.max(dp[1][0], dp[1][1]));
    }

    private static void dfs(int n, int parent) {
        for (int node : list[n]) {
            if (node != parent) {
                dfs(node, n);
                dp[n][0] += Math.max(dp[node][0], dp[node][1]);
                dp[n][1] += dp[node][0];
            }
        }
        dp[n][1] += person[n];
    }
}
