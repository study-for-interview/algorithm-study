package 코테.라인_2022.문제4;

import java.util.Arrays;

class Solution {
    public int solution(int[] arr, int[] brr) {
        int n = arr.length;
        int[] diff = new int[n];

        for(int i=0; i<n; i++){
            diff[i] = arr[i] - brr[i];
        }

        System.out.println(Arrays.toString(diff));

        int i=0;
        int count = 0;
        while(!isClear(diff)){
            if(i == n-1){
                break;
            }
            if(diff[i] == 0){
                i++;
                continue;
            }
            diff[i+1] += diff[i];
            diff[i] = 0;
            i++;
            count++;
        }

        return count;
    }

    public boolean isClear(int[] diff){
        for(int num : diff){
            if(num != 0){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // 3
        System.out.println(new Solution().solution(
            new int[]{3, 7, 2, 4},
            new int[]{4, 5, 5, 2}
        ));
    }
}