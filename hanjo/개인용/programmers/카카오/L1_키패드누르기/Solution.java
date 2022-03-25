package 카카오.L1_키패드누르기;

import java.util.*;

class Solution {

    public final String LEFT = "left";
    public final String RIGHT = "right";
    public final String CENTER = "center";
    public final List<Integer> leftNum = List.of(1, 4, 7); 
    public final List<Integer> rightNum = List.of(3, 6, 9); 

    public int leftHand = 10;
    public int rightHand = 12;
    public StringBuilder result = new StringBuilder();

    public String solution(int[] numbers, String hand) {
        
        for(int key : numbers){
            String keyStatus = statusOf(key);
            if(isLeft(keyStatus)){
                updateLeftHand(key);
            }
            else if(isRight(keyStatus)){
                updateRightHand(key);
            }
            else{
                int leftDist = getDist(key, leftHand);
                int rightDist = getDist(key, rightHand);
                if(leftDist < rightDist){
                    updateLeftHand(key);
                }
                else if(leftDist > rightDist){
                    updateRightHand(key);
                }
                else{
                    if(isLeft(hand)){
                        updateLeftHand(key);
                    }
                    else{
                        updateRightHand(key);
                    }
                }
            }
        }
        return result.toString();
    }

    public String statusOf(int num){
        if(leftNum.contains(num)){
            return LEFT;
        }
        else if(rightNum.contains(num)){
            return RIGHT;
        }
        else{
            return CENTER;
        }
    }

    public boolean isLeft(String str){
        return str.equals(LEFT);
    }

    public boolean isRight(String str){
        return str.equals(RIGHT);
    }

    public void updateLeftHand(int key){
        result.append("L");
        leftHand = key;
    }

    public void updateRightHand(int key){
        result.append("R");
        rightHand = key;
    }

    public int getDist(int key, int hand){
        if(key == 0){
            key = 11;
        }
        if(hand == 0){
            hand = 11;
        }
        int keyX = (key-1)/3;
        int keyY = (key-1)%3;
        int handX = (hand-1)/3;
        int handY = (hand-1)%3;
        return Math.abs(keyX-handX) + Math.abs(keyY-handY);
    }


    public static void main(String args[]){
        // LRLLLRLLRRL
        System.out.println(new Solution().solution(
            new int[]{1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5},
            "right"
        ));
        // LRLLRRLLLRR
        System.out.println(new Solution().solution(
            new int[]{7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2},
            "left"
        ));
        // LLRLLRLLRL
        System.out.println(new Solution().solution(
            new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0},
            "right"
        ));
    }
}

/**
 * ================================================================================
 * 링크 : https://programmers.co.kr/learn/courses/30/lessons/67256
 * 날짜 : 220325
 * 성공여부 : 성공
 * 풀이시간 : 
 * 
 * 테케 19 : (1.41ms, 75.6MB)
 * ================================================================================
 * 
 * 그냥 개빡구현문제. 시간도 오래걸렸는데 풀이도 ㄹㅇ 더럽고 맘에안듬
 * 구현 문제일 때 문제좀 잘읽자.. 또 삽질함
 * 
 * 키패드간 거리를 구할때 대각 거리를 구했는데... 그게 아니라 한칸씩 이동할때의 거리였음
 * 
 */