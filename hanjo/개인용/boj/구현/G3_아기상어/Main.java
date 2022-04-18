package 구현.G3_아기상어;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        
        int[][] map = new int[n][n];
        for(int x=0; x<n; x++){
            st = new StringTokenizer(br.readLine());
            for(int y=0; y<n; y++){
                map[x][y] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(new Solution().solution(map));
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
            if(this.weight == o.weight){
                if(this.x == o.x){
                    return this.y - o.y;
                }
                return this.x - o.x;
            }
            return this.weight - o.weight;
        }

        @Override
        public String toString(){
            return "(" + x + "," + y + ")";
        }
    }

    public static class Solution {

        public final int[] dx = {-1, 0, 1, 0};
        public final int[] dy = {0, 1, 0, -1};
        public final int INF = Integer.MAX_VALUE;

        public int[][] map;
        public int n;
        public Node shark;
        public int sharkSize = 2;
        public int eatCount = 0;

        public int solution(int[][] map){
            this.map = map;
            this.n = map.length;

            // 상어 좌표 구하기
            for(int x=0; x<n; x++){
                for(int y=0; y<n; y++){
                    if(map[x][y] == 9){
                        shark = new Node(x, y, 0);
                        break;
                    }
                }
            }
            
            int time = 0;
            while(true){
                // 상어에서 다른 곳까지의 최단경로
                int[][] dist = new int[n][n];
                for(int[] d : dist){
                    Arrays.fill(d, INF);
                }
                List<Node> fishes = new ArrayList<>();
                dijkstra(shark, dist, fishes);
                for(Node fish : fishes){
                    fish.weight = dist[fish.x][fish.y];
                }
                Collections.sort(fishes);
                // 엄마부르기
                if(fishes.isEmpty()){
                    break;
                }
                // 물고기 먹기
                Node fish = fishes.get(0);
                eatFish(shark, fish);
                time += fish.weight;
            }

            return time;
        }

        public void dijkstra(Node shark, int[][] dist, List<Node> fishes){
            boolean[][] isVisited = new boolean[n][n];

            PriorityQueue<Node> queue = new PriorityQueue<>();
            queue.offer(shark);
            dist[shark.x][shark.y] = 0;

            while(!queue.isEmpty()){
                Node cur = queue.poll();
                isVisited[cur.x][cur.y] = true;
                for(int i=0; i<4; i++){
                    Node next = new Node(cur.x + dx[i], cur.y + dy[i], dist[cur.x][cur.y] + 1);
                    if(next.x < 0 || next.x >= n || next.y < 0 || next.y >= n){
                        continue;
                    }
                    if(map[next.x][next.y] > sharkSize){
                        continue;
                    }
                    // 방문체크 안해주면 시간초과 난다
                    if(isVisited[next.x][next.y] || dist[next.x][next.y] < next.weight){
                        continue;
                    }
                    if(map[next.x][next.y] < sharkSize && map[next.x][next.y] != 0){
                        fishes.add(next);
                    }
                    isVisited[next.x][next.y] = true;
                    dist[next.x][next.y] = next.weight;
                    queue.offer(next);
                }
            }
        }

        public void eatFish(Node shark, Node fish){
            map[shark.x][shark.y] = 0;
            map[fish.x][fish.y] = 9;
            shark.x = fish.x;
            shark.y = fish.y;
            eatCount++;
            if(eatCount == sharkSize){
                sharkSize++;
                eatCount = 0;
            }
        }
    }
    
}

/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/16236
 * 날짜 : 220407
 * 성공여부 : 반성공
 * 풀이시간 : 2h
 * 
 * 시간복잡도 : ?
 * 메모리 : 32696 KB
 * 소요 시간 : 284 ms
 * ================================================================================
 * 
 * (Main2 풀이가 먼저 푼거고 소요시간이 너무 오래걸려서 개선한 풀이)
 * BFS를 활용한 구현 문제
 * 
 * 그냥 지문대로 풀었고 최단경로를 구해야 하므로 다익스트라를 사용함
 * 근데 굳이 다익스트라 사용안해도 되고 BFS로 해결 가능함
 * 
 * 다익스트라는 보통 방문체크를 안해주는데, 이 문제는 방문체크 안해주면 시간초과 났음
 * 중복방문이 많이 일어나는 것같음.. 다익스트라도 방문체크 해주기로 약속~
 * 
 */