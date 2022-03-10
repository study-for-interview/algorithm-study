import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());

        long[] dp = new long[100001];
        final long div = 987654321;

        dp[0] = 1;
        dp[2] = 1;

        for (int i = 4; i <= N; i += 2) {
            for (int j = 0; j <= i - 2; j += 2) {
                dp[i] += dp[j] * dp[i - j - 2];
                dp[i] %= div;
            }
        }

        System.out.println(dp[N]);
    }
}
