import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Main {

    private static final int[] moveI = {-1, 1, 0, 0};
    private static final int[] moveJ = {0, 0, -1, 1};
    private static int N;
    private static int[][] map;
    private static int[][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int problemCnt = 0;
        while (true) {
            N = Integer.parseInt(bufferedReader.readLine());
            problemCnt++;

            if (N == 0) {
                break;
            }

            map = new int[N][N];
            dist = new int[N][N];

            for (int i = 0; i < N; i++) {
                String[] input = bufferedReader.readLine().split(" ");
                for (int j = 0; j < input.length; j++) {
                    map[i][j] = Integer.parseInt(input[j]);
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }

            dijkstra(0, 0);
            System.out.println("Problem " + problemCnt + ": " + dist[N - 1][N - 1]);
        }
    }

    public static void dijkstra(int startI, int startJ) {
        dist[startI][startJ] = map[startI][startJ];
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(x -> x[2]));
        queue.add(new int[]{startI, startJ, dist[startI][startJ]});

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int nowI = poll[0];
            int nowJ = poll[1];
            int nowDist = poll[2];

            if (nowDist > dist[nowI][nowJ]) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nextI = nowI + moveI[i];
                int nextJ = nowJ + moveJ[i];

                if (nextI < 0 || nextJ < 0 || nextI >= N || nextJ >= N) {
                    continue;
                }

                int nextDist = nowDist + map[nextI][nextJ];

                if (nextDist < dist[nextI][nextJ]) {
                    dist[nextI][nextJ] = nextDist;
                    queue.add(new int[]{nextI, nextJ, nextDist});
                }
            }
        }
    }
}
