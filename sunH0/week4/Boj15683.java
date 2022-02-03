package com.example.codingtest.baekjoon.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj15683 {
	static int N, M, ans, cnt;
	static CCTV[] cctvs;
	static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
	static int[][][] dir = {
		{{0}},
		{{1}, {2}, {3}, {0}},
		{{1, 3}, {0, 2}},
		{{0, 1}, {1, 2}, {2, 3}, {3, 0}},
		{{0, 1, 3}, {0, 1, 2}, {1, 2, 3}, {2, 3, 0}},
		{{0, 1, 2, 3}},
	};

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		cctvs = new CCTV[8];

		int remain = N * M;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				if(map[i][j] >= 1 && map[i][j] <= 5) {
					cctvs[cnt++] = new CCTV(map[i][j], i, j);
				}
				else if(map[i][j] == 6) remain--;
			}
		}

		ans = Integer.MAX_VALUE;

		dfs(0, remain - cnt, map);

		System.out.println(ans);
	}

	private static void dfs(int idx, int remain, int[][] map) {

		if(idx == cnt) {
			ans = Math.min(ans, remain);

			return;
		}

		int[][] newMap = new int[N][M];
		copy(newMap, map);

		CCTV cc = cctvs[idx];

		for (int j = 0; j < dir[cc.type].length; j++) {
			int check = 0;
			for (int k = 0; k < dir[cc.type][j].length; k++) {
				int d = dir[cc.type][j][k];
				check += observation(cc.x, cc.y, d, newMap);
			}

			dfs(idx + 1, remain - check, newMap);
			copy(newMap, map);
		}

	}

	private static int observation(int r, int c, int d, int[][] map) {

		int cnt = 0;

		while(true) {

			r += dx[d];
			c += dy[d];

			if(r < 0 || r >= N || c < 0 || c >= M || map[r][c] == 6) return cnt;

			if((map[r][c] >= 1 && map[r][c] <= 5) || map[r][c] == -1) continue;

			map[r][c] = -1;
			cnt++;
		}

	}

	private static void copy(int[][] newMap, int[][] map) {

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				newMap[i][j] = map[i][j];
			}
		}

	}

	static class CCTV {
		int type, x, y;

		public CCTV(int type, int x, int y) {
			this.type = type;
			this.x = x;
			this.y = y;
		}

	}

}


