package 투포인터.G4_로봇프로젝트;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;

        while((str = br.readLine()) != null){
            int x = Integer.parseInt(str);
            int n = Integer.parseInt(br.readLine());
            
            long[] blocks = new long[n];
            for(int i=0; i<n; i++){
                blocks[i] = Long.parseLong(br.readLine());
            }
            
            solution(x, blocks);
        }
    }

    public static void solution(int x, long[] blocks){
        long width = x * 10_000_000L;
        Arrays.sort(blocks);

        int left = 0;
        int right = blocks.length - 1;
        
        while(left < right){
            long sum = blocks[left] + blocks[right];
            
            if(sum == width){
                System.out.println("yes " + blocks[left] + " " + blocks[right]);
                return;
            }
            
            if(sum < width){
                left++;
            }
            else{
                right--;
            }
        }

        System.out.println("danger");
    }
}

/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/3649
 * 날짜 : 220323
 * 성공여부 : 성공
 * 풀이시간 : 30m
 * 
 * 시간복잡도 : O(logN)
 * 메모리 : 322556 KB
 * 소요 시간 : 2376 ms
 * ================================================================================
 * 
 * 기본적인 투포인터 + 이분탐색 문제
 * 그냥 양쪽거 더해주면서 포인터를 움직여주면됨
 * 
 * 근데 테케를 한번에 여러개 받을 수 있어서 킹받는 문제
 * 
 */