package 동적계획법.S3_123더하기;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int t = Integer.parseInt(br.readLine());
        int[] numbers = new int[t];
        for(int i=0; i<t; i++){
            numbers[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[11];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        for(int i=4; i<11; i++){
            dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
        }

        for(int n : numbers){
            System.out.println(dp[n]);
        }
    }
}


/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/9095
 * 날짜 : 220330
 * 성공여부 : 구글링함
 * 풀이시간 : 30m
 * 
 * 시간복잡도 : 
 * 메모리 : 14148 KB
 * 소요 시간 : 120 ms
 * ================================================================================
 * 
 * 기저 사례를 바탕으로 규칙찾아서 점화식 세우는 문제
 * 일단 모든 숫자별 경우의 수를 직접 써보면서 규칙을 찾아야 함
 * 
 */