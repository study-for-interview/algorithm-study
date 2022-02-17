package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/14863 서울에서 경산까지
 */
public class BOJ_14863 {

    static int N, K;
    static int dp[][];
    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int t1, t2, m1, m2;
        dp = new int[N + 1][K + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            t1 = Integer.parseInt(st.nextToken());
            m1 = Integer.parseInt(st.nextToken());
            t2 = Integer.parseInt(st.nextToken());
            m2 = Integer.parseInt(st.nextToken());
            if (i == 1) {
                dp[i][t1] = m1;
                dp[i][t2] = Math.max(dp[i][t2], m2);
            } else {
                for (int j = 0; j <= K; j++) {
                    if (dp[i - 1][j] == 0) {
                        continue;
                    }
                    if (j + t1 <= K) {
                        dp[i][j + t1] = Math.max(dp[i][j + t1], dp[i - 1][j] + m1);
                    }
                    if (j + t2 <= K) {
                        dp[i][j + t2] = Math.max(dp[i][j + t2], dp[i - 1][j] + m2);
                    }
                }
            }
        }
        for (int i = 0; i <= K; i++) {
            answer = Math.max(answer, dp[N][i]);
        }
        System.out.println(answer);
    }

}
