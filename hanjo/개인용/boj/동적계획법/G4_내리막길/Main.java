package 동적계획법.G4_내리막길;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];
        for(int x=0; x<n; x++){
            st = new StringTokenizer(br.readLine());
            for(int y=0; y<m; y++){
                map[x][y] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solution(map));
    }

    public static final int[] dx = {-1, 0, 1, 0};
    public static final int[] dy = {0, 1, 0, -1};
    public static int n;
    public static int m;

    public static int solution(int[][] map){
        n = map.length;
        m = map[0].length;

        int[][] dp = new int[n][m];
        for(int i=0; i<n; i++){
            Arrays.fill(dp[i], -1);
        }

        return dfs(0, 0, map, dp);
    }

    public static int dfs(int x, int y, int[][] map, int[][] dp){
        if(x == n-1 && y == m-1){
            return 1;
        }

        if(dp[x][y] == -1){
            dp[x][y] = 0;   // 일단 밟음
            for(int i=0; i<4; i++){
                int nextX = x + dx[i];
                int nextY = y + dy[i];
                if(nextX < 0 || nextX >= n || nextY < 0 || nextY >= m){
                    continue;
                }
                if(map[x][y] <= map[nextX][nextY]){
                    continue;
                }
                dp[x][y] += dfs(nextX, nextY, map, dp);
            }
        }

        return dp[x][y];
    }
}

/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/1520
 * 날짜 : 220317
 * 성공여부 : 실패 (구글링)
 * 풀이시간 : 1h
 * 
 * 시간복잡도 : 
 * 메모리 : 35608 KB
 * 소요 시간 : 404 ms
 * ================================================================================
 * 
 * DFS + DP 문제
 * 
 * 문제를 읽고 단순히 완전탐색하면 되겠다고 생각했다.
 * 왜냐하면 모든 경로를 탐색하지 않고 탐욕법스럽게(?) 현재 정점보다 낮은 가중치의 정점을 선택하기 때문이다
 * 그래서 걍 bfs 써서 모두 탐색하도록 풀었는데 메모리 초과가 떴다 (Try1)
 * bfs가 안되나 싶어서 그냥 dfs를 썼는데 이번엔 시간초과가 떴다;
 * 
 * 뭐가 문젠가 싶어서 카테고리를 보니까 DP 문제길래 이게 왜 DP지? 어디서 DP를 써야하지?를 고민해봤지만
 * .. 떠오르지 않아서 그냥 답을 봤다.
 * 
 * 물론 모든 경로를 탐색하지 않는 것은 맞지만, 만약 갈 수 있는 경로가 엄청 많다면 ?
 * 시작 정점부터 끝 정점까지 모든 경로를 탐색해야 하고, dfs로 생각하면 O(4^(N*M))이 나오게 된다
 * 
 * 따라서 한번 완성된 경로를 재사용하는 경우를 위해 DP에 메모리제이션해줘야 하는 것
 * 
 * -1 : 한번도 안밟음
 * 0 : 밟았는데 끝까지 못감
 * 1 이상 : 밟고 끝까지 갔음
 * 
 */