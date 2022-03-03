package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * https://www.acmicpc.net/problem/16472 고냥이
 */
public class BOJ_16472 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        String[] s = br.readLine().split("");

        int start = 0;
        int end = 0;
        int max = 0;

        if (n >= s.length) {
            System.out.println(s.length);
            return;
        }

        Map<String, Integer> map = new HashMap<>();
        int preS = start;

        while (start <= end && end < s.length) {

            if ((start != end || end == 0) && preS == start) {
                map.put(s[end], map.getOrDefault(s[end], 0) + 1);
            }

            if (map.size() <= n) {
                max = Math.max(max, end - start + 1);
                end++;
                preS = start;
            } else {
                int num = map.get(s[start]) - 1;
                if (num == 0) {
                    map.remove(s[start]);
                } else {
                    map.put(s[start], num);
                }
                start++;
            }
        }

        System.out.println(max);
    }
}
