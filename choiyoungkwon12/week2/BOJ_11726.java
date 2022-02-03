package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * https://www.acmicpc.net/problem/11726
 * 2×n 타일링
 * DP
 */
public class BOJ_11726 {

    static int N;
    static int[] Dy;

    static void pro(){
        Dy = new int[1005];

        Dy[1] = 1;
        Dy[2] = 2;
        for (int i = 3; i < Dy.length; i++) {
            Dy[i] = (Dy[i-1] + Dy[i-2]) % 10007;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        pro();
        System.out.print(Dy[n]);
    }
}
