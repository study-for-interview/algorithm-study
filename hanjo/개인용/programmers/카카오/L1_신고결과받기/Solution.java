package 카카오.L1_신고결과받기;

import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {

        int n = id_list.length;

        HashMap<String, Integer> users = new HashMap<>();
        for(int i=0; i<n; i++){
            users.put(id_list[i], i);
        }

        boolean[][] isReported = new boolean[n][n];
        for(String str : report){
            String[] id = str.split(" ");
            isReported[users.get(id[0])][users.get(id[1])] = true;
        }

        boolean[] isBlocked = new boolean[n];
        for(int y=0; y<n; y++){
            int count = 0;
            for(int x=0; x<n; x++){
                if(isReported[x][y]){
                    count++;
                }
            }
            if(count >= k){
                isBlocked[y] = true;
            }
        }

        int[] answer = new int[n];
        for(int x=0; x<n; x++){
            for(int y=0; y<n; y++){
                if(isReported[x][y] && isBlocked[y]){
                    answer[x]++;
                }
            }
        }

        return answer;
    }

    public static void main(String[] args){        
        // [2,1,1,0]
        System.out.println(Arrays.toString(new Solution().solution(
            new String[]{"muzi", "frodo", "apeach", "neo"},
            new String[]{"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"},
            2
        )));
        // [0,0]
        System.out.println(Arrays.toString(new Solution().solution(
            new String[]{"con", "ryan"},
            new String[]{"ryan con", "ryan con", "ryan con", "ryan con"},
            3
        )));
    }
}

/**
 * ================================================================================
 * 링크 : https://programmers.co.kr/learn/courses/30/lessons/92334?language=java
 * 날짜 : 220324
 * 성공여부 : 성공
 * 풀이시간 : 20m
 * 
 * 시간복잡도 : O(n + n + n^2 + n^2)
 * 테케 21 : (103.58ms, 172MB)
 * ================================================================================
 * 
 * 그냥 구현문제
 * 적절한 자료구조를 선택하는게 중요했음
 * 
 */