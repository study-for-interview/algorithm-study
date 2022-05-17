import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Main {

    private static int time = 0;
    private static int[][] map;
    private static final int[][] move = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = bufferedReader.readLine().split(" ");
        int height = Integer.parseInt(input[0]);
        int width = Integer.parseInt(input[1]);

        map = new int[height][width];
        for (int i = 0; i < height; i++) {
            input = bufferedReader.readLine().split(" ");
            for (int j = 0; j < width; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }

        int cnt = check();
        while (cnt == 1) {
            time++;
            int[][] minusMap = new int[height][width];
            makeMinusMap(minusMap);
            melt(minusMap);

            cnt = check();
        }

        if (cnt == -1) {
            System.out.println(0);
        } else {
            System.out.println(time);
        }
    }

    public static void makeMinusMap(int[][] minusMap) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] != 0) {
                    int cnt = 0;
                    for (int location = 0; location < move.length; location++) {
                        int nextI = i + move[location][0];
                        int nextJ = j + move[location][1];

                        if (nextI < 0 || nextJ < 0 || nextI >= minusMap.length || nextJ >= minusMap[0].length) {
                            continue;
                        }

                        if (map[nextI][nextJ] == 0) {
                            cnt++;
                        }
                    }
                    minusMap[i][j] = cnt;
                }
            }
        }
    }

    public static void melt(int[][] minusMap) {
        for (int i = 0; i < minusMap.length; i++) {
            for (int j = 0; j < minusMap[i].length; j++) {
                if (minusMap[i][j] != 0) {
                    if (map[i][j] - minusMap[i][j] > 0) {
                        map[i][j] = map[i][j] - minusMap[i][j];
                    } else {
                        map[i][j] = 0;
                    }
                }
            }
        }
    }

    public static int check() {
        int cnt = 0;

        boolean[][] visited = new boolean[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] != 0 && !visited[i][j]) {
                    bfs(visited, i, j);
                    cnt++;
                }
            }
        }

        if (cnt == 0) {
            return -1;
        } else {
            return cnt;
        }
    }

    public static void bfs(boolean[][] visited, int startI, int startJ) {
        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[]{startI, startJ});
        visited[startI][startJ] = true;
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            for (int i = 0; i < move.length; i++) {
                int nextI = now[0] + move[i][0];
                int nextJ = now[1] + move[i][1];

                if (nextI < 0 || nextJ < 0 || nextI >= map.length || nextJ >= map[0].length) {
                    continue;
                }

                if (visited[nextI][nextJ]) {
                    continue;
                }

                if (map[nextI][nextJ] != 0) {
                    visited[nextI][nextJ] = true;
                    queue.add(new int[]{nextI, nextJ});
                }
            }
        }
    }
}
