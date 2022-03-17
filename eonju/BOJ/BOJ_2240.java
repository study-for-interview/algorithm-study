import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = bufferedReader.readLine().split(" ");

        int maxCnt = Integer.parseInt(input[0]);
        int maxMove = Integer.parseInt(input[1]);

        int[] give = new int[maxCnt];
        for (int i = 0; i < maxCnt; i++) {
            give[i] = Integer.parseInt(bufferedReader.readLine());
        }

        int[] dp = new int[maxCnt];
        for (int i = 0; i < maxCnt; i++) {
            int tree = 1;
            int move = 0;

            for (int j = i; j < give.length; j++) {
                if (give[j] != tree) {
                    move++;
                    if (tree == 1) {
                        tree = 2;
                    } else {
                        tree = 1;
                    }
                }

                if (move <= maxMove) {
                    dp[i]++;
                }
            }
        }

        int max = 0;
        for (int i = 0; i < maxCnt; i++) {
            if (max < dp[i]) {
                max = dp[i];
            }
        }

        System.out.println(max);
    }
}
