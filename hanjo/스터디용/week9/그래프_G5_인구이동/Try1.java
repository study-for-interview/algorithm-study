package week9.그래프_G5_인구이동;

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
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/16234
 * 성공여부 : 실패 (1시간초과)
 * 풀이시간 : 1h 30m
 * 
 * 시간복잡도 : ??
 * 메모리 : 297184 KB
 * 소요 시간 : 608 ms
 * ================================================================================
 * 
 * 시뮬레이션이 매우 빡셌던 문제..
 * 일단 문제 이해부터 잘못해서 삽질한번 했다. (Try1)
 * 
 * 배열을 그래프 탐색하는 것은 적록색약과 비슷
 * 
 */