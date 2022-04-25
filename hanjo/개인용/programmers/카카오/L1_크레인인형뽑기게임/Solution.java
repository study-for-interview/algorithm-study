package 카카오.L1_크레인인형뽑기게임;

import java.util.*;

class Solution {

    public int solution(int[][] board, int[] moves) {
        int n = board.length;

        List<Stack<Integer>> cols = new ArrayList<>();
        for(int y=0; y<n; y++){
            Stack<Integer> col = new Stack<>();
            for(int x=n-1; x>=0; x--){
                if(board[x][y] != 0){
                    col.push(board[x][y]);
                }
            }
            cols.add(col);
        }

        Stack<Integer> bucket = new Stack<>();
        int count = 0;
        for(int move : moves){
            Stack<Integer> col = cols.get(move-1);
            if(col.isEmpty()){
                continue;
            }
            int top = col.pop();
            if(bucket.isEmpty() || top != bucket.peek()){
                bucket.push(top);
            }
            else{
                bucket.pop();
                count+=2;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        // 4
        System.out.println(new Solution().solution(
            new int[][]{
                {0,0,0,0,0},
                {0,0,1,0,3},
                {0,2,5,0,1},
                {4,2,4,4,2},
                {3,5,1,3,1},
            },
            new int[]{1,5,3,5,1,2,1,4}
        ));
    }
}

/**
 * ================================================================================
 * 링크 : https://programmers.co.kr/learn/courses/30/lessons/64061
 * 날짜 : 220420
 * 성공여부 : 성공
 * 풀이시간 : 10m
 * 
 * 시간복잡도 : 
 * 테케4 : (1.51ms, 73.3MB)
 * ================================================================================
 * 
 * 스택을 활용한 구현문제
 * 
 */