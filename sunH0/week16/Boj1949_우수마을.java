package tree;

import java.util.*;

public class Boj1949_우수마을 {
    
    static ArrayList<ArrayList<Integer>> map = new ArrayList<>();
    static int n;
    static int[] p;
    static int[][] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        p = new int[n + 1];
        dp = new int[n + 1][2];

        for (int i = 0; i <= n; i++) {
            map.add(new ArrayList<Integer>());
        }

        for (int i = 1; i <= n; i++) {
            p[i] = sc.nextInt();
        }

        for (int i = 0; i < n - 1; i++) {
            int a, b;
            a = sc.nextInt();
            b = sc.nextInt();
            map.get(a).add(b);
            map.get(b).add(a);
        }

        dfs(1, 0);
        int ans = Math.max(dp[1][0], dp[1][1]);
        System.out.println(ans);
    }

    public static void dfs(int cur, int parent) {

        for (int child : map.get(cur)) {
            if (child != parent) {
                dfs(child, cur);
                dp[cur][1] += dp[child][0];
                dp[cur][0] += Math.max(dp[child][0], dp[child][1]);
            }
        }
        dp[cur][1] += p[cur];

    }
    
}

