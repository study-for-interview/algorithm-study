import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class BOJ_16234 {

    private static int[] moveI = {-1, 1, 0, 0};
    private static int[] moveJ = {0, 0, -1, 1};
    private static int[][] map;
    private static boolean[][] visited;
    private static List<int[]> moveList;
    private static boolean isMove;
    private static int N;
    private static int L;
    private static int R;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = bufferedReader.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        L = Integer.parseInt(input[1]);
        R = Integer.parseInt(input[2]);

        map = new int[N][N];
        moveList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(bufferedReader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        }

        int day = 0;
        while (true) {
            isMove = false;
            visited = new boolean[N][N];
            moveList = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        bfs(i, j);
                    }
                }
            }

            if (isMove) {
                day++;
            } else {
                break;
            }
        }
        System.out.println(day);
    }

    public static void bfs(int startI, int startJ) {
        visited[startI][startJ] = true;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startI, startJ});

        int sum = 0;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int nowI = poll[0];
            int nowJ = poll[1];
            sum += map[nowI][nowJ];

            for (int i = 0; i < 4; i++) {
                int nextI = nowI + moveI[i];
                int nextJ = nowJ + moveJ[i];

                if (nextI < 0 || nextJ < 0 || nextI >= N || nextJ >= N) {
                    continue;
                }

                if (visited[nextI][nextJ]) {
                    continue;
                }

                int diff = Math.abs(map[nowI][nowJ] - map[nextI][nextJ]);
                if (isInPopulation(diff)) {
                    visited[nextI][nextJ] = true;
                    queue.add(new int[]{nextI, nextJ});
                    moveList.add(new int[]{nextI, nextJ});
                    isMove = true;
                }
            }
        }

        if (isMove) {
            movePopulation(sum);
        }
    }

    public static void movePopulation(int sum) {
        int avg = sum / moveList.size();

        for (int[] location : moveList) {
            map[location[0]][location[1]] = avg;
        }
    }

    public static boolean isInPopulation(int number) {
        return number >= L && number <= R;
    }
}
