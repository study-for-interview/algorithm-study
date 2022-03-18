package DP;

import java.io.*;
import java.util.*;

class Boj2240_자두나무 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int[] tree = new int[T + 1];

        for (int i = 1; i <= T; i++) {
            tree[i] = Integer.parseInt(br.readLine());
        }
 
        int[][][] dp = new int[3][T + 1][C + 2];
 
        for (int i = 1; i <= T; i++) {
            for (int j = 1; j <= C + 1; j++) {
                if (tree[i] == 1) {
                    dp[1][i][j] = Math.max(dp[1][i - 1][j], dp[2][i - 1][j - 1]) + 1;
                    dp[2][i][j] = Math.max(dp[2][i - 1][j], dp[1][i - 1][j - 1]);
                } else {
                    if (i == 1 && j == 1) continue;
                    dp[1][i][j] = Math.max(dp[1][i - 1][j], dp[2][i - 1][j - 1]);
                    dp[2][i][j] = Math.max(dp[2][i - 1][j], dp[1][i - 1][j - 1]) + 1;
                }
            }
        }
 
        int result = 0;

        for (int i = 1; i <= C + 1; i++) {
            result = Math.max(result, Math.max(dp[1][T][i], dp[2][T][i]));
        }
 
        System.out.println(result);
    }

}


// DP가 떠오르지 않음, 점화식도 생각을 못함
