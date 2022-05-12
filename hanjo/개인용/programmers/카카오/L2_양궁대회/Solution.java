package 카카오.L2_양궁대회;

import java.util.*;

class Solution {
    
    public int n;
    public int len;
    public int[] apeach;
    public int[] ryan;

    public int max;
    public ArrayList<int[]> answer = new ArrayList<>();

    public int[] solution(int n, int[] info) {
        this.n = n;
        this.len = info.length;
        this.apeach = info;

        ryan = new int[len];
        max = 0;
        dfs(0, 0);

        if (answer.isEmpty()) return new int[]{-1};
        Collections.sort(answer, (o1, o2) -> {
            for (int i = 10; i >= 0; i--) {
                if (o1[i] != o2[i]) return o2[i] - o1[i];
            }
            return 0;
        });
        return answer.get(0);
    }

    public void dfs(int depth, int count){
        if(count == n){
            // 어피치와 라이언 점수 계산
            int apeachScore = 0;
            int ryanScore = 0;
            for(int i=0; i<len; i++){
                int score = 10-i;
                if(apeach[i] >= ryan[i]){
                    if(apeach[i] != 0){
                        apeachScore += score;
                    }
                }
                else{
                    ryanScore += score;
                }
            }

            // 어피치보다 점수 낮으면 빠꾸
            int diff = ryanScore - apeachScore;
            if(diff <= 0){
                return;
            }

            // 라이언 배열 업데이트
            if(diff < max){
                return;
            }
            if(diff > max){
                max = diff;
                answer.clear();
                answer.add(ryan.clone());
                return;
            }
            if (diff == max) {
                answer.add(ryan.clone());
                return;
            }
        }
        if(count > n || depth == len){
            return;
        }

        for(int i=depth; i<len; i++){
            // 라이언은 어피치보다 무조건 한발 더 맞춰야함
            int arrowNum = apeach[i]+1;
            // 남은 화살은 0점에서 다 소진
            if(i == len-1){
                arrowNum = n - count;
            }
            ryan[i] = arrowNum;
            dfs(depth+1, count + arrowNum);
            ryan[i] = 0;
        }
    }

    public static void main(String[] args) {
        // [0,2,2,0,1,0,0,0,0,0,0]
        System.out.println(Arrays.toString(new Solution().solution(
            5, 
            new int[]{2,1,1,1,0,0,0,0,0,0,0}
        )));
        // [-1]
        System.out.println(Arrays.toString(new Solution().solution(
            1, 
            new int[]{1,0,0,0,0,0,0,0,0,0,0}
        )));
        // [1,1,2,0,1,2,2,0,0,0,0]
        System.out.println(Arrays.toString(new Solution().solution(
            9, 
            new int[]{0,0,1,2,0,1,1,1,1,1,1}
        )));
        // [1,1,1,1,1,1,1,1,0,0,2]
        System.out.println(Arrays.toString(new Solution().solution(
            10, 
            new int[]{0,0,0,0,0,0,0,0,3,4,3}
        )));
    }
}

/**
 * ================================================================================
 * 링크 : https://programmers.co.kr/learn/courses/30/lessons/92342
 * 날짜 : 220415
 * 성공여부 : 반성공 (질문하기 참고)
 * 풀이시간 : 2h
 * 
 * 시간복잡도 : 
 * 메모리 : KB
 * 소요 시간 : ms
 * ================================================================================
 * 
 * 완전탐색 백트래킹 + 구현 문제
 * 
 * 어피치를 이겨먹는 모든 점수 조합을 찾고 조건에 맞게 반환하면됨
 * 동일 점수차일경우의 정렬조건때문에 테케 8, 18번이 계속 불합격.. (Try1)
 * 
 * 그래서 배열 다 저장해놓고 정렬하는거로 바꿨더니 통과함.
 * 
 */