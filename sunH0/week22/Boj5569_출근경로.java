package DP;

import java.io.*;
import java.util.*;

public class Boj5569_출근경로 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

        int w = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());
        int[][][] dp = new int[w+1][h+1][4];

        // 0 : 위위, 1: 위오, 2: 오오, 3:오위
        // Bottom-Up

        for(int i=1; i<=w; i++){
            dp[i][1][0] = 1;
        }

        for(int i=1; i<=h; i++){
            dp[1][i][2] = 1;
        }

        int mod = 100000;

        for(int i = 2; i <= w; i++) {
            for(int j = 2; j <= h; j++) {
                dp[i][j][0] = (dp[i-1][j][0]+ dp[i-1][j][3])% mod;
                dp[i][j][1] = dp[i][j-1][0]% mod;
                dp[i][j][2] = (dp[i][j-1][1]+ dp[i][j-1][2])% mod;
                dp[i][j][3] = dp[i-1][j][2]% mod;
            }
        }

        int ans = 0;
        for (int i = 0; i < 4; i++) {
            ans += dp[w][h][i];
        }

        System.out.println(ans%mod);
    }
    
}
