package 카카오.L1_다트게임;

import java.util.*;

class Solution {

    public final Map<Character, Integer> bonus = Map.of('S', 1, 'D', 2, 'T', 3);

    public int solution(String dartResult) {

        dartResult = dartResult.replace("10", "x");

        int score = 0;
        int beforeScore = 0;
        int scoreSum = 0;

        for(char c : dartResult.toCharArray()){
            // 점수
            if(Character.isDigit(c) || c == 'x'){
                beforeScore = score;
                scoreSum += score;
                score = c == 'x' ? 10 : c - '0';
                continue;
            }
            // 보너스
            if(c == 'S' || c == 'D' || c == 'T'){
                score = (int)Math.pow(score, bonus.get(c));
                continue;
            }
            // 옵션
            if(c == '*'){
                scoreSum += beforeScore;
                score *= 2;
                continue;
            }
            if(c == '#'){
                score *= -1;
            }
        }
        return scoreSum + score;
    }

    public static void main(String[] args) {
        // 37
        System.out.println(new Solution().solution("1S2D*3T"));
        // 9
        System.out.println(new Solution().solution("1D2S#10S"));
        // 3
        System.out.println(new Solution().solution("1D2S0T"));
        // 23
        System.out.println(new Solution().solution("1S*2T*3S"));
        // 5
        System.out.println(new Solution().solution("1D#2S*3S"));
        // -4
        System.out.println(new Solution().solution("1T2D3D#"));
        // 59
        System.out.println(new Solution().solution("1D2S3T*"));
    }
}

/**
 * ================================================================================
 * 링크 : https://programmers.co.kr/learn/courses/30/lessons/17682
 * 날짜 : 220407
 * 성공여부 : 성공
 * 풀이시간 : 30m
 * 
 * 시간복잡도 : ?
 * 테케 25 : (0.13ms, 75.9MB)
 * ================================================================================
 * 
 * 그냥 구현문제
 * 
 * 까다로웠던 것
 * - 스타상(*)때문에 이전 점수를 저장하고 써먹어야함
 * - 10점이 포함되어 있어서 초반에 문자열을 바꿔줘야함
 * 
 */