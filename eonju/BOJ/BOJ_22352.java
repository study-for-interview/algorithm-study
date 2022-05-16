import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = bufferedReader.readLine().split(" ");
        int height = Integer.parseInt(input[0]);
        int width = Integer.parseInt(input[1]);

        int[][] beforeMap = new int[height][width];
        int[][] afterMap = new int[height][width];

        for (int i = 0; i < height; i++) {
            beforeMap[i] = Arrays.stream(bufferedReader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        }
        for (int i = 0; i < height; i++) {
            afterMap[i] = Arrays.stream(bufferedReader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        }

        int[][] diffMap = diff(beforeMap, afterMap);
        boolean answer = bfs(diffMap);

        if (answer) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    public static boolean bfs(int[][] diffMap) {
        int cnt = 0;

        for (int i = 0; i < diffMap.length; i++) {
            for (int j = 0; j < diffMap[i].length; j++) {
                if (diffMap[i][j] != 0) {
                    cnt++;
                    change(i, j, diffMap[i][j], diffMap);
                }
            }
        }

        return cnt < 2;
    }

    public static void change(int startI, int startJ, int data, int[][] diffMap) {
        int[][] move = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        Queue<int[]> queue = new LinkedList<>(); // i, j

        queue.add(new int[]{startI, startJ});
        diffMap[startI][startJ] = 0;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            for (int i = 0; i < move.length; i++) {
                int nextI = poll[0] + move[i][0];
                int nextJ = poll[1] + move[i][1];

                if (nextI < 0 || nextJ < 0 || nextI >= diffMap.length || nextJ >= diffMap[0].length) {
                    continue;
                }

                if (diffMap[nextI][nextJ] == data) {
                    queue.add(new int[]{nextI, nextJ});
                    diffMap[nextI][nextJ] = 0;
                }
            }
        }
    }

    public static int[][] diff(int[][] beforeMap, int[][] afterMap) {
        int[][] diffMap = new int[beforeMap.length][beforeMap[0].length];
        for (int i = 0; i < beforeMap.length; i++) {
            for (int j = 0; j < beforeMap[i].length; j++) {
                if (beforeMap[i][j] != afterMap[i][j]) {
                    diffMap[i][j] = afterMap[i][j];
                }
            }
        }

        return diffMap;
    }
}
