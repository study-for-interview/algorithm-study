package com.example.codingtest.baekjoon.bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj2589 {

	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static char[][] board;
	static boolean[][] visited;
	static int N;
	static int M;

	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		board = new char[N][M];

		for (int x = 0; x < N; x++) {
			String str = br.readLine();
			for (int y = 0; y < M; y++) {
				board[x][y] = str.charAt(y);
			}
		}

		int result = 0;
		int val = 0;
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < M; y++) {
				if (board[x][y] == 'L') {
					val = bfs(x, y);
				}
				result = Math.max(result, val);
			}
		}
		System.out.println(result);
	}

	private static int bfs(int x, int y) {
		Queue<Node> q = new LinkedList<>();
		visited = new boolean[N][M];
		q.offer(new Node(x, y, 0));
		visited[x][y] = true;

		int val = 0;

		while (!q.isEmpty()) {
			Node node = q.poll();
			for (int d = 0; d < 4; d++) {
				int nx = node.x + dx[d];
				int ny = node.y + dy[d];
				if (nx >= 0 && nx < N && ny >= 0 && ny < M && board[nx][ny] == 'L' && visited[nx][ny] == false) {
					q.offer(new Node(nx, ny, node.cnt + 1));
					visited[nx][ny] = true;
					val = node.cnt + 1;
				}
			}
		}
		return val;
	}

	static class Node {

		int x;
		int y;
		int cnt;

		public Node(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}

	}

}
