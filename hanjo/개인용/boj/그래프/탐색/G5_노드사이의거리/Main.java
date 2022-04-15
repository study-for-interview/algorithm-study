package 그래프.탐색.G5_노드사이의거리;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] edges = new int[n-1][3];
        for(int i=0; i<n-1; i++){
            st = new StringTokenizer(br.readLine());
            edges[i][0] = Integer.parseInt(st.nextToken());
            edges[i][1] = Integer.parseInt(st.nextToken());
            edges[i][2] = Integer.parseInt(st.nextToken());
        }
        
        int[][] targets = new int[m][2];
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            targets[i][0] = Integer.parseInt(st.nextToken());
            targets[i][1] = Integer.parseInt(st.nextToken());
        }

        new Solution().solution(n, edges, targets);
    }

    public static class Node {
        public int num;
        public int weight;

        public Node(int num, int weight){
            this.num = num;
            this.weight = weight;
        }
    }
    
    public static class Solution {

        public Map<Integer, List<Node>> graph;
        public boolean[] isVisited;
        public int dist;

        public void solution(int n, int[][] edges, int[][] targets){
            // 그래프 초기화
            graph = new HashMap<>();
            for(int i=1; i<=n; i++){
                graph.put(i, new ArrayList<>());
            }
            for(int[] edge : edges){
                graph.get(edge[0]).add(new Node(edge[1], edge[2]));            
                graph.get(edge[1]).add(new Node(edge[0], edge[2]));            
            }
            // 탐색
            isVisited = new boolean[n+1];
            for(int[] target : targets){
                Arrays.fill(isVisited, false);
                isVisited[target[0]] = true;
                dfs(0, target[0], target[1]);
                System.out.println(dist);
            }
        }

        public void dfs(int dist, int cur, int end){
            if(cur == end){
                this.dist = dist;
            }
            
            for(Node next : graph.get(cur)){
                if(!isVisited[next.num]){
                    isVisited[next.num] = true;
                    dfs(dist + next.weight, next.num, end);
                }
            }
        }
    }
}


/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/1240
 * 날짜 : 220404
 * 성공여부 : 성공
 * 풀이시간 : 30m
 * 
 * 시간복잡도 : O(N^2)
 * 메모리 : 48868 KB
 * 소요 시간 : 344 ms
 * ================================================================================
 * 
 * 그냥 그래프 탐색 문제
 * 
 * 트리도 걍 그래프니까 그래프 탐색하면서 경로의 합계를 저장하면됨
 * dfs로 풀긴했는데 bfs도 상관없을듯. 어차피 둘다 모든 정점을 방문함.
 * 
 * 근데 누적 거리를 구하는데에는 dfs가 더 적합함
 * 
 */