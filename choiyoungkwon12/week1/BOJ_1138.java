package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1138
 * 한 줄로 서기
 */

public class BOJ_1138 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] answer = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int count = Integer.parseInt(st.nextToken());
            for (int j = 1; j <= n; j++) {
                if (count == 0) {
                    if (answer[j] == 0) {
                        answer[j] = i;
                        break;
                    }
                } else if (answer[j] == 0) {
                    count--;
                }
            }
        }

        for (int i = 1; i < answer.length; i++) {
            System.out.print(answer[i] + " ");
        }
    }
}
