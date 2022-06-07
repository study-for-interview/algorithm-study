import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

    private static int MINUS = 0;
    private static int ZERO = 0;
    private static int PLUS = 0;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(bufferedReader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        }

        divide(0, 0, n);
        System.out.println(MINUS);
        System.out.println(ZERO);
        System.out.println(PLUS);

    }

    public static void divide(int startI, int startJ, int size) {
        if (check(startI, startJ, size)) {
            return;
        }

        int tmp = size / 3;

        for (int i = startI; i < startI + size; i += tmp) {
            for (int j = startJ; j < startJ + size; j += tmp) {
                divide(i, j, tmp);
            }
        }
    }

    public static boolean check(int startI, int startJ, int size) {
        int number = map[startI][startJ];

        for (int i = startI; i < startI + size; i++) {
            for (int j = startJ; j < startJ + size; j++) {
                if (number != map[i][j]) {
                    return false;
                }
            }
        }

        if (number == -1) {
            MINUS++;
        } else if (number == 0) {
            ZERO++;
        } else {
            PLUS++;
        }

        return true;
    }
}
