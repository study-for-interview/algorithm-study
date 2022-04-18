package Programmers;

/**
 * https://programmers.co.kr/learn/courses/30/lessons/92335 k진수에서 소수 개수 구하기
 */
public class Solution92335 {

    public static void main(String[] args) {
        Solution92335 s = new Solution92335();
        int solution = s.solution(437674, 3);
        System.out.println(solution);
    }

    public int solution(int n, int k) {
        int answer = 0;
        String s = convertToKNotation(n, k).toString();
        int i, j;

        for (i = 0; i < s.length(); i = j) {
            for (j = i + 1; j < s.length() && s.charAt(j) != '0'; j++);
            if (isPrime(Long.parseLong(s.substring(i, j)))) {
                answer++;
            }
        }
        return answer;
    }

    private boolean isPrime(long n) {
        if (n <= 1) {
            return false;
        } else if (n == 2) {
            return true;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    private StringBuilder convertToKNotation(int n, int k) {
        StringBuilder s = new StringBuilder();
        while (n > 0) {
            s.insert(0, n % k);
            n /= k;
        }
        return s;
    }
}
