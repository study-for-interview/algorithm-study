package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/19238
 * 스타트 택시
 */
public class BOJ_19238 {

    static int n, m, fuel;
    static int[][] arr = new int[21][21];
    static Taxi taxi;
    static Passenger taken;
    static Map<Integer, Passenger> passMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        taxi = new Taxi(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            Passenger p = new Passenger(
                i + 2,
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()));

            passMap.put(p.id, p);

            arr[p.startX][p.startY] = p.id;
        }

        solution();
    }

    private static void solution() {
        while (!passMap.isEmpty()) {
            int toStartFuel = bfs();
            fuel -= toStartFuel;

            if (fuel < 0) {
                break;
            }

            int toDestFuel = bfs();
            fuel -= toDestFuel;

            if (fuel < 0) {
                break;
            }

            fuel += toDestFuel * 2;
        }

        System.out.println(fuel < 0 ? -1 : fuel);
    }

    private static int bfs() {
        Queue<Taxi> q = new LinkedList<>();
        Queue<Passenger> candidates = new LinkedList<>();
        boolean[][] visited = new boolean[21][21];
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        int prevMove = taxi.move;
        visited[taxi.x][taxi.y] = true;
        q.add(taxi);

        while (!q.isEmpty()) {
            Taxi now = q.poll();

            if (fuel - now.move < 0) {
                return Integer.MAX_VALUE;
            }

            if (prevMove != now.move && !candidates.isEmpty()) {
                break;
            }

            prevMove = now.move;

            if (taken == null) {
                int id = arr[now.x][now.y];

                if (id > 1) {
                    Passenger p = passMap.get(id);
                    candidates.add(p);
                }
            } else {
                if (now.x == taken.endX && now.y == taken.endY) {
                    passMap.remove(taken.id);
                    taken = null;
                    taxi = new Taxi(now.x, now.y, 0);
                    return prevMove;
                }
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (0 < nx && nx < n + 1 && 0 < ny && ny < n + 1) {
                    if (arr[nx][ny] != 1 && !visited[nx][ny]) {
                        q.add(new Taxi(nx, ny, now.move + 1));
                        visited[nx][ny] = true;
                    }
                }
            }
        }

        if (candidates.isEmpty()) {
            return Integer.MAX_VALUE;
        }

        taken = findNearest(candidates);
        taxi = new Taxi(taken.startX, taken.startY, 0);
        arr[taken.startX][taken.startY] = 0;
        return prevMove;
    }

    private static Passenger findNearest(Queue<Passenger> q) {
        Passenger target = q.poll();
        while (!q.isEmpty()) {
            Passenger compare = q.poll();

            if (compare.startX < target.startX) {
                target = compare;
            } else if (compare.startX == target.startX && compare.startY < target.startY) {
                target = compare;
            }
        }
        return target;
    }

    static class Taxi {

        int x;
        int y;
        int move;

        public Taxi(int x, int y, int move) {
            this.x = x;
            this.y = y;
            this.move = move;
        }
    }

    static class Passenger {

        int id;
        int startX;
        int startY;
        int endX;
        int endY;

        public Passenger(int id, int startX, int startY, int endX, int endY) {
            this.id = id;
            this.startX = startX;
            this.startY = startY;
            this.endX = endX;
            this.endY = endY;
        }
    }
}
