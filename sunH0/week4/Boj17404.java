package com.example.codingtest.baekjoon.week4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj17404 {

	static int N, max, ans;
	static int[][] arr, dp;
	static StringTokenizer str;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		arr = new int[N][3];
		dp = new int[N][3];

		for(int i = 0; i< N; i++) {
			str = new StringTokenizer(br.readLine());
			for(int j = 0; j<3; j++) {
				arr[i][j] = Integer.parseInt(str.nextToken());
			}
		}
		max = 1000 * N + 1;
		ans = 1000001;
		for(int k = 0; k<3; k++) {
			for(int i = 0; i<3; i++) {
				if(i==k) dp[0][i] = arr[0][i];
				else dp[0][i] = max;
			}
			for(int i = 1; i< N; i++) {
				dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + arr[i][0];
				dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + arr[i][1];
				dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + arr[i][2];
			}
			for(int i = 0; i<3; i++) {
				if(i==k) continue;
				ans = Math.min(ans, dp[N -1][i]);
			}
		}
		System.out.println(ans);
	}
}

