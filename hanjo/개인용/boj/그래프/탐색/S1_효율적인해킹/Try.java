package 그래프.탐색.S1_효율적인해킹;

import java.util.*;
import java.io.*;

public class Try {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());   // 정점 수
        int m = Integer.parseInt(st.nextToken());   // 간선 수

        int[][] edges = new int[m][2];
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            edges[i][0] = Integer.parseInt(st.nextToken());
            edges[i][1] = Integer.parseInt(st.nextToken());
        }
        solution(n, m, edges);
    }

    public static ArrayList<List<Integer>> graph;
    public static ArrayList<Integer> maxOfDepth;
    public static boolean[] isVisited;
    public static int startV;

    public static void solution(int n, int m, int[][] edges){

        // 그래프 설정
        graph = new ArrayList<>();
        for(int i=0; i<=n; i++){
            graph.add(new ArrayList<>());
        }
        for(int[] edge : edges){
            graph.get(edge[1]).add(edge[0]);
        }

        // 정점 방문 개수 최대값 저장 리스트
        maxOfDepth = new ArrayList<>();
        for(int i=0; i<=n; i++){
            maxOfDepth.add(0);
        }
        
        // 각 정점당 DFS
        isVisited = new boolean[n+1];
        for(int v=1; v<=n; v++){
            Arrays.fill(isVisited, 1, n+1, false);
            startV = v;
            dfs(v, 0);
            
        }

        System.out.println(maxOfDepth);

        // 정답 출력
        ArrayList<Integer> answer = new ArrayList<>();
        int max = Collections.max(maxOfDepth);
        for(int i=1; i<=n; i++){
            if(maxOfDepth.get(i) == max)
                answer.add(i);
        }
        Collections.sort(answer);

        
        StringBuilder sb = new StringBuilder();
        for(int a : answer){
            sb.append(a).append(" ");
        }
        System.out.println(sb.toString());
    }

    public static void dfs(int curV, int depth){
        isVisited[curV] = true;

        for(int nextV : graph.get(curV)){
            if(!isVisited[nextV]){
                dfs(nextV, depth + 1);
            }
        }
        maxOfDepth.set(startV, Math.max(depth, maxOfDepth.get(startV)));
    }
    
}
