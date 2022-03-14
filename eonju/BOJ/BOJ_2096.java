import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int N;
    private static int[][] numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());

        numbers = new int[N][3];
        for (int i = 0; i < N; i++) {
            String[] input = bufferedReader.readLine().split(" ");
            for (int j = 0; j < input.length; j++) {
                numbers[i][j] = Integer.parseInt(input[j]);
            }
        }

        int max = solveMax();
        int min = solveMin();

        System.out.println(max + " " + min);
    }

    public static int solveMin() {
        int[][] memo = new int[N][3];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 0) {
                    memo[i][j] = numbers[i][j];
                    continue;
                }
                if (j == 0) {
                    int next = Math.min(memo[i - 1][0], memo[i - 1][1]) + numbers[i][j];
                    memo[i][j] = next;
                    continue;
                }
                if (j == 1) {
                    int next = Math.min(memo[i - 1][0], Math.min(memo[i - 1][1], memo[i - 1][2])) + numbers[i][j];
                    memo[i][j] = next;
                    continue;
                }
                if (j == 2) {
                    int next = Math.min(memo[i - 1][1], memo[i - 1][2]) + numbers[i][j];
                    memo[i][j] = next;
                }
            }
        }

        int answer = memo[N - 1][0];

        for (int i = 0; i < 3; i++) {
            answer = Math.min(answer, memo[N - 1][i]);
        }

        return answer;
    }

    public static int solveMax() {
        int[][] memo = new int[N][3];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 0) {
                    memo[i][j] = numbers[i][j];
                    continue;
                }
                if (j == 0) {
                    int next = Math.max(memo[i - 1][0], memo[i - 1][1]) + numbers[i][j];
                    memo[i][j] = next;
                    continue;
                }
                if (j == 1) {
                    int next = Math.max(memo[i - 1][0], Math.max(memo[i - 1][1], memo[i - 1][2])) + numbers[i][j];
                    memo[i][j] = next;
                    continue;
                }
                if (j == 2) {
                    int next = Math.max(memo[i - 1][1], memo[i - 1][2]) + numbers[i][j];
                    memo[i][j] = next;
                }
            }
        }

        int answer = memo[N - 1][0];

        for (int i = 0; i < 3; i++) {
            answer = Math.max(answer, memo[N - 1][i]);
        }

        return answer;
    }

}
