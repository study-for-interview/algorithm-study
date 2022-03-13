package 동적계획법.G5_평범한배낭;

import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] bags = new int[n+1][2];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            bags[i][0] = Integer.parseInt(st.nextToken());  // weight
            bags[i][1] = Integer.parseInt(st.nextToken());  // value
        }

        System.out.println(solution(k, bags));
    }


    public static int solution(int k, int[][] bags){

        int n = bags.length;
        int[][] dp = new int[n+1][k+1];

        for(int x=1; x<=n; x++){

            // 현재 무게 및 가중치
            int curW = bags[x-1][0];
            int curV = bags[x-1][1];

            for(int y=1; y<=k; y++){

                // 이전 물건의 누적값
                int beforeDp = dp[x-1][y];
                
                if(curW <= y){
                    // 남는 무게에 해당하는 누적값을 찾음
                    int remainW = y- curW;
                    int newDp = curV + dp[x-1][remainW];

                    // 이전 누적값과 새 누적값 중 더 큰것을 넣음
                    dp[x][y] = Math.max(beforeDp, newDp);
                }
                else{
                    dp[x][y] = beforeDp;
                }
            }
        }
        return dp[n][k];
    }
}

/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/12865
 * 성공여부 : 실패 - 구글링함
 * 풀이시간 : 2h
 * 
 * 시간복잡도 : ??
 * 메모리 : 54152 KB
 * 소요 시간 : 196 ms
 * ================================================================================
 * 
 * 이 문제도 LCS 문제와 같이 DP 표를 그려보면서 규칙을 찾아 풀어야하는 문제
 * DP 행렬이 비슷하게 생겼다. 원래 이런건가?
 * 
 * 하지만 어려운점
 * 어떻게 값을 누적하면서 표를 그릴지 생각이 떠오르지 않음
 * 그리고 거기서 규칙을 찾는것도 어려움
 * 아직 감이 전혀 오지 않는다
 * 
 * 내가 그린 표
 * https://user-images.githubusercontent.com/71180414/153104361-75fd8d24-d6c5-43a3-aff2-b9b2ea90d234.png
 * 
 */