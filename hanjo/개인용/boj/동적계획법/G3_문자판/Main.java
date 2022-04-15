package 동적계획법.G3_문자판;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        
        map = new String[n][m];
        for(int i=0; i<n; i++){
            String[] temp = br.readLine().split("");
            for(int j=0; j<m; j++){
                map[i][j] = temp[j];
            }
        }
        String targetStr = br.readLine();
        
        System.out.println(solution(targetStr));
    }

    public static final int[] dx = {-1, 0, 1, 0};
    public static final int[] dy = {0, 1, 0, -1};
    public static int n;
    public static int m;
    public static int k;
    public static int l;
    public static String[][] map;
    public static String[] target;
    public static int[][][] dp;

    public static int solution(String targetStr){
        l = targetStr.length();
        target = targetStr.split("");
        
        // 시작정점과 dp 초기화
        List<int[]> start = new ArrayList<>();
        dp = new int[n][m][l];

        for(int x=0; x<n; x++){
            for(int y=0; y<m; y++){
                if(map[x][y].equals(target[0])){
                    start.add(new int[]{x, y});
                }
                for(int z=0; z<l; z++){
                    dp[x][y][z] = -1;
                }
            }
        }

        int sum = 0;
        for(int[] node : start){
            int x = node[0];
            int y = node[1];
            sum += dfs(x, y, 1);
        }
        return sum;
    }

    public static int dfs(int x, int y, int depth){
        // 문자열 끝에 도달하면 리턴
        if(depth == l){
            return 1;
        }
        // 이미 초기화된 정점이라면 고대로 리턴
        if(dp[x][y][depth] != -1){
            return dp[x][y][depth];
        }

        // 방문시 일단 dp 초기화
        dp[x][y][depth] = 0;

        for(int i=0; i<4; i++){
            for(int j=1; j<=k; j++){
                int nextX = x + dx[i]*j;
                int nextY = y + dy[i]*j;
                if(nextX < 0 || nextX >= n || nextY < 0 || nextY >= m){
                    continue;
                }
                // 다음 정점이 문자열 만족하지 않으면 빠꾸
                if(!map[nextX][nextY].equals(target[depth])){
                    continue;
                }
                // 다음 정점의 dp결과를 더함
                dp[x][y][depth] += dfs(nextX, nextY, depth+1);
            }
        }
        
        return dp[x][y][depth];
    }
    
}

/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/2186
 * 날짜 : 220324
 * 성공여부 : 실패 (구글링함)
 * 풀이시간 : 2h
 * 
 * 시간복잡도 : 
 * 메모리 : 22148 KB
 * 소요 시간 : 644 ms
 * ================================================================================
 * 
 * dfs + dp 문제. 
 * 내리막길 문제의 상위호환 버전으로 두 문제가 거의 유사하다. 
 * 두 문제 모두 dfs를 하면서 해당 정점까지의 상태를 기록하는 방법으로 풀이한다
 * 
 * 이번 문제는 3차원 배열을 사용해야 했다
 * dp[x][y][depth] 로 -> (x,y) 정점이 문자열의 depth 인덱스만큼 도달했을 때, 문자열을 끝까지 완성할 수 있는 경우의 수를 저장한다
 * 
 * 만약 3차원 dp 배열에 익숙했다면 답 안보고 풀만했다...
 * 풀고나니 쏘카 코테 1번문제랑 거의 똑같다는 걸 깨달았음..... 미리 풀어봤으면 좋았을걸
 * 
 */