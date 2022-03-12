package 완전탐색.B2_일곱난쟁이;

import java.io.*;
import java.util.*;

public class Main {

    public static final int N = 9;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[] height = new int[N];
        int sum = 0;

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            height[i] = h;
            sum += h;
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(i != j && sum - height[i] - height[j] == 100){
                    height[i] = 101;
                    height[j] = 101;
                    Arrays.sort(height);
                    for(int k=0; k<7; k++){
                        System.out.println(height[k]);
                    }
                    return;
                }
            }
        }        
    }
}

/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/2309
 * 성공여부 : 성공
 * 풀이시간 : 10m
 * 
 * 시간복잡도 : O(N^3) / 정렬 - O(n*log(n))
 * 메모리 : 14148 KB
 * 소요 시간 : 128 ms
 * ================================================================================
 * 
 * 그냥 몸풀기용 for문 문제
 * 9명의 키 총합을 구하고 2명을 골라 키를 뺐을때 100나오면 그 2명이 스파이임
 * 
 */
