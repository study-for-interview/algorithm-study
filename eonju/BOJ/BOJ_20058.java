import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class BOJ_20058 {

    private static int[] moveHeight = {-1, 1, 0, 0};
    private static int[] moveWidth = {0, 0, -1, 1};
    public static int mapSize = 0;
    private static int[] targetSize;
    public static int[][] map;
    public static boolean[][] visited;
    private static int maxCnt;
    private static int maxIce;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = bufferedReader.readLine().split(" ");

        mapSize = (int) Math.pow(2, Integer.parseInt(input[0]));
        int levelCnt = Integer.parseInt(input[1]);

        map = new int[mapSize][mapSize];
        for (int i = 0; i < mapSize; i++) {
            input = bufferedReader.readLine().split(" ");
            for (int j = 0; j < mapSize; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }

        targetSize = new int[levelCnt];
        input = bufferedReader.readLine().split(" ");
        for (int i = 0; i < levelCnt; i++) {
            targetSize[i] = (int) Math.pow(2, Integer.parseInt(input[i]));
        }

        for (int i = 0; i < targetSize.length; i++) {
            turnMap(targetSize[i]);
            melt();
        }

        maxCnt = 0;
        maxIce = 0;
        visited = new boolean[mapSize][mapSize];

        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                if (map[i][j] > 0 && !visited[i][j]) {
                    bfs(i, j);
                }
            }
        }

        System.out.println(maxIce);
        System.out.println(maxCnt);
    }

    public static void turnMap(int targetSize) {
        int[][] tmpMap = new int[mapSize][mapSize];

        for (int startI = 0; startI < mapSize; startI += targetSize) {
            for (int startJ = 0; startJ < mapSize; startJ += targetSize) {
                for (int i = startI; i < startI + targetSize; i++) {
                    int maxHeightIdx = startI + targetSize - 1;
                    for (int j = startJ; j < startJ + targetSize; j++) {
                        int maxWidthIdx = startJ + targetSize - 1;
                        if ((maxHeightIdx - i) + (maxWidthIdx - j) == targetSize - 1) {
                            int diff;
                            if (i < (maxHeightIdx + 1) / 2) {
                                diff = (maxWidthIdx - startJ) - (i*2);
                            } else {
                                diff = (maxWidthIdx - startJ) + (i*2);
                            }
                            if (j + diff < startJ + targetSize && isInBound(i, j + diff)) {
                                tmpMap[i][j + diff] = map[i][j];
                                continue;
                            }

                            if (i + diff < startJ + targetSize && isInBound(i + diff, j)) {
                                tmpMap[i + diff][j] = map[i][j];
                                continue;
                            }

                            if (j - diff >= startJ && isInBound(i, j - diff)) {
                                tmpMap[i][j - diff] = map[i][j];
                                continue;
                            }

                            if (i - diff >= startI && isInBound(i - diff, j)) {
                                tmpMap[i - diff][j] = map[i][j];
                            }

                        } else {
                            tmpMap[j][i] = map[i][j];
                        }
                    }
                }
            }

            map = tmpMap;
        }
    }

    public static void melt() {
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                int cnt = 0;

                for (int k = 0; k < 4; k++) {
                    int nextI = i + moveHeight[k];
                    int nextJ = j + moveWidth[k];

                    if (!isInBound(nextI, nextJ)) {
                        continue;
                    }

                    if (map[nextI][nextJ] > 0) {
                        cnt++;
                    }
                }

                if (cnt < 3) {
                    map[i][j] = map[i][j] - 1;
                }
            }
        }
    }

    public static void bfs(int startI, int startJ) {
        Queue<int[]> queue = new LinkedList<>();
        int cnt = 0;
        int sum = 0;

        queue.add(new int[]{startI, startJ});
        visited[startI][startJ] = true;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            cnt++;
            sum += map[poll[0]][poll[1]];
            int nowHeight = poll[0];
            int nowWidth = poll[1];

            for (int i = 0; i < 4; i++) {
                int nextI = nowHeight + moveHeight[i];
                int nextJ = nowWidth + moveWidth[i];

                if (!isInBound(nextI, nextJ)) {
                    continue;
                }

                if (visited[nextI][nextJ]) {
                    continue;
                }

                if (map[nextI][nextJ] > 0) {
                    queue.add(new int[]{nextI, nextJ});
                    visited[nextI][nextJ] = true;
                }
            }
        }

        maxCnt = Math.max(cnt, maxCnt);
        maxIce = maxIce + sum;
    }

    public static boolean isInBound(int i, int j) {
        return i >= 0 && j >= 0 && i < mapSize && j < mapSize;
    }

}
