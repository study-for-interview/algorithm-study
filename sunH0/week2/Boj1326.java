package com.example.codingtest.baekjoon.bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1326 {

	static int[] arr;
	static int N;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		arr = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int d = 1; d < N+1; d++) {
			arr[d] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());

		bfs(start, end);
	}

	static void bfs(int start, int end) {
		Queue<Flog> q = new LinkedList<>();
		q.offer(new Flog(start, 0));
		visited = new boolean[N+1];
		visited[start] = true;

		while (!q.isEmpty()) {
			Flog flog = q.poll();
			int now = flog.node;
			if (now == end) {
				System.out.println(flog.cnt);
				return;
			}
			int power = arr[now];

			for (int d = 1; now + power * d <= N; d++) {
				int jump = now + power * d;
				if (!visited[jump]) {
					visited[jump] = true;
					q.offer(new Flog(jump, flog.cnt + 1));
				}
			}

			for (int d = 1; now - power * d >= 1; d++) {
				int jump = now - power * d;
				if (!visited[jump]) {
					visited[jump] = true;
					q.offer(new Flog(jump, flog.cnt + 1));
				}
			}
		}

	}

	static class Flog {
		int node;
		int cnt;

		private Flog(int node, int cnt) {
			this.node = node;
			this.cnt = cnt;
		}
	}

}



