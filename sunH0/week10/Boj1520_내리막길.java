package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1520_내리막길 {
    
	static int M, N;
    static boolean[][] visited;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	
	static int[][] map,dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[M][N];
		
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dp = new int[M][N];

        visited = new boolean[M][N];
		
		System.out.println(dfs(0,0));
		
	}
	
	private static int dfs(int i, int j) {
		
		if(i == M-1 && j == N-1) {
			return 1;
		}
		
		if(visited[i][j]) {
			return dp[i][j];
		}

		visited[i][j] = true;
		
		for(int k=0; k<4; k++) {
			int nx = i + dx[k];
			int ny = j + dy[k];

			if (nx < 1 || ny < 1 || nx > M || ny > N) {
				continue;
			}
			
			if(map[i][j] > map[nx][ny]) {
				
				dp[i][j] += dfs(nx, ny);
			}
		}
		return dp[i][j];

		
	}

}


// 처음에는 dfs 로 풀이 ->  시간초과 -> 4^(500*500) -> dp 메모리제이션 적용
