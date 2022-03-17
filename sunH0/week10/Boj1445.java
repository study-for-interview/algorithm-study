package dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj1445 {
	static char[][] map;
	static int N, M;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static Node start;
	static Node end;
	static boolean[][] visited;
	
	static class Node{
		int x;
		int y;
		int cntG;
		int cntN;
		
		public Node(int x, int y, int cntG, int cntN) {
			this.x = x;
			this.y = y;
			this.cntG = cntG;
			this.cntN = cntN;
		}
	}
	
	static void checkN(int i, int j) {
		for(int d=0; d<4; d++) {
			int nx = dx[d] + i;
			int ny = dy[d] + j;
			
			if(nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny] == 'S' || map[nx][ny] == 'F' || map[nx][ny] == 'g') continue;
			
			map[nx][ny] = 'n';
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		visited = new boolean[N][M];

		for(int i=0; i<N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 'g') {
					checkN(i, j);
				}
				else if(map[i][j] == 'S') {
					start = new Node(i,j,0,0);
				}
				else if(map[i][j] == 'F') {
					end = new Node(i,j,0,0);
				}
			}
		}
		
		PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> {
			if(a.cntG == b.cntG) {
				return a.cntN - b.cntN;
			}
			
			return a.cntG - b.cntG;
		});
		
		pq.offer(start);
		visited[start.x][start.y] = true;
		
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			
			if(node.x == end.x && node.y == end.y) {
				System.out.println(node.cntG + " " +  node.cntN);
				break;
			}
			
			for(int i=0; i<4; i++) {
				int nx = node.x + dx[i];
				int ny = node.y + dy[i];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny]) continue;
				
				int cntG = node.cntG;
				int cntN = node.cntN;

				if(map[nx][ny] == 'g') cntG++;
				else if(map[nx][ny] == 'n') cntN++;
				
				pq.offer(new Node(nx, ny, cntG, cntN));

				visited[nx][ny] = true;
				
			}
		}
		
		
	}
}
