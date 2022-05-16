package 구현.G3_스타트택시;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int fuel = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][n];
        for(int x=0; x<n; x++){
            st = new StringTokenizer(br.readLine());
            for(int y=0; y<n; y++){
                map[x][y] = Integer.parseInt(st.nextToken());
            }
        }

        int[] start = new int[2];
        st = new StringTokenizer(br.readLine());
        start[0] = Integer.parseInt(st.nextToken())-1;
        start[1] = Integer.parseInt(st.nextToken())-1;

        int[][] passengers = new int[m][4];
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            passengers[i][0] = Integer.parseInt(st.nextToken())-1;
            passengers[i][1] = Integer.parseInt(st.nextToken())-1;
            passengers[i][2] = Integer.parseInt(st.nextToken())-1;
            passengers[i][3] = Integer.parseInt(st.nextToken())-1;
        }
        
        System.out.println(new Solution().solution(fuel, map, start, passengers));
    }

    public static class Node implements Comparable<Node>{
        public int x;
        public int y;
        public int weight;
        public int idx;

        public Node(int x, int y, int weight){
            this.x = x;
            this.y = y;
            this.weight = weight;
        }

        public Node(int x, int y, int weight, int idx){
            this.x = x;
            this.y = y;
            this.weight = weight;
            this.idx = idx;
        }

        @Override
        public int compareTo(Node o){
            if(this.weight == o.weight){
                if(this.x == o.x){
                    return this.y - o.y;
                }
                return this.x - o.x;
            }
            return this.weight - o.weight;
        }
    }

    public static class Solution {

        public static final int INF = Integer.MAX_VALUE;
        public static final int[] dx = {-1, 0, 1, 0};
        public static final int[] dy = {0, 1, 0, -1};

        public int n;
        public int m;
        public int[][] map;
        public List<Node> fromList;
        public List<Node> toList;
        
        public int solution(int fuel, int[][] map, int[] start, int[][] passengers){
            this.n = map.length;
            this.m = passengers.length;
            this.map = map;
            
            fromList = new ArrayList<>();
            toList = new ArrayList<>();
            for(int i=0; i<m; i++){
                fromList.add(new Node(passengers[i][0], passengers[i][1], INF, i));
                toList.add(new Node(passengers[i][2], passengers[i][3], INF, i));
            }

            Node taxi = new Node(start[0], start[1], 0);
            boolean[] isArrived = new boolean[m];

            while(!isDone(isArrived)){
                // 택시 -> 손님
                int[][] dist1 = dijkstra(taxi);
                for(Node from : fromList){
                    from.weight = dist1[from.x][from.y];
                }
                Collections.sort(fromList);

                Node from = null;
                for(Node f : fromList){
                    if(!isArrived[f.idx]){
                        from = f;
                        fuel -= from.weight;
                        break;
                    }
                }
                if(fuel < 0){
                    return -1;
                }
                
                // 손님 -> 목적지
                int[][] dist2 = dijkstra(from);
                Node to = toList.get(from.idx);
                int dist = dist2[to.x][to.y];

                fuel -= dist;
                if(fuel < 0){
                    return -1;
                }
                taxi = to;
                fuel += dist*2;
                isArrived[from.idx] = true;
            }
            
            return fuel;
        }

        public int[][] dijkstra(Node start){

            PriorityQueue<Node> queue = new PriorityQueue<>();
            boolean[][] isVisited = new boolean[n][n];
            int[][] dist = new int[n][n];
            for(int i=0; i<n; i++){
                Arrays.fill(dist[i], INF);
            }

            queue.offer(start);
            isVisited[start.x][start.y] = true;
            dist[start.x][start.y] = 0;

            while(!queue.isEmpty()){
                Node cur = queue.poll();
                isVisited[cur.x][cur.y] = true;

                for(int i=0; i<4; i++){
                    Node next = new Node(cur.x + dx[i], cur.y + dy[i], dist[cur.x][cur.y] + 1);
                    if(next.x < 0 || next.x >= n || next.y < 0 || next.y >= n){
                        continue;
                    }
                    if(map[next.x][next.y] == 1){
                        continue;
                    }
                    if(!isVisited[next.x][next.y] && dist[next.x][next.y] > next.weight){
                        dist[next.x][next.y] = next.weight;
                        queue.offer(next);
                    }
                }
            }
            return dist;
        }

        public boolean isDone(boolean[] isArrived){
            for(boolean b : isArrived){
                if(!b){
                    return false;
                }
            }
            return true;
        }
    }
}


/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/19238
 * 날짜 : 220413
 * 성공여부 : 실패(1시간 초과)
 * 풀이시간 : 2h
 * 
 * 시간복잡도 : 
 * 메모리 : 65384 KB
 * 소요 시간 : 552 ms
 * ================================================================================
 * 
 * 구현 + 최단경로 문제(BFS or 다익스트라)
 * 최단경로 개념이 나오면 항상 다익스트라로 풀었는데, 좌표에서의 최단경로는 그냥 BFS가 더 빠름
 * (근데 이번에도 다익스트라 씀 다음엔 BFS로 풀자)
 * 
 * 1. 택시가 손님을 태울때의 최단경로를 구하고
 * 2. 태운 후 도착지까지의 최단경로를 구한다
 * 
 * 위 과정을 반복하면서 주어진 손님을 다 데려다 주면 되는데
 * 과정을 반복할때마다 조건에 맞춰 연료를 업데이트 시켜줘야함
 * 
 * 처음 설계할때 손님 출발지-도착지 자료구조를 제대로 생각안하고 시작해서 고생함
 * 처음엔 설계대로 풀다가 점점 틀어져서 마지막에 무지성 풀이했음.. 코드 별로임
 * 
 */