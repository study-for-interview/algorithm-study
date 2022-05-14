package 구현.G2_로봇청소기;

import java.io.*;
import java.util.*;

public class Main2 {

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
            System.out.println(new Main2().solution(map));
        }
    }

    public static class Node {
        public int x;
        public int y;
        public int weight;
        public int mask;
        
        public Node(int x, int y, int weight, int mask){
            this.x = x;
            this.y = y;
            this.weight = weight;
            this.mask = mask;
        }
    }

    public static final int[] dx = {-1, 0, 1 ,0};
    public static final int[] dy = {0, -1, 0, 1};
    
    public int solution(char[][] map){
        int h = map.length;
        int w = map[0].length;

        int[][] intMap = new int[h][w];
        int dirtyNum = 0;
        Node start = null;
        for(int x=0; x<h; x++){
            for(int y=0; y<w; y++){
               if(map[x][y] == 'x'){
                   intMap[x][y] = -1;
               }
               else if(map[x][y] == '*'){
                   dirtyNum += 1; 
                   intMap[x][y] = dirtyNum;
               }
               else if(map[x][y] == 'o'){
                   start = new Node(x, y, 0, 0);
               }
            }
        }

        // 방문할 곳 만큼의 비트를 잡는다
        int maxMask = 1 << dirtyNum;
        boolean[][][] isVisited = new boolean[h][w][maxMask];
        Queue<Node> queue = new LinkedList<>();
        queue.offer(start);
        isVisited[start.x][start.y][0] = true;

        while(!queue.isEmpty()){
            Node cur = queue.poll();
            // 종료조건
            if(cur.mask == maxMask - 1){
                return cur.weight;
            }
            
            for(int i=0; i<4; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                int nw = cur.weight + 1;
                int nm = cur.mask;
                
                if(nx < 0 || nx >= h || ny < 0 || ny >= w){
                    continue;
                }
                if(intMap[nx][ny] == -1){
                    continue;
                }
                if(intMap[nx][ny] > 0){
                    nm = nm | (1 << intMap[nx][ny] - 1);
                }
                if(isVisited[nx][ny][nm]){
                    continue;
                }

                isVisited[nx][ny][nm] = true;
                queue.offer(new Node(nx, ny, nw, nm));
            }
        }

        return -1;
    }
}


/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/4991
 * 날짜 : 220426
 * 성공여부 : 성공
 * 풀이시간 : 1h
 * 
 * 시간복잡도 : 
 * 메모리 : 66404 KB
 * 소요 시간 : 340 ms
 * ================================================================================
 * 
 * 비트마스킹 + bfs 풀이
 * 
 * 
 */