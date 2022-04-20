package week15.구현_G2_로봇청소기;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true){
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            if(w==0 && h==0){
                return;
            }
            char[][] map = new char[h][w];
            for(int x=0; x<h; x++){
                String row = br.readLine();
                for(int y=0; y<w; y++){
                    map[x][y] = row.charAt(y);
                }
            }
            System.out.println(new Solution().solution(map));
        }
    }

    public static class Node {
        public int x;
        public int y;
        public int weight;
        
        public Node(int x, int y, int weight){
            this.x = x;
            this.y = y;
            this.weight = weight;
        }
    }

    public static class Solution {

        public static final int[] dx = {-1, 0, 1 ,0};
        public static final int[] dy = {0, -1, 0, 1};

        public int w;
        public int h;
        public char[][] map;

        public List<Node> targets;
        public int n;
        public int[][] dist;
        public boolean[] isVisited;
        public int min;
        
        public int solution(char[][] map){
            h = map.length;
            w = map[0].length;
            this.map = map;
            
            // 거리를 구할 목표를 모두 구함
            targets = new ArrayList<>();
            for(int x=0; x<h; x++){
                for(int y=0; y<w; y++){
                    if(map[x][y] == 'o'){
                        targets.add(0, new Node(x, y, 0));
                    }
                    else if(map[x][y] == '*'){
                        targets.add(new Node(x, y, 0));
                    }
                }
            }

            // 목표간 모든 거리를 구함
            n = targets.size();
            dist = new int[n][n];
            for(int i=0; i<n; i++){
                if(!bfs(i)){
                    return -1;
                }
            }

            // 모든 방문순서 경우의 수를 탐색
            isVisited = new boolean[n];
            isVisited[0] = true;
            min = Integer.MAX_VALUE;
            dfs(0, 0, 0);
            
            return min;
        }

        public boolean bfs(int startIdx){
            Node start = targets.get(startIdx);
            boolean[][] isVisited = new boolean[h][w];
            Queue<Node> queue = new LinkedList<>();
            queue.offer(start);
            isVisited[start.x][start.y] = true;

            int count = 0;
            
            while(!queue.isEmpty()){
                Node cur = queue.poll();

                for(int i=0; i<n; i++){
                    Node target = targets.get(i);
                    if(target.x == cur.x && target.y == cur.y){
                        dist[startIdx][i] = cur.weight;
                        dist[i][startIdx] = cur.weight;
                        count++;
                        break;
                    }
                }

                for(int i=0; i<4; i++){
                    Node next = new Node(cur.x + dx[i], cur.y + dy[i], cur.weight+1);
                    if(next.x < 0 || next.x >= h || next.y < 0 || next.y >= w){
                        continue;
                    }
                    if(map[next.x][next.y] == 'x'){
                        continue;
                    }
                    if(!isVisited[next.x][next.y]){
                        isVisited[next.x][next.y] = true;
                        queue.offer(next);
                    }
                }
            }
            return count == n;
        }

        public void dfs(int depth, int sum, int from){
            if(depth == n-1){
                min = Math.min(min, sum);
                return;
            }

            for(int to=1; to<n; to++){
                if(isVisited[to]){
                    continue;
                }
                isVisited[to] = true;
                dfs(depth+1, sum + dist[from][to], to);
                isVisited[to] = false;
            }
        }
    }
    
}


/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/4991
 * 날짜 : 220420
 * 성공여부 : 실패 (구글링)
 * 풀이시간 : 2h
 * 
 * 시간복잡도 : 
 * 메모리 : 26712 KB
 * 소요 시간 : 432 ms
 * ================================================================================
 * 
 * BFS -> DFS 구현 문제
 * 
 * 
 * 
 */