package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * https://www.acmicpc.net/problem/16120 ppap
 */
public class BOJ_16120 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String result = checkedPPAP(s);
        System.out.println(result);
    }

    private static String checkedPPAP(String s) {
        boolean flag = false;
        int count = 0;
        if (s.charAt(s.length() - 1) == 'P') {
            flag = true;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == 'P') {
                    count++;
                } else if (c == 'A') {
                    if (s.charAt(i + 1) != 'P' || count < 2) {
                        flag = false;
                        break;
                    }
                    count -= 2;
                }
            }
            if (count != 1) {
                flag = false;
            }
        }
        return flag ? "PPAP" : "NP";
    }
}
