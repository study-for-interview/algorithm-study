package 동적계획법.S1_RGB거리;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[][] map = new int[n][3];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            map[i][0] = Integer.parseInt(st.nextToken());
            map[i][1] = Integer.parseInt(st.nextToken());
            map[i][2] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(map));
    }

    public static int solution(int map[][]){
        int n = map.length;
        int[][] dp = new int[n][3];
        for(int i=0; i<3; i++){
            dp[0][i] = map[0][i];
        }

        for(int i=1; i<n; i++){
            dp[i][0] = map[i][0] + Math.min(dp[i-1][1], dp[i-1][2]);
            dp[i][1] = map[i][1] + Math.min(dp[i-1][0], dp[i-1][2]);
            dp[i][2] = map[i][2] + Math.min(dp[i-1][0], dp[i-1][1]);
        }
        
        int min = Math.min(dp[n-1][0], Math.min(dp[n-1][1], dp[n-1][2]));
        return min;
    }
}

/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/1149
 * 날짜 : 220314
 * 성공여부 : 성공
 * 풀이시간 : 10m
 * 
 * 시간복잡도 : 
 * 메모리 : 14564 KB
 * 소요 시간 : 140 ms
 * ================================================================================
 * 
 * DP문제. '내려가기' 문제의 하위호환 문제임
 * 동작 방식은 내려가기와 동일한데, n 제한이 1000이길래 이번엔 DP 배열을 완전히 채우도록 구현했음
 * 
 */