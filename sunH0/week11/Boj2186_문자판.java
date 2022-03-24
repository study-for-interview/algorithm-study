package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Boj2186_문자판 {

    static int N,M,K;
	static char[][] map;
	static char[] taget;
	static int[][][] dp;
	static int[] dx= {0,0,1,-1};
	static int[] dy= {1,-1,0,0};

    public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine()," ");

		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		map=new char[N][M];

		for(int i=0; i<N; i++) {
			map[i]=br.readLine().toCharArray();
		}

		taget=br.readLine().toCharArray();
        int ans=0;
        dp = new int[N][M][taget.length];

		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				Arrays.fill(dp[i][j], -1);
			}
		}


		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j]==taget[0]) {
					ans+=dfs(i,j,0);
				}
			}
		}

		System.out.println(ans);
	}

	private static int dfs(int x, int y, int idx) {

		if(idx==taget.length-1){
            return dp[x][y][idx] = 1;
        }
		if(dp[x][y][idx]!=-1) return dp[x][y][idx];

        dp[x][y][idx]=0;


		for(int i=0; i<4; i++) {
			for(int j=1; j<=K; j++) {
				int nx=x+dx[i]*j;
				int ny=y+dy[i]*j;

				if(nx<0||ny<0||nx>=N||ny>=M) continue;
				if(map[nx][ny]==taget[idx+1]) {
                    dp[i][j][idx] += dfs(nx, ny, idx + 1);

				}
			}
		}
		
		return dp[x][y][idx];
	}

    
}




