import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main2 {

    private static int[] moveHeight = {-1, 1, 0, 0};
    private static int[] moveWidth = {0, 0, -1, 1};
    private static int height;
    private static int width;
    private static int k;
    private static String[][] map;
    private static int[][][] dp;
    private static String[] target;


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] input = bufferedReader.readLine().split(" ");
        height = Integer.parseInt(input[0]);
        width = Integer.parseInt(input[1]);
        k = Integer.parseInt(input[2]);

        map = new String[height][width];
        for (int i = 0; i < height; i++) {
            map[i] = bufferedReader.readLine().split("");
        }

        target = bufferedReader.readLine().split("");

        dp = new int[height][width][target.length];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        int answer = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (map[i][j].equals(String.valueOf(target[0]))) {
                    answer += dfs(0, i, j);
                }
            }
        }

        System.out.println(answer);
    }

    public static int dfs(int idx, int i, int j) {
        if (idx == target.length - 1) {
            return dp[i][j][idx] = 1;
        }

        if (dp[i][j][idx] != -1) {
            return dp[i][j][idx];
        }

        dp[i][j][idx] = 0;

        for (int move = 1; move <= k; move++) {
            for (int location = 0; location < 4; location++) {
                int nextI = i + move * moveHeight[location];
                int nextJ = j + move * moveWidth[location];

                if (nextI < 0 || nextJ < 0 || nextI >= height || nextJ >= width) {
                    continue;
                }

                String nextAlphabet = String.valueOf(target[idx + 1]);

                if (map[nextI][nextJ].equals(nextAlphabet)) {
                    dp[i][j][idx] += dfs(idx + 1, nextI, nextJ);
                }
            }
        }

        return dp[i][j][idx];
    }

}
