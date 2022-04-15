package 동적계획법.G4_내리막길;

import java.io.*;
import java.util.*;

public class Try1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];
        for(int x=0; x<n; x++){
            st = new StringTokenizer(br.readLine());
            for(int y=0; y<m; y++){
                map[x][y] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solution(map));
    }

    public static class Node{
        public int x;
        public int y;
        public int height;
        
        public Node(int x, int y, int height){
            this.x = x;
            this.y = y;
            this.height = height;
        }
    }

    public static final int[] dx = {-1, 0, 1, 0};
    public static final int[] dy = {0, 1, 0, -1};
    public static int n;
    public static int m;

    public static int solution(int[][] map){
        n = map.length;
        m = map[0].length;
        return bfs(map);
    }

    public static int bfs(int[][] map){

        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(0, 0, map[0][0]));

        int count = 0;

        while(!queue.isEmpty()){
            Node cur = queue.poll();

            if(cur.x == n-1 && cur.y == m-1){
                count++;
            }
            for(int i=0; i<4; i++){
                Node next = new Node(cur.x + dx[i], cur.y + dy[i], 0);
                if(next.x < 0 || next.x >= n || next.y < 0 || next.y >= m){
                    continue;
                }
                next.height = map[next.x][next.y];

                if(next.height < cur.height){
                    queue.offer(next);
                }
            }
        }
        return count;
    }
    
}

// 메모리 초과