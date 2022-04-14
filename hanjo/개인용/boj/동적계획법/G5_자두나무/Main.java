package 동적계획법.G5_자두나무;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        int[] trees = new int[t];
        for(int i=0; i<t; i++){
            trees[i] = Integer.parseInt(br.readLine());
        }
        
        System.out.println(solution(t, w, trees));
    }

    public static int solution(int t, int w, int[] trees){
        
        int[][] dp = new int[t+1][w+2];
        
        for(int time=1; time<t+1; time++){
            int curTree = trees[time-1];
            for(int move=1; move<w+2; move++){
                int plus = 0;
                if((move%2 == 0 && curTree == 2) || (move%2 == 1 && curTree == 1)){
                    plus = 1;
                }
                dp[time][move] = plus + Math.max(dp[time-1][move-1], dp[time-1][move]);
            }
        }

        int max = 0;
        for(int num : dp[t]){
            max = Math.max(max, num);
        }
        return max;
    }

}


/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/2240
 * 날짜 : 220316
 * 성공여부 : 성공
 * 풀이시간 : 1h
 * 
 * 시간복잡도 : ?
 * 메모리 : 14176 KB
 * 소요 시간 : 120 ms
 * ================================================================================
 * 
 * 고려해야하는 요소가 많은 동적계획법 문제
 * 풀이 방법이 약간 내려가기 문제와 비슷하다 (dp값을 누적하면서 아래로 가는데, 방향이 정해져 있음)
 * 
 * 우선 이동할 수 있는 거리의 모든 경우의수를 고려해야한다는 것을 생각해내야 한다
 * 만약 w=2이라면 이동 가능한 거리는 0, 1, 2 세가지임
 * 
 * 따라서 사람이 이동한 거리에 대한 시간별 DP를 업데이트 시켜나가면 된다
 * 여기서 이동한 거리를 통해 사람이 위치한 나무를 알아낼 수 있는데, 나무가 2개이니 2로 나누면 어떤 나무인지 알수 있음
 * 
 * 풀이 사진 : https://user-images.githubusercontent.com/71180414/158498969-3a20b2e8-c167-4515-b4c0-e38404589d71.png
 * 
 */