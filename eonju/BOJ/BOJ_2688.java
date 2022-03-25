import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class BOJ_2688 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(bufferedReader.readLine());
        long[][] dp = new long[65][10]; // 자리수, 시작하는 수

        for (int i = 0; i <= 9; i++) {
            dp[1][i] = 1;
        }

        for (int i = 2; i <= 64; i++) {
            for (int j = 0; j <= 9; j++) {
                for (int k = j; k <= 9; k++) {
                    dp[i][j] += dp[i - 1][k];
                }
            }
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < testCase; i++) {
            int digit = Integer.parseInt(bufferedReader.readLine());
            long sum = 0;

            for (int j = 0; j <= 9; j++) {
                sum += dp[digit][j];
            }

            answer.append(sum + "\n");
        }

        System.out.println(answer);
    }
}
