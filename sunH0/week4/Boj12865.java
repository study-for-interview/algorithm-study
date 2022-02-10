package com.example.codingtest.baekjoon.week4;

import java.util.*;

public class Boj12865 {
	static int N, M;
	static int dp[][];
	static int w[],v[];


	public static void main(String[] args)   {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		dp = new int[N +1][M +1];

		w =new int[N +1];
		v = new int[N +1];

		for(int i=1;i<= N;i++) {
			w[i] = sc.nextInt();
			v[i]= sc.nextInt();

		}

		for(int i=1;i<= N;i++) {
			for(int j=1;j<= M;j++) {
				dp[i][j] = dp[i-1][j];
				if(j - w[i]>=0) {
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-w[i]]+v[i]);
				}
			}
		}

		System.out.println(dp[N][M]);

	}

}
