import java.io.*;
import java.util.*;


class BJ_2688 {
    static long[][] dp;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Main T = new Main();
        dp = new long[65][10];

        for (int i = 0; i < 10; i++) {
            dp[1][i] = 1;
        }

        for (int i = 2; i <= 64; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = j; k < 10; k++) {
                    dp[i][j] += dp[i - 1][k];
                }
            }
        }


        int m = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (m-- > 0) {
            int N = Integer.parseInt(br.readLine());
            long ans = 0;

            for (int i = 0; i <= 9; i++) {
                ans += dp[N][i];
            }

            sb.append(ans + "\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
