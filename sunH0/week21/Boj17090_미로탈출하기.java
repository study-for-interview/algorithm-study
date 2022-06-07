package DP;

import java.util.*;
import java.io.*;

public class Boj17090_미로탈출하기 {

    static int N,M;
    static char[][] map;
    static boolean[][] visited;
    static int ans=0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N][M];
        map = new char[N][M];

        for(int i=0; i<N; i++){
            char[] line = br.readLine().toCharArray();
            for(int j=0; j<M; j++){
                map[i][j] = line[j];
            }
        }       
        
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){               
                if(dfs(i,j)) ans++;
            }
        }

        System.out.println(ans);
    }

    static boolean dfs(int r, int c){


        boolean result = false;

        if(r>=N || r<0 || c>=M || c<0) {
            return true;
        }

        if(map[r][c]=='S'){
            return true;
        }

        if(map[r][c]=='F'){
            return false;
        }

        if(visited[r][c]){
            return false;
        }

        visited[r][c] = true;
        char word = map[r][c];

        switch(word) {
            case 'U': result = dfs(r-1,c); break;
            case 'D': result = dfs(r+1,c); break;
            case 'R': result = dfs(r,c+1); break;
            case 'L': result = dfs(r,c-1); break;
        }

        map[r][c] = result? 'S':'F';

        return result;
    }

}
        



