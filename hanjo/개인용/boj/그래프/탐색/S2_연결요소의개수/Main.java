package 그래프.탐색.S2_연결요소의개수;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] edges = new int[m][2];
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            edges[i][0] = Integer.parseInt(st.nextToken());
            edges[i][1] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(n, edges));
    }

    public static HashMap<Integer, ArrayList<Integer>> graph;
    public static boolean[] isVisited;

    public static int solution(int n, int[][] edges){
        
        graph = new HashMap<>();
        for(int i=1; i<=n; i++){
            graph.put(i, new ArrayList<>());
        }
        for(int[] edge : edges){
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        isVisited = new boolean[n+1];

        // dfs
        int count = 0;
        for(int i=1; i<=n; i++){
            if(!isVisited[i]){
                count++;
                dfs(i);
            }
        }

        return count;
    } 
    
    public static void dfs(int cur){

        for(int next : graph.get(cur)){
            if(!isVisited[next]){
                isVisited[next] = true;
                dfs(next);
            }
        }
    }
}

/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/11724
 * 성공여부 : 성공
 * 풀이시간 : 10m
 * 
 * 시간복잡도 : O(n^2)
 * 메모리 : 170968 KB
 * 소요 시간 : 832 ms
 * ================================================================================
 * 
 * 단순 그래프 탐색 문제.
 * 분리된 그래프가 몇개 나오는지 찾으면 된다.
 * 
 * 1. 모든 정점을 시작점으로 두고 그래프 탐색을 해서 연결된 모든 정점은 방문체크 해줌
 * 2. 만약 다음 시작점이 이미 방문되었다면 이전에 연결된 그래프인 것
 * 3. 방문체크가 안된 시작점이 등장할때마다 개수를 세주면 됨
 * 
 */
