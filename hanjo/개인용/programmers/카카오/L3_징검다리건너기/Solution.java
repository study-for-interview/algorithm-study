package 카카오.L3_징검다리건너기;

public class Solution {
    
    public static int solution(int[] stones, int k) {

        int left = 1;
        int right = findMax(stones) + 1;  // 0인 돌도 있을 수 있으므로 +1 해줘야한다

        // 건널수 없는 사람 수의 최소를 구한다 -> lower bound
        while(left < right){
            int mid = (left + right) / 2;

            // 건널수 있다면 숫자 키움
            if(canPass(stones, k, mid)){
                left = mid + 1;
            }
            else{
                right = mid; 
            }
        }
        // 건널수 있는 사람 수의 최대 = (건널수 없는 사람 수의 최소) - 1
        return left - 1;
    }

    public static int findMax(int[] arr){
        int max = 0;
        for(int num : arr){
            if(num > max){
                max = num;
            }
        }
        return max;
    }

    public static boolean canPass(int[] stones, int k, int mid){

        int count = 0;

        // 연속된 '음수' 길이가 k개일때부터 건널 수 없음
        for(int stone : stones){
            if(stone - mid < 0){
                count ++;
            } else{
                count = 0;
                continue;
            }
            if(count == k){
                return false;
            }
        }
        
        return true;
    }

    public static void main(String[] args){
        // 3
        System.out.println(solution(new int[]{2, 4, 5, 3, 2, 1, 4, 2, 5, 1}, 3));
        // 1
        System.out.println(solution(new int[]{1}, 2));
        // 0
        System.out.println(solution(new int[]{0}, 1));
    }
}

/**
 * ================================================================================
 * 링크 : https://programmers.co.kr/learn/courses/30/lessons/64062
 * 성공여부 : 실패
 * 풀이시간 : 2h
 * 
 * 시간복잡도 : 
 * ================================================================================
 * 
 * Try1에서 이분탐색없이 풀었다가 효율성 시간초과나서 다시 풀었음
 * 
 * 어떤것을 이분탐색할지, 그리고 left, mid, right가 의미하는 바가 뭔지를 잘 알고 풀어야했음
 * parametric search + lower bound로 풀었음
 * 
 * < 이분탐색 사고 과정 >
 * 1. 어떤것을 이분탐색할지? 그것이 정렬되어 있는지
 * 2. left, right의 초기값을 어떻게 설정할지
 * 3. 일반 이분탐색 / lower bound / upper bound 중 하나 선택
 * 4. 이분탐색 종료시점을 어떻게 설정할지? (left<right / left<=right)
 * 
 */
