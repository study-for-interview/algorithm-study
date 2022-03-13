package 완전탐색.S2_도영이가만든음식;

import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        int[][] ingredients = new int[n][2];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            ingredients[i][0] = Integer.parseInt(st.nextToken());
            ingredients[i][1] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(ingredients));
    }

    public static int min = Integer.MAX_VALUE;

    public static int solution(int[][] ingredients){
        
        int len = ingredients.length;
        boolean[] visited = new boolean[len];
        combination(ingredients, visited, 0);

        return min;
    }

    public static void combination(int[][] ingredients, boolean[] visited, int depth){

        int len = ingredients.length;

        if(depth == len){
            int sourSum = 1;
            int bitterSum = 0;
            boolean isInput = false;  // 재료가 들어갔는지 여부 판단
            for(int i=0; i<len; i++){
                if(visited[i]){
                    sourSum *= ingredients[i][0];
                    bitterSum += ingredients[i][1];
                    isInput = true;
                }
            }
            int result = Math.abs(sourSum-bitterSum);
            if(isInput && result < min){
                min = result;
            }
            return;
        }

        visited[depth] = true;
        combination(ingredients, visited, depth+1);
        visited[depth] = false;
        combination(ingredients, visited, depth+1);
    }
}

/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/2961
 * 성공여부 : 실패
 * 풀이시간 : 1H
 * 
 * 시간복잡도 : O(2^n)
 * 메모리 : 14128 KB
 * 소요 시간 : 128 ms
 * ================================================================================
 * 
 * dfs를 활용한 조합을 구하는 문제
 * 이번엔 조합 구현시 재귀방식을 사용했지만, 백트래킹 방식도 있으므로 공부할 필요 있음
 * 
 * 또 visited 배열을 사용해 방문 기록을 모두 남긴 후 마지막 depth에서 한번에 계산했으나
 * 굳이 이렇게 하지 않고 탐색하면서 신맛과 쓴맛의 합을 쌓아도 됐을것 같음
 * 
 */