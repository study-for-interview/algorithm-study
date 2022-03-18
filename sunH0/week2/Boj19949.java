package com.example.codingtest.baekjoon.Backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj19949 {

	static int[] answer = new int[10];
	static int[] choice = new int[10];
	static int result=0;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int d = 0; d < 10; d++) {
			answer[d] = Integer.parseInt(st.nextToken());
		}

		dfs(0);
		System.out.print(result);
	}

	static void dfs(int depth) {
		int cnt=0;
		if (depth == 10) {
			for (int d = 0; d < 10; d++) {
				if (answer[d] == choice[d]) {
					cnt++;
				}
			}
			if (cnt >= 5) {
				result++;
			}
			return;
		}

		for (int d = 1; d < 6; d++) {
			if (depth>1&& d == choice[depth - 1] && d == choice[depth - 2]) {
				continue;
			}
			choice[depth] = d;
			dfs(depth + 1);
			choice[depth] = 0;
		}
	}

}
