package week10.최단경로_G2_일요일아침데이트;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        char[][] map = new char[n][m];
        for(int i=0; i<n; i++){
            char[] temp = br.readLine().toCharArray();
            for(int j=0; j<n; j++){
                map[i][j] = temp[j];
            }
        }

        solution(map);
    }

    public static class Node{
        public int x;
        public int y;

        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static class Dist{
        public int path;
        public int garbage;
        public int garbageSide;

        public Dist(int path, int garbage, int garbageSide){
            this.path = path;
            this.garbage = garbage;
            this.garbageSide = garbageSide;
        }

        @Override
        public String toString(){
            return path + " " + garbage + " " + garbageSide; 
            // return String.valueOf(path);
        }
    }

    
    public static final int[] dx = {-1, 0, 1, 0};
    public static final int[] dy = {0, 1, 0, -1};
    public static int n;
    public static int m;

    public static void solution(char[][] map){
        n = map.length;
        m = map[0].length;

        Node start = null;
        Node flower = null;
    
        Dist[][] dist = new Dist[n][m];
        for(int x=0; x<n; x++){
            for(int y=0; y<n; y++){
                if(map[x][y] == 'S'){
                    start = new Node(x, y);
                }
                if(map[x][y] == 'F'){
                    flower = new Node(x, y);
                }
                dist[x][y] = new Dist(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
            }
        }
        dijkstra(start, dist, map);


        for(Dist[] d : dist){
            System.out.println(Arrays.toString(d));
        }
        
        System.out.println(dist[flower.x][flower.y].garbage + " " + dist[flower.x][flower.y].garbageSide);
    }
    
    public static void dijkstra(Node start, Dist[][] dist, char[][] map){
        
        Queue<Node> queue = new LinkedList<>();
        queue.offer(start);
        dist[start.x][start.y] = new Dist(0, 0, 0);

        while(!queue.isEmpty()){
            Node cur = queue.poll();
            Dist curDist = dist[cur.x][cur.y];
            
            for(int i=0; i<4; i++){
                Node next = new Node(cur.x + dx[i], cur.y + dy[i]);

                if(isOutIndex(next.x, next.y)){
                    continue;
                }

                Dist nextDist = dist[next.x][next.y];

                int garbage = 0;
                if(map[next.x][next.y] == 'g'){
                    garbage++;
                }

                int garbageSide = 0;
                for(int j=0; j<4; j++){
                    Node side = new Node(next.x + dx[j], next.y + dy[j]);
                    if(isOutIndex(side.x, side.y)){
                        continue;
                    }
                    if(map[side.x][side.y] == 'g'){
                        garbageSide++;
                    }
                }

                int newPath = curDist.path + 1;
                int newGarbage = curDist.garbage + garbage;
                int newGarbageSide = curDist.garbageSide + garbageSide;

                if(newGarbage < nextDist.garbage){
                    nextDist.path = newPath;
                    nextDist.garbage = newGarbage;
                    nextDist.garbageSide = newGarbageSide;
                    queue.offer(next);
                    continue;
                }
                // if(newGarbageSide < nextDist.garbageSide){
                //     nextDist.path = newPath;
                //     nextDist.garbageSide = newGarbageSide;
                //     queue.offer(next);
                //     continue;
                // }
                // if(newPath < nextDist.path){
                //     nextDist.path = newPath;
                //     queue.offer(next);
                //     continue;
                // }
            
            }
        }
    }

    public static boolean isOutIndex(int x, int y){
        if(x < 0 || x >= n || y < 0 || y >= m){
            return true;
        }
        return false;
    }

}