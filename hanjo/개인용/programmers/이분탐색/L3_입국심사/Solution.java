package 이분탐색.L3_입국심사;

import java.util.*;

public class Solution {

    public static long solution(int n, int[] times) {

        long answer = 0;
        Arrays.sort(times);

        long left = 1;
        long right = (long)times[times.length - 1] * n;    // 최대 시간
        
        // 소요시간이 최소가 될때까지(left>right) 이분탐색
        while(left <= right) {
            long mid = (left + right)/2;
            long people = 0;

            for(int time : times){
                people += mid / time;  // 총 소요시간 / 시간 = 수용가능인원
            }
            
            // 수용가능인원 == n 을 만족해도 소요시간이 최소가 될때까지 왼쪽으로 간다
            if(people >= n){
                answer = mid;
                right = mid - 1;
            }
            else{
                left = mid + 1;
            }
        }
        return answer;
    }

    public static long try1(int n, int[] times) {

        long answer = 0;
        Arrays.sort(times);

        long left = 1;
        long right = (long)times[times.length - 1] * n;
        
        // 틀린점 : left == right 인 경우에도 최소가 포함됨
        while(left < right) {

            long mid = (left + right)/2;
            long people = 0;

            for(int time : times){
                people += mid / time;
            }

            System.out.println("L : " + left + " / M : " + mid + " / R : " + right + " / P : " + people);

            if(people > n){
                right = mid - 1;
            }
            // 이렇게 하면 안되는 이유 : 반드시 people == n 의 상황이 오지 않음
            else if(people == n){
                answer = mid;
                right = mid - 1;
            }
            else{
                left = mid + 1;
            }
        }
        
        return answer;
    }

    public static void main(String[] args) {

        System.out.println(solution(6, new int[]{7, 10})); // 28
        System.out.println(solution(1, new int[]{2, 2}));   // 2
        System.out.println(solution(10, new int[]{6, 8, 10}));  // 30

        // System.out.println(try1(6, new int[]{7, 10})); // 28
        // System.out.println(try1(1, new int[]{2, 2}));   // 2
        // System.out.println(try1(10, new int[]{6, 8, 10}));  // 30

    }
}


/**
 * ================================================================================
 * 링크 : https://programmers.co.kr/learn/courses/30/lessons/43238
 * 성공여부 : 실패
 * 풀이시간 : 2h 30m
 * 
 * 시간복잡도 : O( log(최대소요시간=n * 10억) )
 * 메모리(테케7) : 94.4 MB
 * 소요 시간 : 104.13 ms
 * ================================================================================
 * 
 * 기본적인 이분탐색 문제임
 * 무엇을 어떻게 이분탐색할지를 생각해내는 것이 중요한 문제
 * 손으로 그려보고 어떻게 동작하는지 이해 후 풀어보는 것이 좋음
 * 
 * 이분탐색시 L,R 이동 기준과 끝나는 시점을 잘 정해야 한다.
 * 이번 문제에서는 L,R 이동시 M보다 1보다 크거나 작게 설정하고 / L>R 시점에 끝나도록 함
 * 
 * ================================================================================
 * 
 * upper bound
 * k값 이상의 값이 최초로 등장하는 곳을 찾음
 * 
 * lower bound
 * k값 초과의 값이 최초로 등장하는 곳을 찾음
 * 
 */