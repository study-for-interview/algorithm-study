import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static int size;
    static int[][] map;
    static int moveHeight[] = {-1, 0, 1, 0}; //위 왼 아래 오
    static int moveWidth[] = {0, 1, 0, -1};
    static LinkedList<Node> fishes;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        size = Integer.parseInt(bufferedReader.readLine());
        map = new int[size][size];

        Queue<Node> queue = new LinkedList<>();
        for (int i = 0; i < size; i++) {

            String[] input = bufferedReader.readLine().split(" ");
            for (int j = 0; j < input.length; j++) {
                map[i][j] = Integer.parseInt(input[j]);

                if (map[i][j] == 9) {
                    queue.add(new Node(i, j, 0));
                    map[i][j] = 0;
                }
            }

        }

        int eat = 0;
        int time = 0;
        int sharkSize = 2;

        while (true) {
            fishes = new LinkedList<>();
            int[][] dist = new int[size][size];

            while (!queue.isEmpty()) {
                Node now = queue.poll();

                for (int i = 0; i < 4; i++) {
                    int nextX = now.x + moveHeight[i];
                    int nextY = now.y + moveWidth[i];

                    if (nextX < 0 || nextY < 0 || nextX >= size || nextY >= size) {
                        continue;
                    }

                    if (dist[nextX][nextY] == 0 && map[nextX][nextY] <= sharkSize) {
                        dist[nextX][nextY] = dist[now.x][now.y] + 1;
                        queue.add(new Node(nextX, nextY, dist[nextX][nextY]));
                        if (1 <= map[nextX][nextY] && map[nextX][nextY] <= 6 && map[nextX][nextY] < sharkSize) {
                            fishes.add(new Node(nextX, nextY, dist[nextX][nextY]));
                        }
                    }
                }
            }

            if (fishes.size() == 0) {
                System.out.println(time);
                return;
            }

            Node fish = fishes.get(0);

            for (int i = 1; i < fishes.size(); i++) {
                if (fish.dist > fishes.get(i).dist) {
                    fish = fishes.get(i);
                } else if (fish.dist == fishes.get(i).dist) {
                    if (fish.x > fishes.get(i).x) {
                        fish = fishes.get(i);
                    } else if (fish.x == fishes.get(i).x) {
                        if (fish.y > fishes.get(i).y) {
                            fish = fishes.get(i);
                        }
                    }
                }
            }

            time += fish.dist;
            eat++;
            map[fish.x][fish.y] = 0;
            if (eat == sharkSize) {
                sharkSize++;
                eat = 0;
            }

            queue.add(new Node(fish.x, fish.y, 0));
        }
    }

    static class Node {

        int x;
        int y;
        int dist;

        public Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}
