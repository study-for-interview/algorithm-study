package BOJ;

import java.io.*;
import java.util.*;


public class Boj1967 {

    static class Node {
		int node, dist;

		public Node(int node, int dist) {
			this.node = node;
			this.dist = dist;
		}
	}

	static ArrayList<Node>[] list;
	static boolean[] visit;
	static int max = 0;
	static int N;
	static int max_idx = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		list = new ArrayList[N + 1];

		for (int i = 0; i <= N; i++)
			list[i] = new ArrayList<>();

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());

			int parent = Integer.parseInt(st.nextToken());
			int child = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			list[parent].add(new Node(child, weight));
			list[child].add(new Node(parent, weight));
		}

		visit = new boolean[N + 1];
		visit[1] = true;
		dfs(1, 0);

		visit = new boolean[N + 1];
		visit[max_idx] = true;
		dfs(max_idx, 0);
		bw.write(max + "\n");

	}

	public static void dfs(int idx, int dist) {
		if (max < dist) {
			max = dist;
			max_idx = idx;
		}

		for (Node n : list[idx]) {
			if (!visit[n.node]) {
				visit[n.node] = true;
				dfs(n.node, dist + n.dist);
			}
		}
	}
}
