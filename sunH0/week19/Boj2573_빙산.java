import java.util.*;
import java.io.*;

public class Boj2573_빙산 {

    static int N, M, ans;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
    
    private static int cntIceburge(){
        int cnt = 0;
        visited = new boolean[N][M];
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] != 0 && !visited[i][j]) {
                    dfs(i, j);
                    cnt++;
                }
            }  
        }  

        return cnt;
    }

    private static void dfs(int x, int y){
        visited[x][y] = true;

        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx >= 0 && ny >= 0 && nx < N && ny < M) {
                if(map[nx][ny] != 0 && !visited[nx][ny]) {
                    dfs(nx, ny);
                }
            }
        }

    }

    public static void melting() {

        boolean[][] temp = new boolean[N][M];

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] != 0) {
                    temp[i][j] = true;
                    int cnt = 0;

                    for(int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if(nx >= 0 && ny >= 0 && nx < N && ny < M) {
                            if(!temp[nx][ny] && map[nx][ny] == 0) cnt++;
                        }
                        
                    }
                    if(map[i][j] < cnt) map[i][j] = 0;
                    else map[i][j] -= cnt;
                }

            }
        }

    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());

        N =	Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

        ans = 0;

        while(true){
            int iceCnt = cntIceburge();
            if(iceCnt >= 2) break; 
            else if(iceCnt==0) {
                ans=0;
                break;
            }

            melting();
            ans++;
        }

        System.out.println(ans);
    }

}
