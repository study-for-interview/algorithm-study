import java.util.*;
import java.io.*;
public class Boj2533_사회망서비스 {
    static int N;
    static int[][] dp;
    static List<Integer>[] tree;
    static boolean[] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        dp = new int[N+1][2];
        tree = new ArrayList[N+1];
        visited = new boolean[N+1];

        for(int i=1; i<=N; i++){
            tree[i] = new ArrayList<>();
        }   

        for(int i = 1; i < N; i++) { // 모든 노드는 연결되어 있다.
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            tree[p].add(c);
            tree[c].add(p);
        }

        dp(1);
        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    static void dp(int num) {
        
       if(visited[num]==false){
            visited[num] = true;
            dp[num][0] = 0;
            dp[num][1] = 1;
        }else return;

        for(int node : tree[num]) {
            if(!visited[node]) {
                dp(node);
                dp[num][0] += dp[node][1];
                dp[num][1] += Math.min(dp[node][0],dp[node][1]);
            }

        }
 
    }
    
}

// 모든 경우의 수 -> DP -> 부모나 자식 중 하나는 얼리어답터야 한다. -> 자식 부모 둘다 얼리어답터일 수 있다.(즉, 최소값을 저장한다.)
// unsolve
