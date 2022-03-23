package com.example.codingtest.baekjoon.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj14503 {

	static int x,y, cnt, r,c,d;
	static int map[][];
	static int dx[] = {0,1,0,-1};
	static int dy[] = {-1,0,1,0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		y = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());

		map = new int[y][x];
		st = new StringTokenizer(br.readLine());

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		cnt = 1;

		for(int i=0; i<y; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<x; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(r,c,d);
		System.out.println(cnt);
	}

	public static void dfs(int r, int c, int dir) {
		map[r][c] = -1;

		for(int i=0; i<4; i++) {
			dir = (dir + 3) % 4;
			int ny = r + dy[dir];
			int nx = c + dx[dir];

			if(ny >=0 && ny < y && nx >= 0 && nx < x && map[ny][nx] == 0) {
				cnt++;
				dfs(ny,nx, dir);

				return;
			}
		}

		int back = (dir + 2) % 4;
		int by = r + dy[back];
		int bx = c + dx[back];
		if(by>=0 && by<y && bx>=0 && bx<x && map[by][bx] != 1) {
			dfs(by,bx,dir);
		}
	}
}

