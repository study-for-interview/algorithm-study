package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * https://www.acmicpc.net/problem/11057
 * 오르막수
 * DP
 */
public class BOJ_11057 {

    static int[][] Dy;
    static int N;
    static int[] A;

    static void pro() {
        for (int i = 0; i <= 9; i++) {
            Dy[1][i] = 1;
        }

        for (int len = 2; len <= N; len++) {
            for (int num = 0; num <= 9; num++) {
                for (int prev = 0; prev <= num; prev++) {
                    Dy[len][num] += Dy[len -1][prev];
                    Dy[len][num] %= 10007;
                }
            }
        }
        
        int ans = 0;
        for (int i = 0; i <= 9; i++) {
            ans += Dy[N][i];
            ans %= 10007;
        }
        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        Dy = new int[N + 1][10];
        A = new int[N + 1];
        pro();
    }
}
