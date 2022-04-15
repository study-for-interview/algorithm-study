package 그래프.최단경로.G2_일요일아침데이트;

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
            for(int j=0; j<m; j++){
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
            for(int y=0; y<m; y++){
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
                int garbageSide = 0;
                if(map[next.x][next.y] == 'g'){
                    garbage++;
                }
                if(map[next.x][next.y] == '.'){
                    for(int j=0; j<4; j++){
                        Node side = new Node(next.x + dx[j], next.y + dy[j]);
                        if(isOutIndex(side.x, side.y)){
                            continue;
                        }
                        if(map[side.x][side.y] == 'g'){
                            garbageSide++;
                            break;
                        }
                    }
                }

                int newPath = curDist.path + 1;
                int newGarbage = curDist.garbage + garbage;
                int newGarbageSide = curDist.garbageSide + garbageSide;

                // 우선순위대로 결정
                if(newGarbage <= nextDist.garbage){
                    if(newGarbage < nextDist.garbage){
                        nextDist.path = newPath;
                        nextDist.garbage = newGarbage;
                        nextDist.garbageSide = newGarbageSide;
                        queue.offer(next);
                    }
                    else{
                        if(newGarbageSide < nextDist.garbageSide){
                            nextDist.path = newPath;
                            nextDist.garbage = newGarbage;
                            nextDist.garbageSide = newGarbageSide;
                            queue.offer(next);
                        }
                    }
                }
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


/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/1445
 * 날짜 : 220315
 * 성공여부 : 실패 (1시간초과)
 * 풀이시간 : 오래걸림
 * 
 * 시간복잡도 : 
 * 메모리 : 23536 KB
 * 소요 시간 : 200 ms
 * ================================================================================
 * 
 * 가중치 기준이 2개인 다익스트라 문제
 * 
 * 평범한 다익스트라 문제는 가중치가 최단경로 하나만 있기에 경로만 누적하면 되지만
 * 이 문제는 두가지 가중치를 누적해야 했다. 게다가 두 가중치에 우선순위가 있음..;
 * 
 * 따라서 평소에 최단경로만 업데이트시키는 dist 배열에 여러 가중치를 담는 객체를 넣어줌
 * (처음엔 경로까지 비교해야하는건줄 알고 세개를 담았는데.. 경로는 필요없었음)
 * 
 * 그렇게 다익스트라를 돌리려 했는데, 일단 우선순위큐를 사용하지 못했다. 
 * 보통 우선순위큐에 효율적인 간선 순서대로 담기는데, 이번엔 Node 클래스에까지 우선순위를 구현해야 해서 걍 안했다.
 * 
 * 다음 정점의 가중치(쓰레기 밟기랑 옆 지나가기) 구하는 것은 걍 빡구현이였고
 * 제일 어려웠던것은 dist 배열을 우선순위대로 업데이트 시키는 것이였음
 * 여기서 뇌정지가 와서 삽질하다가 어찌저찌 스스로 풀긴했는데 업데이트 로직이 뭔가 완벽히 이해되진 않아서 찝찝함
 * 
 */