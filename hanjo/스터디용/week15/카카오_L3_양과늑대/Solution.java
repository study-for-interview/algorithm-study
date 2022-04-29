package week15.카카오_L3_양과늑대;

class Solution {

    public int solution(int[] info, int[][] edges) {
        int answer = 0;
        return answer;
    }

    public static void main(String[] args) {
        // 5
        System.out.println(new Solution().solution(
            new int[]{0,0,1,1,1,0,1,0,1,0,1,1},
            new int[][]{{0,1},{1,2},{1,4},{0,8},{8,7},{9,10},{9,11},{4,3},{6,5},{4,6},{8,9}} 
        ));
        // 5
        System.out.println(new Solution().solution(
            new int[]{0,1,0,1,1,0,1,0,0,1,0},
            new int[][]{{0,1},{0,2},{1,3},{1,4},{2,5},{2,6},{3,7},{4,8},{6,9},{9,10}} 
        ));
    }

}