import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = bufferedReader.readLine().split(" ");

        int w = Integer.parseInt(input[0]);
        int h = Integer.parseInt(input[1]);

        int[][][][] dp = new int[w + 1][h + 1][2][2]; // 3IDX : 방향(0: 오른쪽, 1: 아래) // 4IDX : 꺾었는지 (0: 꺾지않음, 1: 꺾음)
        for (int i = 1; i <= w; i++) {
            dp[i][1][0][0] = 1;
        }

        for (int i = 1; i <= h; i++) {
            dp[1][i][1][0] = 1;
        }

        for (int i = 2; i <= w; i++) {
            for (int j = 2; j <= h; j++) {
                dp[i][j][1][0] = (dp[i][j - 1][1][1] + dp[i][j - 1][1][0]) % 100000;
                dp[i][j][1][1] = dp[i][j - 1][0][0] % 100000;
                dp[i][j][0][0] = (dp[i - 1][j][0][0] + dp[i - 1][j][0][1]) % 100000;
                dp[i][j][0][1] = dp[i - 1][j][1][0];
            }
        }

        int result = (dp[w][h][0][0] + dp[w][h][0][1] + dp[w][h][1][0] + dp[w][h][1][1]) % 100000;
        System.out.println(result);
    }
}
