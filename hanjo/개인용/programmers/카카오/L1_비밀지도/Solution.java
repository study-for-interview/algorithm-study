package 카카오.L1_비밀지도;

import java.util.Arrays;

class Solution {

    public int n;

    public String[] solution(int n, int[] arr1, int[] arr2) {
        this.n = n;

        int[][] map1 = fill(arr1);
        int[][] map2 = fill(arr2);
        String[] answer = combine(map1, map2);

        return answer;
    }

    public int[][] fill(int[] arr){
        int[][] map = new int[n][n];
        for(int x=0; x<n; x++){
            int num = arr[x];
            for(int y=n-1; y>=0; y--){
                map[x][y] = num % 2;
                num /= 2;
            }
        }
        return map;
    }

    public String[] combine(int[][] map1, int[][] map2){
        String[] result = new String[n];
        for(int x=0; x<n; x++){
            String row = "";
            for(int y=0; y<n; y++){
                if(map1[x][y] == 0 && map2[x][y] == 0){
                    row += " ";
                }
                else{
                    row += "#";
                }
            }
            result[x] = row;
        }
        return result;
    }

    public static void main(String[] args){
        // ["#####","# # #", "### #", "# ##", "#####"]
        System.out.println(Arrays.toString(new Solution().solution(
            5,
            new int[]{9, 20, 28, 18, 11},
            new int[]{30, 1, 21, 17, 28}
        )));
        // ["######", "### #", "## ##", " #### ", " #####", "### # "]
        System.out.println(Arrays.toString(new Solution().solution(
            6,
            new int[]{46, 33, 33 ,22, 31, 50},
            new int[]{27 ,56, 19, 14, 14, 10}
        )));
    }
}


/**
 * ================================================================================
 * 링크 : https://programmers.co.kr/learn/courses/30/lessons/17681
 * 날짜 : 220419
 * 성공여부 : 성공
 * 풀이시간 : 20m
 * 
 * 테케1 : (2.31ms, 76.2MB)
 * ================================================================================
 * 
 * 진법계산 + 매우쉬운 구현문제
 * 하지만 비트연산을 활용해 푸는게 더 맞는 풀이인듯. (Answer 참고)
 * 
 */