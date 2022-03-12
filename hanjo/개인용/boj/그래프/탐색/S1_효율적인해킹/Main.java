package 그래프.탐색.S1_효율적인해킹;

import java.util.*;
import java.io.*;

public class Main {

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
    public static int[] answer;
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

        // 정점 방문 개수 최대값 저장 배열
        answer = new int[n+1];
        
        // 각 정점당 DFS
        isVisited = new boolean[n+1];
        for(int v=1; v<=n; v++){
            Arrays.fill(isVisited, 1, n+1, false);
            startV = v;
            dfs(v);
        }

        System.out.println(Arrays.toString(answer));

        // 정답 출력
        int max = 0;
        for(int i=1; i<=n; i++){
            max = Math.max(max, answer[i]);
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=n; i++){
            if(answer[i] == max){
                sb.append(i).append(" ");
            }
        }
        System.out.println(sb.toString());
    }

    public static void dfs(int curV){
        isVisited[curV] = true;

        for(int nextV : graph.get(curV)){
            if(!isVisited[nextV]){
                dfs(nextV);
                answer[startV] += 1;
            }
        }
    }
}


/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/1325
 * 성공여부 : 실패
 * 풀이시간 : 3h
 * 
 * 시간복잡도 : 
 * 메모리 : 47900 KB
 * 소요 시간 : 7540 ms
 * ================================================================================
 * 
 * 문제를 잘못골랐다.. 티어가 낮아도 정답 비율이 낮은건 피해야 함
 * 
 * 문제 지문이 개헷갈려서 잘못이해하고 시간 엄청씀 ㅡㅡ
 * dfs시 하나의 경로로만 가면서 방문 정점을 세야하는 줄 알았는데, 정점 방문시마다 연결된 정점을 세면서 가는거임
 * (한마디로 더 어렵게 생각함)
 * dfs만 할줄알면 풀 수 있는 문제 
 * 
 */