import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

    static int width, height, robotI, robotJ, dirtyCnt;
    static int moveI[] = {-1, 0, 1, 0}, moveJ[] = {0, 1, 0, -1};
    static int cache[][][];
    static char map[][];
    static boolean visited[][];
    static List<Location> trashs;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String[] input = bufferedReader.readLine().split(" ");
            height = Integer.parseInt(input[1]);
            width = Integer.parseInt(input[0]);

            if (width == 0 && height == 0) {
                break;
            }

            map = new char[height][width];
            cache = new int[height][width][1 << 11];
            dirtyCnt = 0;
            trashs = new ArrayList<>();

            for (int i = 0; i < height; i++) {
                map[i] = bufferedReader.readLine().toCharArray();

                for (int j = 0; j < width; ++j) {
                    Arrays.fill(cache[i][j], -1);

                    if (map[i][j] == '*') {
                        dirtyCnt++;
                        trashs.add(new Location(i, j));
                    } else if (map[i][j] == 'o') {
                        robotI = i;
                        robotJ = j;
                    }
                }
            }

            System.out.println(solution(robotI, robotJ, 0, 0));
        }
    }

    public static int solution(int startI, int startJ, int pass, int cnt) {
        if (cnt == dirtyCnt) {
            return 0;
        }

        if (cache[startI][startJ][pass] != -1) {
            return cache[startI][startJ][pass];
        }

        int ret = 987654321;
        cache[startI][startJ][pass] = ret;
        for (int i = 0; i < dirtyCnt; i++) {
            if (((1 << i) & pass) > 0) {
                continue;
            }
            Location p = trashs.get(i);
            int cost = bfs(startI, startJ, p.i, p.j);
            int next = solution(p.i, p.j, pass | (1 << i), cnt + 1);

            if (cost == -1 || next == -1) {
                return -1;
            }

            ret = Math.min(ret, cost + next);
        }

        if (ret == 987654321) {
            return -1;
        }

        return cache[startI][startJ][pass] = ret;
    }

    public static int bfs(int nowI, int nowJ, int endI, int endJ) {
        visited = new boolean[height][width];
        Queue<Location> queue = new LinkedList<>();
        int count = 0;

        visited[nowI][nowJ] = true;
        queue.add(new Location(nowI, nowJ));
        map[nowI][nowJ] = '.';

        while (!queue.isEmpty()) {
            int size = queue.size();
            count++;
            for (int i = 0; i < size; i++) {
                Location here = queue.poll();

                for (int direction = 0; direction < 4; direction++) {
                    int nextI = here.i + moveI[direction];
                    int nextJ = here.j + moveJ[direction];

                    if (!isInBound(nextI, nextJ)) {
                        continue;
                    }

                    if (visited[nextI][nextJ] || map[nextI][nextJ] == 'x') {
                        continue;
                    }

                    if (nextI == endI && nextJ == endJ) {
                        robotI = nextI;
                        robotJ = nextJ;
                        map[nextI][nextJ] = '.';

                        return count;
                    }

                    visited[nextI][nextJ] = true;
                    queue.add(new Location(nextI, nextJ));
                }
            }
        }

        return -1;
    }

    public static boolean isInBound(int i, int j) {
        return i >= 0 && j >= 0 && i < height && j < width;
    }

    static class Location {

        int i;
        int j;

        public Location(int x, int y) {
            this.i = x;
            this.j = y;
        }
    }
}
