package 코테.십일번가인턴_2022.문제2;

class Solution {

    public int solution(int[] A) {
        // write your code in Java SE 8
        boolean[] isOn = new boolean[A.length];

        int count = 0;
        Loop: for (int num : A) {
            isOn[num - 1] = true;
            for (int i = num - 1; i >= 0; i--) {
                if (!isOn[i]) {
                    continue Loop;
                }
            }
            count++;
        }

        return count;
    }

    public static void main(String[] args) {
        // 3
        System.out.println(new Solution().solution(new int[]{2, 1, 3, 5, 4}));
        // 2
        System.out.println(new Solution().solution(new int[]{2, 3, 4, 1, 5}));
        // 3
        System.out.println(new Solution().solution(new int[]{1, 3, 4, 2, 5}));

        System.out.println(new Solution().solution(new int[]{1, 2, 3, 4, 5}));
        System.out.println(new Solution().solution(new int[]{5, 4, 3, 2, 1}));
        System.out.println(new Solution().solution(new int[]{1}));

    }
}