import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static int n;
    static int[] population;
    static ArrayList<ArrayList<Integer>> graph;
    static boolean[] visited;
    static int[][] dp;

    static public void dfs(int number) {
        //0: 우수마을 아닐때
        //1: 우수마을일때

        visited[number] = true;
        dp[number][0] = 0;
        dp[number][1] = population[number];

        for (int child : graph.get(number)) {
            if (!visited[child]) {
                dfs(child);
                //우수 마을일때 자식노드가 무조건 우수마을이 아니여야한다.
                dp[number][1] += dp[child][0];
                // 우수마을이 아닐때 적어도 하나는 우수마을이어야한다.
                dp[number][0] += Math.max(dp[child][0], dp[child][1]);
            }
        }
    }


    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        dp = new int[n + 1][2];
        population = new int[n+1];
        visited = new boolean[n + 1];
        graph = new ArrayList<ArrayList<Integer>>();
        for(int i=0; i<=n; i++){
            graph.add(new ArrayList<Integer>());
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            population[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < n-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        dfs(1);
        System.out.println(Math.max(dp[1][0], dp[1][1]));
    }
}