package 완전탐색.G4_별자리찾기;

import java.io.*;
import java.util.*;

public class Try1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int m = Integer.parseInt(br.readLine());
        int[][] target = new int[m][2];
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            target[i][0] = Integer.parseInt(st.nextToken());
            target[i][1] = Integer.parseInt(st.nextToken());
        }

        int n = Integer.parseInt(br.readLine());
        int[][] photo = new int[n][2];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            photo[i][0] = Integer.parseInt(st.nextToken());
            photo[i][1] = Integer.parseInt(st.nextToken());
        }
        
        solution(target, photo);
    }

    public static int N;
    public static int M;
    public static int[][] gTarget;
    public static int[][] gPhoto;
    public static boolean[][] isVisited;
    public static final int[] dx = {-1, 0, 1, 0};
    public static final int[] dy = {0, 1, 0, -1};
    public static int limitX = 0;
    public static int limitY = 0;
    public static int X;
    public static int Y;

    public static void solution(int[][] target, int[][] photo){
        M = target.length;
        N = photo.length;
        gTarget = target;
        gPhoto = photo;

        for(int[] p : photo){
            limitX = Math.max(limitX, p[0]);
            limitY = Math.max(limitY, p[1]);
        }

        isVisited = new boolean[1000000][1000000];

        dfs(0, 0);

        System.out.println(X + " " + Y);
    }

    public static void dfs(int x, int y){

        boolean isExist = false;
        for(int[] t : gTarget){
            isExist = false;
            int curX = t[0]+x;
            int curY = t[1]+y;
            if(curX < 0 || curX > limitX || curY < 0 || curY > limitY){
                return;
            }
            for(int[] p : gPhoto){
                if(curX == p[0] && curY == p[1]){
                    isExist = true;
                }
            }
            if(!isExist){
                break;
            }
        }
        if(isExist){
            X = x;
            Y = y;
            return;
        }

        for(int i=0; i<4; i++){
            int nextX = x + dx[i];
            int nextY = y + dy[i];
            if(!isVisited[nextX][nextY]){
                isVisited[nextX][nextY] = true;
                dfs(nextX, nextY);
            }
        }
    }
    
}

/**
 * 1. target 전체 좌표를 dfs로 모두 이동시켜봄
 * 2. photo와 비교해서 딱 맞으면 끝
 * 
 * --> 실패함. dfs의 끝 기준이 명확하지 않음
 */