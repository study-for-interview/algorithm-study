import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    private static int[][] original;
    private static int[] answer;
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(bufferedReader.readLine());
        original = new int[n][n];
        answer = new int[3];

        int number = check(0, 0, n);
        if(number == -100){
            solve(0, 0, n, n, n / 3);
        } else {
            if (number == -1) {
                answer[0]++;
            } else if (number == 0) {
                answer[1]++;
            } else if (number == 1) {
                answer[2]++;
            }
        }

        for (int i = 0; i < answer.length; i++) {
            System.out.println(answer[i]);
        }
    }

    public static void solve(int startI, int startJ, int endI, int endJ, int areaSize) {
        for (int i = startI; i < endI; i += areaSize) {
            for (int j = startJ; j < endJ; j += areaSize) {
                int num = check(i, j, areaSize);

                if (num == -100) {
                    solve(i, j, i + areaSize, j + areaSize, areaSize / 3);
                    continue;
                }

                if (num == -1) {
                    answer[0]++;
                } else if (num == 0) {
                    answer[1]++;
                } else if (num == 1) {
                    answer[2]++;
                }
            }
        }
    }

    public static int check(int startI, int startJ, int areaSize) {
        int number = original[startI][startJ];

        for (int i = startI; i < startI + areaSize; i++) {
            for (int j = startJ; j < startJ + areaSize; j++) {
                if (number != original[i][j]) {
                    return -100;
                }
            }
        }

        return number;
    }
}
