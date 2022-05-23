import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    private static final int[][] move = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};  // 0위, 1아래, 2왼쪽, 3오른쪽
    private static final int BLANK = 0;
    private static final int WALL = 6;

    private static int[][] ONE = {{0}, {1}, {2}, {3}};
    private static int[][] TWO = {{0, 1}, {2, 3}};
    private static int[][] THREE = {{0, 2}, {0, 3}, {1, 2}, {1, 3}};
    private static int[][] FOUR = {{0, 2, 3}, {0, 1, 2}, {1, 2, 3}, {0, 1, 3}};
    private static int[][] FIVE = {{0, 1, 2, 3}};
    private static int[][] map;

    private static int answer = Integer.MAX_VALUE;
    private static ArrayList<int[]> cameras;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());

        int height = Integer.parseInt(st.nextToken());
        int width = Integer.parseInt(st.nextToken());

        map = new int[height][width];
        cameras = new ArrayList<>();
        int[][] visited = new int[height][width];

        for (int i = 0; i < height; i++) {
            st = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < width; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != BLANK && map[i][j] != WALL) {
                    cameras.add(new int[]{i, j});
                }
                if (map[i][j] != BLANK) {
                    visited[i][j] = -1;
                }
            }
        }

        dfs(0, visited);
        System.out.println(answer);
    }

    public static void dfs(int cameraIdx, int[][] visited) {
        if (cameraIdx >= cameras.size()) {
            answer = Math.min(answer, countBlank(visited));
            return;
        }

        int[] cameraLocation = cameras.get(cameraIdx);
        int cameraType = map[cameraLocation[0]][cameraLocation[1]];
        int[][] todo;   // 카메라의 방향의 경우의 수

        if (cameraType == 1) {
            todo = ONE;
        } else if (cameraType == 2) {
            todo = TWO;
        } else if (cameraType == 3) {
            todo = THREE;
        } else if (cameraType == 4) {
            todo = FOUR;
        } else {
            todo = FIVE;
        }

        for (int i = 0; i < todo.length; i++) {
            int[][] tmpVisited = new int[visited.length][visited[0].length];
            for (int k = 0; k < visited.length; k++) {
                tmpVisited[k] = Arrays.copyOf(visited[k], visited[k].length);
            }

            for (int j = 0; j < todo[i].length; j++) {
                int nextI = cameraLocation[0] + move[todo[i][j]][0];
                int nextJ = cameraLocation[1] + move[todo[i][j]][1];

                while (isInBound(nextI, nextJ) && map[nextI][nextJ] != WALL) {
                    tmpVisited[nextI][nextJ] = -1;

                    nextI = nextI + move[todo[i][j]][0];
                    nextJ = nextJ + move[todo[i][j]][1];
                }

                dfs(cameraIdx + 1, tmpVisited);
            }
        }

    }

    public static int countBlank(int[][] visited) {
        int cnt = 0;

        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited[i].length; j++) {
                if (visited[i][j] == BLANK) {
                    cnt++;
                }
            }
        }

        return cnt;
    }

    public static boolean isInBound(int i, int j) {
        return i >= 0 && j >= 0 && i < map.length && j < map[0].length;
    }
}
