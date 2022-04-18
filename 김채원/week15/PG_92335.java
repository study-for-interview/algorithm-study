import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main {

    private static boolean isPrime(long num) {
        if (num <= 1) return false;

        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    private static String convertKbinary(int n, int k) {
        StringBuilder str = new StringBuilder();
        str.append(0);

        while (n > 0) {
            str.append(n % k);
            n /= k;
        }
        str.append(0);

        str = str.reverse();
        return str.toString();
    }

    public int solution(int n, int k) {
        int answer = 0;

        String str = convertKbinary(n, k);
        System.out.println(str);
        int cnt = 0;
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == '0') {
                if (cnt + 1 == i) {
                    cnt = i;
                    continue;
                }
                System.out.println(str.substring(cnt+1, i));
                if (isPrime(Long.parseLong(str.substring(cnt + 1, i)))) {
                    answer++;
                }
                cnt = i;
            }

        }
        System.out.println(answer);
        return answer;
    }


    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int n = 437674;
//        int k = 3;

        int n = 110011;
        int k = 10;
        T.solution(n, k);
    }
}