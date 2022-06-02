import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

    private static int[][] map;
    private static int[][] answer;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        map = new int[9][9];
        answer = new int[9][9];

        for (int i = 0; i < 9; i++) {
            map[i] = Arrays.stream(bufferedReader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        }

        fillNumber(0, 0);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(answer[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void fillNumber(int startI, int startJ) {
        if (startI == 8 && startJ == 8 && map[startI][startJ] != 0) {
            for (int i = 0; i < map.length; i++) {
                answer[i] = Arrays.copyOf(map[i], map[i].length);
            }
            return;
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (map[i][j] == 0) {
                    for (int num = 1; num <= 9; num++) {
                        map[i][j] = num;
                        if (checkMiniMap(i, j) && checkHorizontal(i) && checkVertical(j)) {
                            fillNumber(i, j);
                        }
                    }
                }
            }
        }
    }

    public static boolean checkMiniMap(int i, int j) {
        int startI = (i / 3) * 3;
        int startJ = (j / 3) * 3;

        boolean[] visited = new boolean[10];
        for (int height = startI; height < startI + 3; height++) {
            for (int width = startJ; width < startJ + 3; width++) {
                int number = map[height][width];
                if (number == 0) {
                    continue;
                }
                if (visited[number]) {
                    return false;
                } else {
                    visited[number] = true;
                }
            }
        }
        return true;
    }

    public static boolean checkHorizontal(int i) {
        boolean[] visited = new boolean[10];

        for (int width = 0; width < 9; width++) {
            int number = map[i][width];
            if (visited[number]) {
                return false;
            } else {
                visited[number] = true;
            }
        }

        return true;
    }

    public static boolean checkVertical(int j) {
        boolean[] visited = new boolean[10];

        for (int height = 0; height < 9; height++) {
            int number = map[height][j];
            if (visited[number]) {
                return false;
            } else {
                visited[number] = true;
            }
        }

        return true;
    }
}
