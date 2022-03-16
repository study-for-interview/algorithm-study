package 백트래킹.G5_테트로미노;

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
                // 방문체크 초기화 -> 이거때문에 시간소요가 매우 컸음
                // for(boolean[] arr : isVisited){
                //     Arrays.fill(arr, false);
                // }
                // 모든 모양 찾기
                isVisited[x][y] = true;
                dfs(1, map[x][y], x, y);
                isVisited[x][y] = false;    // 여기서 방문을 다시 풀어주면 모두 false 상태임
                // ㅏㅓㅗㅜ 모양 찾기
                searchExShape(x, y);
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

    public static ArrayList<Integer> adj = new ArrayList<>();

    public static void searchExShape(int x, int y){
        adj.clear();
        int sum = gMap[x][y];
        for(int i=0; i<4; i++){
            int adjX = x + dx[i];
            int adjY = y + dy[i];
            // 범위 초과시
            if(adjX < 0 || adjX >= N || adjY < 0 || adjY >= M){
                continue;
            }
            adj.add(gMap[adjX][adjY]);
            sum += gMap[adjX][adjY];
        }
        if(adj.size() == 3){
            max = Math.max(max, sum);
        }
        else if(adj.size() == 4){
            for(int val : adj){
                max = Math.max(max, sum - val);
            }
        }
    }
}

/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/14500
 * 성공여부 : 성공
 * 풀이시간 : 1h
 * 
 * 시간복잡도 : O(N^2)
 * 메모리 : 59372 -> 43624 KB (ArrayList 전역변수로 바꾼 후)
 * 소요 시간 : 3240 -> 732 ms (Arrays.fill 제거 후)
 * ================================================================================
 * 
 * 완전탐색 백트래킹 dfs 문제
 * 모든 원소를 시작점으로 dx, dy를 활용해 4번까지 뻗어나가면서 모든 테트로미노를 완전탐색한다
 * 
 * 근데 여기서 dfs의 한 줄기마다 isVisited를 다르게 가져야한다.
 * 만약 dfs의 한 줄기 탐색이 끝나고 방문체크를 풀어주지 않는다?
 *  -> 다음 줄기 탐색때 방문했던 원소를 다시 방문 못함
 *  -> 모든 경우의 수를 찾지 못함
 * 
 * 따라서 한 줄기 탐색이 끝나고 나오면서 방문체크를 풀어준다. 이것이 백트래킹
 * 
 * <시행착오>
 * dfs 함수 내부에서 백트래킹을 썼다면, dfs가 끝났을때 시작 원소빼고 모든 원소 방문체크가 풀려있을 것
 * 이것을 놓치고 원소시작시마다 isVisited 배열을 초기화 시켜줘서 소요시간이 4배정도 더걸렸음
 * 
 */