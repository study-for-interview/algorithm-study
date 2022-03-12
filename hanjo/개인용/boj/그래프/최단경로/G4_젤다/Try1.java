package 그래프.최단경로.G4_젤다;

import java.io.*;
import java.util.*;

public class Try1 {
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
        public int x;
        public int y;
        public int weight;

        public Node(int x, int y, int weight){
            this.x = x;
            this.y = y;
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

        Map<Node, List<Node>> graph = new HashMap<>();

        for(int x=0; x<n; x++){
            for(int y=0; y<n; y++){
                graph.put(new Node(x, y, 0), new ArrayList<>());
            }
        }
        for(int x=0; x<n; x++){
            for(int y=0; y<n; y++){
                Node cur = new Node(x, y, 0);
                for(int i=0; i<4; i++){
                    Node next = new Node(x + dx[i], y + dy[i], map[x][y]);
                    if (next.x < 0 || next.x >= n || next.y < 0 || next.y >= n) {
                        continue;
                    }
                    graph.get(cur).add(next);
                }
            }
        }
        return map[0][0] + dijkstra(n, graph);
    }

    public static int dijkstra(int n,  Map<Node, List<Node>> graph){

        int[][] dist = new int[n][n];
        for(int i=0; i<n; i++){
            Arrays.fill(dist[i], INF);
        }
        
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(0, 0, 0));
        
        while(!queue.isEmpty()){
            Node cur = queue.poll();
            
            for(Node next : graph.get(cur)){
                int newDist = dist[cur.x][cur.y] + next.weight;
                if(newDist < dist[next.x][next.y]){
                    queue.offer(next);
                }
            }
        }

        return dist[n-1][n-1];
    }
}

// 당연히 Node 해시값은 주소값이다. 이를 hashmap으로 비교하려면 모두 오버라이딩 해줘야함