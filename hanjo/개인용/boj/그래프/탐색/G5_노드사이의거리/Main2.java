package 그래프.탐색.G5_노드사이의거리;

import java.io.*;
import java.util.*;

public class Main2 {

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

        public int n;
        public Map<Integer, List<Node>> graph;
        public int dist;

        public void solution(int n, int[][] edges, int[][] targets){
            this.n = n;
            // 그래프 초기화
            graph = new HashMap<>();
            for(int i=1; i<=n; i++){
                graph.put(i, new ArrayList<>());
            }
            for(int[] edge : edges){
                graph.get(edge[0]).add(new Node(edge[1], edge[2]));            
                graph.get(edge[1]).add(new Node(edge[0], edge[2]));            
            }
            // 탐색 - bfs
            for(int[] target : targets){
                bfs(target[0], target[1]);
                System.out.println(dist);
            }
        }

        public void bfs(int start, int end){
            boolean[] isVisited = new boolean[n+1];
            Queue<Node> queue = new LinkedList<>();
            isVisited[start] = true;
            queue.offer(new Node(start, 0));

            while(!queue.isEmpty()){
                Node cur = queue.poll();

                if(cur.num == end){
                    this.dist = cur.weight;
                    return;
                }

                for(Node next : graph.get(cur.num)){
                    if(!isVisited[next.num]){
                        isVisited[next.num] = true;
                        // bfs로 누적거리 구하려면 노드를 새로 초기화해줘야함
                        queue.offer(new Node(next.num, next.weight + cur.weight));
                    }
                }
            }
        }
    }
}

/**
 * ================================================================================
 * 시간복잡도 : O(V+E)
 * 메모리 : 64056 KB
 * 소요 시간 : 432 ms
 * ================================================================================
 * 
 * bfs 풀이.. 근데 왜 bfs가 시간 더걸림?
 * 
 */