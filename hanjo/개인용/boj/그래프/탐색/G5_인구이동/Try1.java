package 그래프.탐색.G5_인구이동;

import java.io.*;
import java.util.*;

public class Try1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        
        int[][] map = new int [n][n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solution(l, r, map));
    }

    public static final int[] dx = {-1, 0, 1, 0};
    public static final int[] dy = {0, 1, 0, -1};
     
    public static int solution(int l, int r, int[][] map){
        int n = map.length;
        int year = 0;

        while(true){

            boolean[][] isUnion = new boolean[n][n];
            int unionSize = 0;
            int populationSum = 0;
        
            for(int x=0; x<n; x++){
                for(int y=0; y<n; y++){
                    for(int i=0; i<4; i++){
                        int nextX = x + dx[i];
                        int nextY = y + dy[i];
                        if(nextX < 0 || nextX >= n || nextY < 0 || nextY >= n){
                            continue;
                        }
                        int diff = Math.abs(map[x][y] - map[nextX][nextY]);
                        if(!isUnion[nextX][nextY] && l <= diff && diff <= r){
                            isUnion[nextX][nextY] = true;
                            populationSum += map[nextX][nextY];
                            unionSize++;
                        }
                    }
                }
            }

            if(unionSize == 0){
                break;
            }

            int unionAvg = populationSum / unionSize;
            for(int x=0; x<n; x++){
                for(int y=0; y<n; y++){
                    if(isUnion[x][y]){
                        map[x][y] = unionAvg;
                    }
                }
            }
            
            year++;
        }

        return year;
    }
    
}

/**
 * 
 * 연합이 여러개 생길수도 있다는 것을 간과한 풀이.
 * 문제를 정확하게 이해하지 못하고 풀어서 그래프 탐색을 사용하지 않음
 * 
 */