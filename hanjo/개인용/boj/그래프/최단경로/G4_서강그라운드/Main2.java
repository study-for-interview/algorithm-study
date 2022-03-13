package 그래프.최단경로.G4_서강그라운드;

import java.io.*;
import java.util.*;

public class Main2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());   // 정점 수
        int m = Integer.parseInt(st.nextToken());   // 수색범위
        int r = Integer.parseInt(st.nextToken());   // 간선 수

        int[] items = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++){
            items[i] = Integer.parseInt(st.nextToken());
        }

        int[][] edges = new int[r][3];
        for(int i=0; i<r; i++){
            st = new StringTokenizer(br.readLine());
            edges[i][0] = Integer.parseInt(st.nextToken());
            edges[i][1] = Integer.parseInt(st.nextToken());
            edges[i][2] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(m, items, edges));
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

    public static final int INF = Integer.MAX_VALUE;

    public static int solution(int m, int[] items, int[][] edges){

        int n = items.length - 1;

        HashMap<Integer, ArrayList<Node>> graph = new HashMap<>();
        for(int i=1; i<=n; i++){
            graph.put(i, new ArrayList<>());
        }
        for(int[] edge : edges){
            graph.get(edge[0]).add(new Node(edge[1], edge[2]));
            graph.get(edge[1]).add(new Node(edge[0], edge[2]));
        }

        int[][] dist = new int[n+1][n+1];
        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                if(i==j){
                    dist[i][j] = 0;
                }
                else{
                    dist[i][j] = INF;
                }
            }
        }

        for(int i=1; i<=n; i++){
            dijkstra(i, n, graph, dist);
        }

        int max = 0;
        for(int i=1; i<=n; i++){
            int sum = 0;
            for(int j=1; j<=n; j++){
                if(dist[i][j] <= m){
                    sum += items[j];
                }
            }
            if(sum > max){
                max = sum;
            }
        }
        
        return max;
    }

    public static void dijkstra(int start, int n, HashMap<Integer, ArrayList<Node>> graph, int[][] dist){
        
        boolean[] isVisited = new boolean[n+1];

        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(start, 0));

        while(!queue.isEmpty()){
            Node cur = queue.poll();
            isVisited[start] = true;
            
            for(Node next : graph.get(cur.num)){
                int newDist = dist[start][cur.num] + next.weight;
                if(!isVisited[next.num] && newDist < dist[start][next.num]){
                    dist[start][next.num] = newDist;
                    queue.offer(next);
                    // 큐에 넣을때 방문체크하면 오류난다
                    // isVisited[next.num] = true;
                }
            }
        }
    }
    
}

/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/14938
 * 성공여부 : 반성공
 * 풀이시간 : ?
 * 
 * 시간복잡도 : O(N*E*logN) 
 * 메모리 : 14536 KB
 * 소요 시간 : 140 ms
 * ================================================================================
 * 
 * 먼저 플로이드와샬로 풀고나서 최단경로 구하는 알고리즘만 바꾼 풀이임.
 * 굳이 dist 배열을 만들 필요는 없었지만 첫번째 풀이를 고대로 재사용했음.
 * (다익스트라를 사용하면 다익스트라를 돌리는 부분에서 먹을수 있는 아이템 합계를 구하면 됨)
 * 
 * < 시행착오 >
 * 분명 기본 테케에서 최단경로 배열이 정상적으로 나오는데 계속 답이 틀렸다고 떴음
 * 대체 뭐가 문제인지 삽질하다가 보니, 방문체크가 문제였다.
 * 이전 풀이는 인접정점을 큐에 넣을때 방문체크를 해줬었는데, 이렇게하면 최단경로의 후보군을 아예 줄여버리는 결과가 나온다.
 * 
 * 그리고 곰곰히 생각해보니 다익스트라에서 굳이 방문체크가 필요없었다.
 * 방문체크는 되돌아가는것을 방지하기위해 해주는것인데, 어차피 효율적인 경로를 선택하니 되돌아갈 일은 없음
 * 
 * 
 * 
 * 참고 링크 : https://www.acmicpc.net/board/view/9350
 * 
 */

