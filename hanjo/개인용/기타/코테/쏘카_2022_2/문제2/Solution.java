package 코테.쏘카_2022_2.문제2;

import java.util.*;

public class Solution {

    public int K;
    public int[] numbers;
    public int size;
    public List<List<Integer>> perms;
    public boolean[] isVisited;

    public int solution(int[] numbers, int K) {
        this.K = K;
        this.numbers = numbers;
        this.size = numbers.length;

        // 조건 만족하는 후보 순열 구하기
        perms =  new ArrayList<>();
        isVisited = new boolean[size];
        permutation(0, new ArrayList<>());

        // 후보 순회하면서 최소 변경 횟수 구하기
        int min = Integer.MAX_VALUE;
        for(var perm : perms){
            int count = 0;
            for(int i=0; i<size; i++){
                if(i != perm.get(i)){
                    Collections.swap(perm, i, perm.indexOf(i));
                    count++;
                }
            }
            min = Math.min(min, count);
        }

        return min;
    }

    public void permutation(int depth, List<Integer> perm){
        
        if(depth == size){
            for(int i=0; i<size-1; i++){
                int diff = Math.abs(numbers[perm.get(i)] - numbers[perm.get(i+1)]);
                if(diff > K){
                    return;
                }
            }
            perms.add(perm);
            return;
        }
        
        for(int i=0; i<size; i++){
            if(!isVisited[i]){
                // 백트래킹으로 찾기
                isVisited[i] = true;
                List<Integer> next = new ArrayList<>(perm);
                next.add(i);
                permutation(depth+1, next);
                isVisited[i] = false;
            }
        }
    }

    public static void main(String[] args){
        // 1
        System.out.println(new Solution().solution(
            new int[]{10, 40, 30, 20},
            20
        ));
        // 2
        System.out.println(new Solution().solution(
            new int[]{3, 7, 2, 8, 6, 4, 5, 1},
            3
        ));
    }
    
}
