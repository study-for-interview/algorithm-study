import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

class babyShark {
    int x;
    int y;
    int size;
    int time;
    int eatFish;

    public babyShark(int x, int y, int size, int time, int eatFish) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.time = time;
        this.eatFish = eatFish;
    }
}

class Point implements Comparable<Point> {
    int x;
    int y;
    int time;

    public Point(int x, int y, int time) {
        this.x = x;
        this.y = y;
        this.time = time;
    }

    @Override
    public int compareTo(Point o) {
        if (this.x == o.x) return this.y - o.y;
        else return this.x - o.x;
    }
}

public class Main {
    static int n;
    static int[][] map;
    static int answer;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    static ArrayList<Point> fish;
    static babyShark shark;
    static boolean ch;

    public static void move() {
        Queue<Point> Q = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];
        visited[shark.x][shark.y] = true;
        Q.add(new Point(shark.x, shark.y, 0));
        fish = new ArrayList<>();

        int distance = Integer.MAX_VALUE;
        while (!Q.isEmpty()) {
            Point point = Q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = point.x + dx[i];
                int ny = point.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                if (map[nx][ny] > shark.size) continue;
                if (visited[nx][ny]) continue;
                if (map[nx][ny] != 0 && map[nx][ny] < shark.size && distance >= point.time + 1) {
                    distance = point.time + 1;
                    fish.add(new Point(nx, ny, point.time + 1));
                }

                visited[nx][ny] = true;
                Q.offer(new Point(nx, ny, point.time + 1));
            }
        }

        if (fish.size() == 0) {
            ch = false;
        } else {

            Collections.sort(fish);
            Point fishPoint = fish.get(0);
            map[fishPoint.x][fishPoint.y] = 0;
            shark.x = fishPoint.x;
            shark.y = fishPoint.y;
            shark.time += fishPoint.time;
            shark.eatFish += 1;
            if (shark.eatFish == shark.size) {
                shark.size++;
                shark.eatFish = 0;
            }
        }

    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        ch = true;

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    map[i][j] = 0;
                    shark = new babyShark(i, j, 2, 0, 0);
                }

            }
        }


        while (ch) {
            move();
        }

        System.out.println(shark.time);
    }


}