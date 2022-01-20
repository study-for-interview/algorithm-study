package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * https://www.acmicpc.net/problem/2579 계단오르기 DP
 */
public class BOJ_2579 {

    static int[][] Dy;
    static int[] A;
    static int n;

    static void pro() {
        // 초기값 구하기
        Dy[1][0] = 0;
        Dy[1][1] = A[1];

        if (n >= 2) {
            Dy[2][0] = A[2];
            Dy[2][1] = A[2] + A[1];
        }

        for (int i = 3; i <= n; i++) {
            Dy[i][0] = Math.max(Dy[i - 2][0] + A[i], Dy[i - 2][1] + A[i]);
            Dy[i][1] = Dy[i - 1][0] + A[i];
        }

        int ans = Math.max(Dy[n][0], Dy[n][1]);

        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        Dy = new int[n + 5][n + 5];
        A = new int[n + 5];
        for (int i = 1; i <= n; i++) {
            int num = Integer.parseInt(br.readLine());
            A[i] = num;
        }
        pro();
    }
}
