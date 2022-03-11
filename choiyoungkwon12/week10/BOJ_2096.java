package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/2096 내려가기
 */
public class BOJ_2096 {

    static int[][] map;
    static int[][] minDp;
    static int[][] maxDp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        minDp = new int[n + 1][3];
        maxDp = new int[n + 1][3];
        map = new int[n + 1][3];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            map[i][0] = Integer.parseInt(st.nextToken());
            map[i][1] = Integer.parseInt(st.nextToken());
            map[i][2] = Integer.parseInt(st.nextToken());

            // 최대
            maxDp[i][0] = Math.max(maxDp[i - 1][0], maxDp[i - 1][1]);
            maxDp[i][0] += map[i][0];

            maxDp[i][1] = Math.max(maxDp[i - 1][0], Math.max(maxDp[i - 1][1], maxDp[i - 1][2]));
            maxDp[i][1] += map[i][1];

            maxDp[i][2] = Math.max(maxDp[i - 1][1], maxDp[i - 1][2]);
            maxDp[i][2] += map[i][2];

            // 최소
            minDp[i][0] = Math.min(minDp[i - 1][0], minDp[i - 1][1]);
            minDp[i][0] += map[i][0];

            minDp[i][1] = Math.min(minDp[i - 1][0], Math.min(minDp[i - 1][1], minDp[i - 1][2]));
            minDp[i][1] += map[i][1];

            minDp[i][2] = Math.min(minDp[i - 1][1], minDp[i - 1][2]);
            minDp[i][2] += map[i][2];
        }

        int min = Math.min(minDp[n][0], Math.min(minDp[n][1], minDp[n][2]));
        int max = Math.max(maxDp[n][0], Math.max(maxDp[n][1], maxDp[n][2]));

        System.out.println(max + " " + min);
    }
}


