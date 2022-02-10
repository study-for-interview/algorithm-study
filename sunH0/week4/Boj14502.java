package com.example.codingtest.baekjoon.week4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj14502 {

	static class Dot {
		int x, y;

		public Dot(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int n;
	static int m;
	static int[][] map;
	static int[][] copy;
	static List<Dot> virus = new ArrayList<Dot>();
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static int max = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		copy = new int[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2)
					virus.add(new Dot(i, j));
			}
		}

		setWall(0, 0);
		System.out.println(max);
	}

	static void setWall(int start, int depth) {
		if (depth == 3) {
			copyMap();

			for (Dot dot : virus) {
				spreadVirus(dot.x, dot.y);
			}

			max = Math.max(max, countSafeArea());
			return;
		}

		for (int i = start; i < n * m; i++) {
			int x = i / m;
			int y = i % m;

			if (map[x][y] == 0) {
				map[x][y] = 1;
				setWall(i + 1, depth + 1);
				map[x][y] = 0;
			}
		}
	}

	static void copyMap() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				copy[i][j] = map[i][j];
			}
		}
	}

	static void spreadVirus(int x, int y) {
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (0 <= nx && nx < n && 0 <= ny && ny < m) {
				if (copy[nx][ny] == 0) {
					copy[nx][ny] = 2;
					spreadVirus(nx, ny);
				}
			}
		}
	}

	static int countSafeArea() {
		int safe = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (copy[i][j] == 0)
					safe++;
			}
		}
		return safe;
	}
}
