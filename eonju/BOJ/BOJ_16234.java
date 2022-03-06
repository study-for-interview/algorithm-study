import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class BOJ_16234 {

    private static int[] moveI = {-1, 1, 0, 0};
    private static int[] moveJ = {0, 0, -1, 1};
    private static int[][] map;
    private static int[][] copyMap;
    private static boolean[][] visited;
    private static boolean flag;
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
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            input = bufferedReader.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }

        int year = 0;
        while (true) {
            flag = false;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        copyMap = new int[N][N];
                        visited = new boolean[N][N];
                        bfs(i, j);
                    }

                }
            }

            if (flag) {
                year++;
            } else {
                break;
            }
        }

        System.out.println(year);

    }

    public static void bfs(int startI, int startJ) {
        Queue<int[]> queue = new LinkedList<>();
        visited[startI][startJ] = true;
        queue.add(new int[]{startI, startJ});

        int sum = map[startI][startJ];
        int cnt = 1;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int nowI = poll[0];
            int nowJ = poll[1];

            for (int i = 0; i < 4; i++) {
                int nextI = nowI + moveI[i];
                int nextJ = nowJ + moveJ[i];

                if (nextI < 0 || nextJ < 0 || nextI >= N || nextJ >= N) {
                    continue;
                }

                if (visited[nextI][nextJ]) {
                    continue;
                }

                if (isInPopulation(map[nowI][nowJ] - map[nextI][nextJ])) {
                    queue.add(new int[]{nextI, nextJ});

                    copyMap[nextI][nextJ] = map[nextI][nextJ];
                    sum += map[nextI][nextJ];
                    cnt++;
                    flag = true;
                }
            }
        }

        movePopulation(sum, cnt);
    }

    public static void movePopulation(int sum, int cnt) {
        int people = sum / cnt;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (copyMap[i][j] > 0) {
                    map[i][j] = people;
                }
            }
        }
    }

    public static boolean isInPopulation(int number) {
        return number >= L && number <= R;
    }
}
