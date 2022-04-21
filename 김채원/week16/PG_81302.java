import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static char[][] map;
    static ArrayList<Point> tester;
    static Queue<Point> Q;
    static boolean visited[][];
    static boolean flag;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int distance(int r1, int r2, int c1, int c2) {
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }

    static void bfs(int x, int y) {
        Q = new LinkedList<>();
        Q.add(new Point(x, y));
        visited = new boolean[5][5];
        visited[x][y] = true;

        while (!Q.isEmpty()) {
            Point point = Q.poll();
            int xx = point.x;
            int yy = point.y;

            for (int i = 0; i < 4; i++) {
                int nx = xx + dx[i];
                int ny = yy + dy[i];

                if (nx < 0 || ny < 0 || nx >= 5 || ny >= 5) continue;
                if (visited[nx][ny]) continue;
                int distance = Math.abs(nx - x) + Math.abs(ny - y);
                if (map[nx][ny] == 'P' && distance <= 2) {
                    flag = true;
                    return;
                } else if (map[nx][ny] == 'O' && distance == 1) {
                    Q.add(new Point(nx, ny));
                    visited[nx][ny] = true;
                }
            }
        }
    }

    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        Arrays.fill(answer, 1);
        for (int i = 0; i < places.length; i++) {
            tester = new ArrayList<>();
            map = new char[5][5];
            for (int j = 0; j < places[i].length; j++) {
                for (int k = 0; k < places[i][j].length(); k++) {
                    map[j][k] = places[i][j].charAt(k);
                    if (map[j][k] == 'P') tester.add(new Point(j, k));
                }
            }

            flag = false;

            for (int j = 0; j < tester.size(); j++) {
                Point point = tester.get(j);
                bfs(point.x, point.y);

                if (flag) {
                    answer[i] = 0;
                    break;
                }

            }

        }

        System.out.println(Arrays.toString(answer));
        return answer;
    }


    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[][] places = {{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
                {"XPXXX", "XOXXX", "POXXX", "XPXXX", "XXXXX"},
                {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"}
                , {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"}, {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};
        T.solution(places);

    }
}