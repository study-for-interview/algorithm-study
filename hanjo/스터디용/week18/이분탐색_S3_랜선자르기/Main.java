package week18.이분탐색_S3_랜선자르기;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        long[] lan = new long[n];
        for(int i=0; i<n; i++){
            lan[i] = Integer.parseInt(br.readLine());
        }
        
        System.out.println(solution(k, lan));
    }
    
    public static long solution(int k, long[] lan){
        
        int n = lan.length;
        Arrays.sort(lan);
        
        long left = 0;
        long right = lan[n-1]+1;    // +1 주는게 포인트..

        while(left < right){
            long mid = (left + right)/2;
            
            if(isValid(mid, k, lan)){
                left = mid + 1;
            }
            else{
                right = mid;
            }
        }

        return left - 1;
    }

    public static boolean isValid(long mid, int k, long[] lan){
        int count = 0;
        for(long num : lan){
            count += num/mid;
        }
        return count >= k;
    }
}


/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/1654
 * 날짜 : 220513
 * 성공여부 : 성공
 * 풀이시간 : 30m
 * 
 * 시간복잡도 : O(logN)
 * 메모리 : 17656 KB
 * 소요 시간 : 224 ms
 * ================================================================================
 * 
 * 이분탐색 lowerbound
 * long 타입 써야함
 * 
 */