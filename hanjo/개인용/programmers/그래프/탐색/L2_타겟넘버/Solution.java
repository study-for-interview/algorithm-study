package 그래프.탐색.L2_타겟넘버;

public class Solution {

    public static int count = 0;

    public static int solution(int[] numbers, int target) {
        
        dfs(numbers, target, 1, numbers[0]);
        dfs(numbers, target, 1, -numbers[0]);
        return count;
    }

    public static void dfs(int[] numbers, int target, int depth, int sum){
        
        if(depth == numbers.length){
            if(sum == target){
                count++;
            }
            return;
        }

        dfs(numbers, target, depth+1, sum + numbers[depth]);
        dfs(numbers, target, depth+1, sum - numbers[depth]);
    }


    public static void main(String[] args) {

        System.out.println(solution(new int[]{1,1,1,1,1}, 3));  // 5
        
        count = 0;
        System.out.println(solution(new int[]{4,1,2,1}, 4));    // 2

        count = 0;
        System.out.println(solution(new int[]{1}, 1));  // 1
    }
}

/**
 * ================================================================================
 * 링크 : https://programmers.co.kr/learn/courses/30/lessons/43165
 * 성공여부 : 성공
 * 풀이시간 : 20m
 * 
 * 시간복잡도 : O(2^n)
 * 소요시간/메모리 (테케1) : 5.12ms, 80.6MB
 * ================================================================================
 * 
 * dfs 기본문제임. 그냥 완전탐색 문제
 * 그래프 생각안하고 재귀만 돌리면 풀수 있는 문제
 * 
 */