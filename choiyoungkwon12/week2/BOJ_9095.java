package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * https://www.acmicpc.net/problem/9095
 * 1,2,3 더하기
 */
public class BOJ_9095 {

    static int[] Dy;

    static void preprocess(){
        Dy = new int[15];
        // 초기값 구하기
        Dy[1] = 1;
        Dy[2] = 2;
        Dy[3] = 4;

        // 점화식을 토대로 Dy 배열 채우기
        for (int i = 4; i < 11; i++) {
            Dy[i] = Dy[i-1] + Dy[i-2] + Dy[i-3];
        }
    }

    public static void main(String[] args) throws IOException {

        preprocess();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= t; i++) {
            int n = Integer.parseInt(br.readLine());
            sb.append(Dy[n]).append("\n");
        }

        System.out.print(sb);
    }
}
