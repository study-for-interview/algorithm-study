package 탐욕법.G4_카드정렬하기;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] sets = new int[n];
        for(int i=0; i<n; i++){
            sets[i] = Integer.parseInt(br.readLine());
        }
        
        System.out.println(solution(sets));
    }

    public static int solution(int[] sets){

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for(int set : sets){
            queue.offer(set);
        }
        
        int sum = 0;
        while(queue.size() > 1){
            int newSet = queue.poll() + queue.poll();
            sum += newSet;
            queue.offer(newSet);
        }
        return sum;
    }
    
}

/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/1715
 * 날짜 : 220401
 * 성공여부 : 성공
 * 풀이시간 : 30m
 * 
 * 시간복잡도 : 
 * 메모리 : 28300 KB
 * 소요 시간 : 384 ms
 * ================================================================================
 * 
 * 탐욕법 문제
 * 
 * 1 3 5 7 8 이 주어지는 경우를 시뮬레이션 해보고 규칙을 찾아냈음
 *  4  5 7 8 
 *    9  7 8 -> 여기서 선택을 해줘야함
 * 
 * 우선순위큐를 사용하면 바로 해결됐음
 * 
 */