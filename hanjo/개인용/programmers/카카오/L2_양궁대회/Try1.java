package 카카오.L2_양궁대회;

import java.util.*;

class Try1 {
    
    public int n;
    public int len;
    public int[] apeach;
    public int[] ryan;

    public int max;
    public int[] answer;

    public int[] solution(int n, int[] info) {
        this.n = n;
        this.len = info.length;
        this.apeach = info;

        ryan = new int[len];
        max = 0;
        answer = new int[]{-1};
        dfs(0, 0);

        return answer;
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
                answer = ryan.clone();
                return;
            }
            if(diff == max){
                for(int i=len-1; i>=0; i--){
                    if(answer[i] < ryan[i]){
                        answer = ryan.clone();
                        return;
                    }
                }
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
        System.out.println(Arrays.toString(new Try1().solution(
            5, 
            new int[]{2,1,1,1,0,0,0,0,0,0,0}
        )));
        // [-1]
        System.out.println(Arrays.toString(new Try1().solution(
            1, 
            new int[]{1,0,0,0,0,0,0,0,0,0,0}
        )));
        // [1,1,2,0,1,2,2,0,0,0,0]
        System.out.println(Arrays.toString(new Try1().solution(
            9, 
            new int[]{0,0,1,2,0,1,1,1,1,1,1}
        )));
        // [1,1,1,1,1,1,1,1,0,0,2]
        System.out.println(Arrays.toString(new Try1().solution(
            10, 
            new int[]{0,0,0,0,0,0,0,0,3,4,3}
        )));
    }
}