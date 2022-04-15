package 동적계획법.G5_줄어들지않아;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        for(int i=0; i<t; i++){
            int n = Integer.parseInt(br.readLine());
            System.out.println(solution(n));
        }
    }

    public static long solution(int n){
        long dp[][] = new long[n+1][10];
        Arrays.fill(dp[1], 1);
        
        for(int x=2; x<=n; x++){
            long sum = 0;
            for(int y=0; y<10; y++){
                dp[x][y] = dp[x-1][y] + sum;
                sum += dp[x-1][y];
            }
        }
        return Arrays.stream(dp[n]).sum();
    }
}

/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/2688
 * 날짜 : 220325
 * 성공여부 : 실패 (구글링함)
 * 풀이시간 : 1h
 * 
 * 시간복잡도 : 
 * 메모리 : 14332 KB
 * 소요 시간 : 136 ms
 * ================================================================================
 * 
 * 그냥 DP문제
 * 완전탐색으로 풀었다가 시간초과 났음 (Try1)
 * n이 64까지니까 완탐은 O(10^64) 이므로 절대 안풀린다.
 * 
 * dp[x][y] -> 자릿수 x일 때 끝자리가 y가 될 수 있는 경우의 수
 * 점화식은 dp[x][y] = dp[x-1][0~y까지 합]
 * ex) dp[3][2] = dp[2][0] + dp[2][1] + dp[2][2] 
 * 
 */