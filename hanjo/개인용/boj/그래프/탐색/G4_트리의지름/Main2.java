package 그래프.탐색.G4_트리의지름;

import java.io.*;
import java.util.*;

public class Main2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        if(n == 1){
            System.out.println(0);
            return;
        }
        
        int[][] edges = new int[n-1][3];
        for(int i=0; i<n-1; i++){
            st = new StringTokenizer(br.readLine());
            edges[i][0] = Integer.parseInt(st.nextToken());
            edges[i][1] = Integer.parseInt(st.nextToken());
            edges[i][2] = Integer.parseInt(st.nextToken());
        }

        System.out.println(new Solution().solution(n, edges));
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
        
        public int n;
        public HashMap<Integer, ArrayList<Node>> graph;

        public int solution(int n, int[][] edges){
            this.n = n;

            graph = new HashMap<>();
            for(int i=1; i<=n; i++){
                graph.put(i, new ArrayList<>());
            }
            for(int[] edge : edges){
                graph.get(edge[0]).add(new Node(edge[1], edge[2]));
                graph.get(edge[1]).add(new Node(edge[0], edge[2]));
            }

            Node maxFromRoot = bfs(1);
            Node maxFromMax = bfs(maxFromRoot.num);

            return maxFromMax.weight;
        }

        public Node bfs(int start){
            
            boolean[] isVisited = new boolean[n+1];
            Queue<Node> queue = new LinkedList<>();
            queue.offer(new Node(start, 0));
            isVisited[start] = true;

            Node maxNode = new Node(0, 0);

            while(!queue.isEmpty()){
                Node cur = queue.poll();
                if(cur.weight > maxNode.weight){
                    maxNode = cur;
                }
                for(Node next : graph.get(cur.num)){
                    if(!isVisited[next.num]){
                        isVisited[next.num] = true;
                        queue.offer(new Node(next.num, next.weight + cur.weight));
                    }
                }
            }
            return maxNode;
        }
    }

}

/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/1967
 * 날짜 : 220328
 * 성공여부 : 성공
 * 풀이시간 : 30m
 * 
 * 시간복잡도 : O(V+E) 2번
 * 메모리 : 24416 KB
 * 소요 시간 : 296 ms
 * ================================================================================
 * 
 * 저번에 DFS로 풀었어서 이번엔 BFS로 풀었다.
 * 근데 이 문제엔 DFS가 더 적합한듯?
 * 
 * 트리의 지름 구하는 방법 다시한번 복습
 * 루트에서 제일 먼 노드 구하고 -> 그 노드랑 제일 먼 노드
 * 
 */