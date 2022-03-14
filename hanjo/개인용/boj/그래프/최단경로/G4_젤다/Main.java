package 그래프.최단경로.G4_젤다;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int count = 0;
        while(true){
            count ++;

            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            if(n == 0){
                return;
            }

            int[][] map = new int[n][n];
            for(int i=0; i<n; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<n; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            StringBuilder sb = new StringBuilder();
            sb.append("Problem ").append(count).append(": ").append(solution(n, map));
            System.out.println(sb.toString());
        }
    }

    public static class Node implements Comparable<Node>{
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

    public static final int[] dx = {-1, 0, 1, 0};
    public static final int[] dy = {0, 1, 0, -1};
    public static final int INF = Integer.MAX_VALUE;
    
    public static int solution(int n, int[][] map){
        Map<Integer, List<Node>> graph = new HashMap<>();

        for(int i=0; i<n*n; i++){
            graph.put(i, new ArrayList<>());
        }
        for(int x=0; x<n; x++){
            for(int y=0; y<n; y++){
                int cur = x*n + y;
                for(int i=0; i<4; i++){
                    int nextX = x + dx[i];
                    int nextY = y + dy[i];
                    if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= n) {
                        continue;
                    }
                    graph.get(cur).add(new Node(nextX*n + nextY, map[nextX][nextY]));
                }
            }
        }
        return map[0][0] + dijkstra(n, graph);
    }

    public static int dijkstra(int n,  Map<Integer, List<Node>> graph){

        int[] dist = new int[n*n];
        Arrays.fill(dist, INF);
        dist[0] = 0;
        
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(0, 0));
        
        while(!queue.isEmpty()){
            Node cur = queue.poll();
            
            for(Node next : graph.get(cur.num)){
                int newDist = dist[cur.num] + next.weight;
                if( newDist < dist[next.num]){
                    dist[next.num] = newDist;
                    queue.offer(next);
                }
            }
        }
        return dist[n*n-1];
    }
}

/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/4485
 * 성공여부 : 성공
 * 풀이시간 : 1h
 * 
 * 시간복잡도 : O((N+1)*log(N))
 * 메모리 : 77352 KB
 * 소요 시간 : 664 ms
 * ================================================================================
 * 
 * 평범한 다익스트라 문제.
 * 문제보고 그냥 배열을 그래프로 만들어버리고 풀어야겠다고 생각했다
 * 
 * n by n 배열이니까 이를 1차원배열로 저장하려면 x*n + y가 1차원배열 인덱스가 된다
 * 따라서 이것을 정점 번호로 저장해버리고 걍 평범하게 풀었다
 * 
 * 근데 굳이 안만들어도 됐을 것 같다. 
 * 풀이가 복잡해서 어디선가 오류가 터져서 오래걸렸다.. 또 이렇게하면 메모리를 엄청쓴다
 * 
 */