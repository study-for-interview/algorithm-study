//https://leetcode.com/problems/range-addition-ii/
package leetcode.Easy_598;

public class Solution {
    public static int maxCount(int m, int n, int[][] ops) {
        int minX = m;
        int minY = n;
        for(int[] op : ops){
            if(op[0] < minX)
                minX = op[0];
            if(op[1] < minY)
                minY = op[1];
        }
        return minX*minY;
    }

    public static void main(String[] args) {
        int m = 3;
        int n = 3;
        int[][] ops = {{2,2}, {3,3}};
        System.out.println(maxCount(m, n, ops));
    }
}
