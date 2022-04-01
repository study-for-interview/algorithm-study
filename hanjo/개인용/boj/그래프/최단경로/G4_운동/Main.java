package 그래프.최단경로.G4_운동;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        int[][] edges = new int[e][3];
        for(int i=0; i<e; i++){
            st = new StringTokenizer(br.readLine());
            edges[i][0] = Integer.parseInt(st.nextToken());
            edges[i][1] = Integer.parseInt(st.nextToken());
            edges[i][2] = Integer.parseInt(st.nextToken());
        }
        
        System.out.println(new Solution().solution(v, edges));
    }

    public static class Node implements Comparable<Node> {
        public int num;
        public int weight;

        public Node(int num, int weight){
            this.num = num;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o){
            return this.weight - o.weight;
        }
    }
    
    public static class Solution{

        public final int INF = 1000;
        public Map<Integer, List<Node>> graph;

        public int solution(int v, int[][] edges){

            // 그래프 초기화
            graph = new HashMap<>();
            for(int i=1; i<=v; i++){
                graph.put(i, new ArrayList<>());
            }
            for(int[] edge : edges){
                graph.get(edge[0]).add(new Node(edge[1], edge[2]));
            }

            // 최단경로 배열 초기화
            int[][] dist = new int[v+1][v+1];
            for(int i=1; i<=v; i++){
                Arrays.fill(dist[i], INF);
            }

            // 순회하며 다익스트라
            for(int i=1; i<=v; i++){
                dijkstra(i, dist[i]);
            }

            for(var d : dist){
                System.out.println(Arrays.toString(d));
            }

            // 배열을 순회하며 사이클들의 최소값 찾기
            int min = INF;
            for(int i=1; i<=v; i++){
                for(int j=1; j<=v; j++){
                    if(i==j){
                        min = Math.min(min, dist[i][j]);
                        continue;
                    }
                }
            }
            return min == INF ? -1 : min;
        }

        public void dijkstra(int start, int[] dist){
            
            PriorityQueue<Node> queue = new PriorityQueue<>();
            queue.offer(new Node(start, 0));

            while(!queue.isEmpty()){
                Node cur = queue.poll();

                for(Node next : graph.get(cur.num)){
                    int nextDist = dist[cur.num] + next.weight;
                    if(nextDist < dist[next.num]){
                        dist[next.num] = nextDist;
                        queue.offer(next);
                    }
                }
            }
        }
        
    }
}


/**
 * ================================================================================
 * 링크 : 
 * 날짜 : 
 * 성공여부 : 
 * 풀이시간 : 
 * 
 * 시간복잡도 : 
 * 메모리 : KB
 * 소요 시간 : ms
 * ================================================================================
 * 
 * 
 */