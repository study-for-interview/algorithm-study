package 완전탐색;

import java.io.*;
import java.util.*;

public class Boj22352_항체인식 {

    static int N, M;
    static int[][] bMap;
    static int[][] aMap;

    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        bMap = new int[N][M];
        aMap = new int[N][M];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                bMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
               aMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Loop1 :
        for(int x=0; x<N; x++){
            Loop2 :
            for(int y=0;  y<M; y++){
                if(bMap[x][y]!=aMap[x][y]){
                    bfs(x,y,aMap[x][y]);
                    break Loop1;
                }
            }
        }
        
        if(validCPCU()){
            System.out.println("YES");
        }else {
            System.out.println("NO");
        }


    }

    static void bfs(int x, int y, int val){
        Queue<Node> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];

        q.add(new Node(x,y));
        visited[x][y] = true;        

        while(!q.isEmpty()){

            Node now = q.poll();
            
            for(int i=0; i<4; i++){
                    int nx = now.x + dx[i];
                    int ny = now.y + dy[i];

                    if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

                    if(!visited[nx][ny]&&bMap[nx][ny]==bMap[x][y]){
                        q.add(new Node(nx,ny));
                        visited[nx][ny] = true;
                        bMap[nx][ny] = val;
                    }
                }
            
        }
        bMap[x][y] = val;
    }

    static boolean validCPCU(){

        for(int x=0; x<N; x++){
            for(int y=0;  y<M; y++){
                if(bMap[x][y]!=aMap[x][y]){
                    return false;
                }
            }
        }

        return true;
    }

    static class Node{
        int x;
        int y;
    
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }    
    }   
    
}


