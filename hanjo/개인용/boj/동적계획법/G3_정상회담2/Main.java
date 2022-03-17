package 동적계획법.G3_정상회담2;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        System.out.println(solution(n));
    }

    public static final int MOD = 987654321;

    public static long solution(int n){
        long[] dp = new long[n+1];
        
        dp[0] = 1;
        dp[1] = 0;
        dp[2] = 1;

        for(int i=4; i<=n; i+=2){
            for(int j=0; j<=i-2; j+=2){
                dp[i] += dp[j]*dp[i-j-2];
                dp[i] %= MOD;
            }
        }

        return dp[n];
    }
}

/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/1670
 * 성공여부 : 실패 (구글링)
 * 풀이시간 : 1h
 * 
 * 시간복잡도 : O(n)
 * 메모리 : 14152 KB
 * 소요 시간 : 184 ms
 * ================================================================================
 * 
 * 동적계획법 문제
 * 
 * DP는 규칙을 찾아야 하는데 머리가 안돌아가는지 규칙이 도저히 생각이 안나서 답을 봄
 * 점화식을 아래 링크를 참고해서 세우고 풀었음
 * 
 * (참고한 풀이 : https://for-development.tistory.com/118)
 * 
 */