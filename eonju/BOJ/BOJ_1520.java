import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    private static int[] moveHeight = {-1, 1, 0, 0};
    private static int[] moveWidth = {0, 0, -1, 1};
    private static int[][] map;
    private static int[][] dp;
    private static int height;
    private static int width;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = bufferedReader.readLine().split(" ");
        height = Integer.parseInt(input[0]);
        width = Integer.parseInt(input[1]);
        map = new int[height][width];
        dp = new int[height][width];

        for (int i = 0; i < height; i++) {
            input = bufferedReader.readLine().split(" ");
            for (int j = 0; j < width; j++) {
                map[i][j] = Integer.parseInt(input[j]);
                dp[i][j] = -1;
            }
        }

        int answer = dfs(0, 0);
        System.out.println(answer);

    }

    public static int dfs(int i, int j) {
        if (i == height - 1 && j == width - 1) {
            return 1;
        }

        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        dp[i][j] = 0;
        for (int move = 0; move < 4; move++) {
            int nextI = i + moveHeight[move];
            int nextJ = j + moveWidth[move];

            if (nextI < 0 || nextJ < 0 || nextI >= height || nextJ >= width) {
                continue;
            }

            if (map[nextI][nextJ] < map[i][j]) {
                dp[i][j] += dfs(nextI, nextJ);
            }
        }

        return dp[i][j];
    }
}
