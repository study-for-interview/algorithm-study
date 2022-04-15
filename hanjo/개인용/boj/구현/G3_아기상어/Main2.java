package 구현.G3_아기상어;

import java.io.*;
import java.util.*;

public class Main2 {

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
    }

    public static class Solution {

        public final int[] dx = {-1, 0, 1, 0};
        public final int[] dy = {0, 1, 0, -1};
        public final int INF = Integer.MAX_VALUE;

        public int[][] map;
        public int n;
        public int sharkSize = 2;
        public int eatCount = 0;

        public int solution(int[][] map){
            this.map = map;
            this.n = map.length;

            int time = 0;
            while(true){
                // 상어 좌표, 작은 물고기 좌표 구하기
                Node shark = null;
                List<Node> fishes = new ArrayList<>();
                for(int x=0; x<n; x++){
                    for(int y=0; y<n; y++){
                        if(map[x][y] == 0){
                            continue;
                        }
                        if(map[x][y] == 9){
                            shark = new Node(x, y, 0);
                            continue;
                        }
                        if(map[x][y] < sharkSize){
                            fishes.add(new Node(x, y, 0));
                        }
                    }
                }
                // 상어 <-> 작은 물고기 최단경로 모두 구하기
                for(Node fish : fishes){
                    fish.weight = dijkstra(shark, fish);
                }
                Collections.sort(fishes);
                // 엄마부르기
                if(fishes.isEmpty() || fishes.get(0).weight == INF){
                    break;
                }
                // 물고기 먹기
                Node fish = fishes.get(0);
                eatFish(shark, fish);
                time += fish.weight;
            }

            return time;
        }

        public int dijkstra(Node start, Node end){

            boolean[][] isVisited = new boolean[n][n];

            int[][] dist = new int[n][n];
            for(int[] d : dist){
                Arrays.fill(d, INF);
            }

            Queue<Node> queue = new LinkedList<>();
            queue.offer(start);
            dist[start.x][start.y] = 0;

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
                    if(isVisited[next.x][next.y] || dist[next.x][next.y] < next.weight){
                        continue;
                    }
                    isVisited[next.x][next.y] = true;
                    dist[next.x][next.y] = next.weight;
                    queue.offer(next);
                }
            }
            return dist[end.x][end.y];
        }

        public void eatFish(Node shark, Node fish){
            map[shark.x][shark.y] = 0;
            map[fish.x][fish.y] = 9;
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
 * 메모리 : 298500 KB
 * 소요 시간 : 1392 ms
 * ================================================================================
 * 
 * 기존에 풀었던 방식
 * 상어가 이동할때마다 map 전체를 순회하므로 시간이 개오래걸림
 * 메모리도 많이 씀
 * 
 */
