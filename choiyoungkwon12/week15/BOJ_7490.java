package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Collectors;

/**
 * https://www.acmicpc.net/problem/7490
 * 0 만들기
 */
public class BOJ_7490 {

    private static final StringBuilder[] sb = new StringBuilder[10];
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        preprocess();
        for (int i = 0; i < tc; i++) {
            n = Integer.parseInt(br.readLine());
            System.out.println(sb[n]);
        }
    }

    private static void preprocess() {
        for (int i = 0; i < sb.length; i++) {
            sb[i] = new StringBuilder();
        }

        for (int i = 3; i <= 9; i++) {
            n = i;
            comb(1, 1, "1");
        }
    }

    private static void comb(int num, int len, String str) {
        if (len == n) {
            if (calculate(str) == 0) {
                sb[n].append(str).append("\n");
            }
            return;
        }
        comb(num + 1, len + 1, str + ' ' + (num + 1));
        comb(num + 1, len + 1, str + '+' + (num + 1));
        comb(num + 1, len + 1, str + '-' + (num + 1));

    }

    private static int calculate(String str) {
        str = str.replaceAll(" ", "");
        Iterator<Integer> iterator = Arrays.stream(str.split("[+,-]")).map(Integer::parseInt)
            .collect(Collectors.toList()).iterator();
        Integer result = iterator.next();

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '+') {
                result += iterator.next();
            } else if (str.charAt(i) == '-') {
                result -= iterator.next();
            }
        }

        return result;
    }

}
