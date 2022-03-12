package 완전탐색.L2_소수찾기;

import java.util.*;

public class Solution {

    public static int solution(String numbers) {

        HashSet<Integer> set = new HashSet<>();
        permutation("", numbers, set);
        ArrayList<Integer> numList = new ArrayList<>(set);

        int count = 0;
        for(int number : numList){
            if(isPrime(number)){
                count++;
            }
        }

        return count;
    }

    // 재귀로 순열 만들기
    public static void permutation(String prefix, String str, HashSet<Integer> set) {
        int n = str.length();

        if (!prefix.equals("")) {
            set.add(Integer.valueOf(prefix));
        }
        for (int i = 0; i < n; i++) {
            permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n), set);
        }
    }

    public static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(solution("17"));
    }
}


/**
 * ================================================================================
 * 링크 : https://programmers.co.kr/learn/courses/30/lessons/42839?language=java
 * 성공여부 : 실패
 * 풀이시간 : 1h
 * 
 * 시간복잡도 : O(n!)
 * 메모리(테케10) : 78.9MB
 * 소요 시간 : 118.66ms
 * ================================================================================
 * 
 * 파이썬과 달리 자바에는 순열구해주는 유틸이 없음
 * 따라서 순열을 직접구현해야했는데 구현이 쉽지 않아서 답을 봄
 * 
 */