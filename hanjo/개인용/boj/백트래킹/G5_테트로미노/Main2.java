package 백트래킹.G5_테트로미노;

import java.io.*;
import java.util.*;

public class Main2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solution(n, m, map));
    }

    public static final int[] dx = {-1, 0, 1, 0};
    public static final int[] dy = {0, 1, 0, -1};
    public static int N;
    public static int M;
    public static boolean[][] isVisited;
    public static int[][] gMap;
    public static int max = 0;

    public static int solution(int n, int m, int[][] map){
        N = n;
        M = m;
        gMap = map;
        isVisited = new boolean[N][M];
        
        for(int x=0; x<N; x++){
            for(int y=0; y<M; y++){
                // 모든 모양 찾기
                isVisited[x][y] = true;
                dfs(1, map[x][y], x, y);
                isVisited[x][y] = false;
                // ㅏㅓㅗㅜ 모양 찾기
                searchExShape(x,y);
            }
        }

        return max;
    }

    public static void dfs(int depth, int sum, int x, int y){
        if(depth == 4){
            max = Math.max(max, sum);
            return;
        }
        
        for(int i=0; i<4; i++){
            int nextX = x + dx[i];
            int nextY = y + dy[i];
            // 범위 초과시
            if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= M){
                continue;
            }
            // 이미 방문시
            if(isVisited[nextX][nextY]){
                continue;
            }
            isVisited[nextX][nextY] = true;
            dfs(depth+1, sum + gMap[nextX][nextY], nextX, nextY);
            // 백트래킹
            isVisited[nextX][nextY] = false;
        }
    }

    // 미리 정의해놓고 찾는다
    private static int ex[][] = {{0, 0, 0, 1}, {0, 1, 2, 1}, {0, 0, 0, -1}, {0, -1, 0, 1}};
    private static int ey[][] = {{0, 1, 2, 1}, {0, 0, 0, 1}, {0, 1, 2, 1}, {0, 1, 1, 1}};

    public static void searchExShape(int x, int y){

        for(int i=0; i<4; i++){

            Boolean isOut = false;
            int sum_value = 0;

            for(int j=0; j<4; j++){
                int nx = x + ex[i][j];
                int ny = y + ey[i][j];

                if(nx < 0 || nx >= N || ny < 0 || ny >= M) {
                    isOut = true;
                    break;
                }
                else {
                    sum_value += gMap[nx][ny];
                }
            }
            if(!isOut) {
                max = Math.max(max, sum_value);
            }
        }
    }
}

/**
 * ================================================================================
 * 메모리 : 32380 KB
 * 소요 시간 : 748 ms
 * ================================================================================
 * 
 * searchExShape 메소드 구현만 다른 풀이
 * 다 풀고 다른 풀이 탐방하는데 이렇게 푼 풀이가 꽤 있길래 돌려봤더니 메모리를 더 조금씀
 * 첫번째 풀이에서는 원소마다 ArrayList를 계속 생성해줘서 많이 쓴듯.
 *
*/