import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 사회망 서비스(SNS)
// DP + 트리
public class p2533 {
    static int n;
    static boolean[] visited;
    static List<Integer>[] graph;
    // dp : 해당 지점까지의 얼리어답터 인원수(트리 구조이기 때문에 자식 노드들의 dp개수를 더해온다)
    static int[][] dp;	// [y][x]일 때, y : 노드 번호, x : 0 -> 해당 노드번호가 earlyAdaptor가 아닐때, 1 -> 해당 노드번호가 earlyAdaptor일 때

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.valueOf(br.readLine());
        dp = new int[n + 1][2];
        visited = new boolean[n + 1];
        graph = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }


        StringTokenizer st;
        for(int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.valueOf(st.nextToken());
            int end = Integer.valueOf(st.nextToken());
            graph[start].add(end);
            graph[end].add(start);
        }

        // 트리 구조이기 때문에 1부터 시작
        dfs(1);
        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    static void dfs(int number) {
        visited[number] = true;
        dp[number][0] = 0;	// 해당 number가 얼리어답터가 아닌 경우
        dp[number][1] = 1;	// 해당 number가 얼리어답터인 경우(우선 시작 시 해당 지점 얼리어답터이므로 개수 1)

        for(int child : graph[number]) {
            if(!visited[child]) {	// dfs 중복 방문 방지(안해도되는데 확인해보기)
                dfs(child);	// dfs 재귀호출을 통해 자식 노드의 dp값을 미리 구한다.
                dp[number][0] += dp[child][1];	// 자식 노드가 무조건 얼리어답터여야한다.
                dp[number][1] += Math.min(dp[child][0], dp[child][1]);	// 왜냐하면 최소의 얼리어답터 인원을 뽑기 때문에 자식 노드가 얼리어답터 일수도, 아닐수도 있다.
            }
        }
    }