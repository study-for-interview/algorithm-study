package 코테.십일번가인턴_2022.문제1;

class Solution {

    public int solution(int A, int B) {
        if (A == B) {
            return 0;
        }

        String strA = String.valueOf(A);
        String strB = String.valueOf(B);
        int lenA = strA.length();
        int lenB = strB.length();

        for (int i = 0; i <= lenB - lenA; i++) {
            String sub = strB.substring(i, i + lenA);
            if (sub.equals(strA)) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        // 2
        System.out.println(new Solution().solution(53, 1953786));
        System.out.println(new Solution().solution(99, 1199));

        System.out.println(new Solution().solution(1, 1));
        System.out.println(new Solution().solution(1, 123));

        System.out.println(new Solution().solution(123, 1));
        System.out.println(new Solution().solution(100, 101));
        System.out.println(new Solution().solution(5, 123));
    }
}
