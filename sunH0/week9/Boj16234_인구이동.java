import java.io.*;
import java.util.*;

class Node {
	int x;
	int y;

	Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Boj16234_인구이동 {
	public static int[][] map;
	public static boolean[][] visited;
	public static int[] dx = {-1, 0, 1, 0};
	public static int[] dy = {0, -1, 0, 1};
	public static int N, L, R;
	public static int ans = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken()); 
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken()); 

		map = new int[N][N];

		for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		while(true) {	
			boolean isMove = false;

			visited = new boolean[N][N];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(!visited[i][j]) { 
						if(bfs(i, j))
							isMove = true;
					}

				}
			}

			if(!isMove) {
				break;
			} else {
				ans++;
			}
		}

		System.out.println(ans);
	}

	public static boolean bfs(int x, int y) {
		boolean isUnion = false; 
		ArrayList<Node> unionList = new ArrayList<>(); 

		unionList.add(new Node(x, y));
		int unionCnt = 1; 
		int sum = map[x][y];

		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(x, y));
		visited[x][y] = true;

		while(!queue.isEmpty()) {
            Node curNode = queue.poll();
			int cX = curNode.x;
			int cY = curNode.y;

			for(int i = 0; i < 4; i++) { 
				int nx = cX + dx[i];
				int ny = cY + dy[i];

				if(nx < 0 || ny < 0 || nx >= N || ny >= N) 
					continue;

				if(visited[nx][ny]) 
					continue;

				int val = Math.abs(map[cX][cY] - map[nx][ny]);

				if(val >= L && val <= R && !visited[nx][ny]) {
					unionList.add(new Node(nx, ny));
					unionCnt++;
					sum += map[nx][ny];
					
					visited[nx][ny] = true;
					queue.add(new Node(nx, ny));
				}
			}
		}
		
		if(unionList.size() > 1) { 
			isUnion = true; 
			int p = sum / unionCnt;
			
			for(int i = 0; i < unionList.size(); i++) { 
				Node node = unionList.get(i);
				map[node.x][node.y] = p;
			}
		}
		
		return isUnion;
	}
}