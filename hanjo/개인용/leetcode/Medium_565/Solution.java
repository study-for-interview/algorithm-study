//https://leetcode.com/problems/array-nesting/

package leetcode.Medium_565;

import java.util.HashSet;
import java.util.Set;

public class Solution {

    public static int arrayNesting(int[] nums) {
        int max = 0;
        Set<Integer> visited= new HashSet<>();
        for(int k : nums){
            int idx = k;
            int cnt = 0;
            while(!visited.contains(nums[idx])){
                visited.add(nums[idx]);
                idx = nums[idx];
                cnt ++;
            }
            if(cnt > max)
                max = cnt;
        }
        return max;
    }

    public static void main(String[] args) {

        int[] nums = {5,4,0,3,1,6,2};
        System.out.println(arrayNesting(nums));

    }


}
