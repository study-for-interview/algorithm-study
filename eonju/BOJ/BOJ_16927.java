import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = bufferedReader.readLine().split(" ");
        int height = Integer.parseInt(input[0]);
        int width = Integer.parseInt(input[1]);
        int turnCnt = Integer.parseInt(input[2]);

        map = new int[height][width];
        for (int i = 0; i < height; i++) {
            map[i] = Arrays.stream(bufferedReader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        }

        int startI = 0;
        int endI = height - 1;
        int startJ = 0;
        int endJ = width - 1;

        while (true) {
            int blockCnt = (endI - startI + 1) * 2 + (endJ - startJ + 1) * 2 - 4;
            turn(turnCnt % blockCnt, startI, endI, startJ, endJ);
            startI++;
            endI--;
            startJ++;
            endJ--;
            if (startI > endI || startJ > endJ) {
                break;
            }
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void turn(int turnCnt, int startI, int endI, int startJ, int endJ) {
        for (int k = 0; k < turnCnt; k++) {

            int temp = map[startI][startJ];

            for (int j = startJ; j < endJ; j++) {
                map[startI][j] = map[startI][j + 1];
            }

            for (int i = startI; i < endI; i++) {
                map[i][endJ] = map[i + 1][endJ];
            }

            for (int j = endJ; j > startJ; j--) {
                map[endI][j] = map[endI][j - 1];
            }

            for (int i = endI; i > startI; i--) {
                map[i][startJ] = map[i - 1][startJ];
            }

            map[startI + 1][startJ] = temp;
        }
    }
}
