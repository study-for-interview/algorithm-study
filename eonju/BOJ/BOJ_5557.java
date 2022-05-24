import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    private static int[] numbers;
    private static long[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int numberCnt = Integer.parseInt(bufferedReader.readLine());

        numbers = new int[numberCnt];

        String[] input = bufferedReader.readLine().split(" ");
        for (int i = 0; i < input.length; i++) {
            numbers[i] = Integer.parseInt(input[i]);
        }

        dp = new long[21];
        dp[numbers[0]] = 1;
        solve(1);
        System.out.println(dp[numbers[numbers.length - 1]]);
    }

    public static void solve(int idx) {
        if (idx == numbers.length - 1) {
            return;
        }

        long temp[] = new long[21];
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] != 0) {
                if (i + numbers[idx] <= 20) {
                    temp[i + numbers[idx]] += dp[i];
                }
                if (i - numbers[idx] >= 0) {
                    temp[i - numbers[idx]] += dp[i];
                }
            }
        }

        dp = temp.clone();
        solve(idx + 1);
    }

}
