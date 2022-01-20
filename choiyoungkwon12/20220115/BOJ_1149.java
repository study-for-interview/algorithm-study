package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1149 RGB 거리 DP
 */

public class BOJ_1149 {

    static int N;

    static int[][] A;

    static int[][] Dy;

    static void pro() {
        Dy[1][1] = A[1][1];
        Dy[1][2] = A[1][2];
        Dy[1][3] = A[1][3];

        for (int i = 2; i <= N; i++) {
            Dy[i][1] = Math.min(Dy[i - 1][2], Dy[i - 1][3]) + A[i][1];
            Dy[i][2] = Math.min(Dy[i - 1][1], Dy[i - 1][3]) + A[i][2];
            Dy[i][3] = Math.min(Dy[i - 1][1], Dy[i - 1][2]) + A[i][3];
        }

        System.out.println(Math.min(Math.min(Dy[N][1], Dy[N][2]), Dy[N][3]));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N + 1][4];
        Dy = new int[N + 1][4];
        StringTokenizer st;

        for (int i = 1; i <= N; i++) {
            String s = br.readLine();
            st = new StringTokenizer(s);
            A[i][1] = Integer.parseInt(st.nextToken());
            A[i][2] = Integer.parseInt(st.nextToken());
            A[i][3] = Integer.parseInt(st.nextToken());
        }

        pro();
    }
}
