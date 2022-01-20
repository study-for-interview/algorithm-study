package com.example.codingtest.baekjoon.Backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj18429 {

	static boolean visited[];
	static int N;
	static int K;
	static int[] arr;
	static int ans;

	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		arr = new int[N];
		visited = new boolean[N];
		st = new StringTokenizer(br.readLine());

		for (int d = 0; d < N; d++) {
			arr[d] = Integer.parseInt(st.nextToken());
		}

		dfs(0,500);
		System.out.println(ans);
	}

	static void dfs(int day, int weight) {
		if (day == N) {
			ans++;
			return;
		}
		for (int d = 0; d < N; d++) {
			int now = weight - K + arr[d];
			if (visited[d] == false && now >= 500) {
				visited[d] = true;
				dfs(day + 1, now);
				visited[d] = false;
			}
		}
	}

}
