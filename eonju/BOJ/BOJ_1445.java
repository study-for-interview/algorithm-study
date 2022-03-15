import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

class Main {

    private static int[] moveHeight = {-1, 1, 0, 0};
    private static int[] moveWidth = {0, 0, -1, 1};

    private static int[] startLocation;
    private static int[] flowerLocation;
    private static int height;
    private static int width;
    private static String[][] map;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = bufferedReader.readLine().split(" ");
        height = Integer.parseInt(input[0]);
        width = Integer.parseInt(input[1]);

        map = new String[height][width];
        for (int i = 0; i < height; i++) {
            map[i] = bufferedReader.readLine().split("");
        }

        startLocation = new int[2];
        flowerLocation = new int[2];
        makeNearN();

        visited = new boolean[height][width];
        dijkstra();
    }

    public static void dijkstra() {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(startLocation[0], startLocation[1], 0, 0));
        visited[startLocation[0]][startLocation[1]] = true;

        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            int nowI = poll.i;
            int nowJ = poll.j;
            int nowGarbage = poll.garbage;
            int nowNearGarbage = poll.nearGarbage;

            for (int i = 0; i < 4; i++) {
                int nextI = nowI + moveHeight[i];
                int nextJ = nowJ + moveWidth[i];

                if (nextI < 0 || nextJ < 0 || nextI >= height || nextJ >= width) {
                    continue;
                }

                if (visited[nextI][nextJ]) {
                    continue;
                }

                if (map[nextI][nextJ].equals(".")) {
                    queue.add(new Node(nextI, nextJ, nowGarbage, nowNearGarbage));
                    visited[nextI][nextJ] = true;
                }

                if (map[nextI][nextJ].equals("F")) {
                    System.out.println(nowGarbage + " " + nowNearGarbage);
                    return;
                }

                if (map[nextI][nextJ].equals("n")) {
                    queue.add(new Node(nextI, nextJ, nowGarbage, nowNearGarbage + 1));
                    visited[nextI][nextJ] = true;
                }

                if (map[nextI][nextJ].equals("g")) {
                    queue.add(new Node(nextI, nextJ, nowGarbage + 1, nowNearGarbage));
                    visited[nextI][nextJ] = true;
                }

            }

        }
    }

    public static void makeNearN() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (map[i][j].equals("g")) {
                    for (int location = 0; location < 4; location++) {
                        int nextI = i + moveHeight[location];
                        int nextJ = j + moveWidth[location];

                        if (nextI < 0 || nextJ < 0 || nextI >= height || nextJ >= width) {
                            continue;
                        }

                        if (map[nextI][nextJ].equals(".")) {
                            map[nextI][nextJ] = "n";
                        }
                    }
                }

                if (map[i][j].equals("F")) {
                    flowerLocation[0] = i;
                    flowerLocation[1] = j;
                }

                if (map[i][j].equals("S")) {
                    startLocation[0] = i;
                    startLocation[1] = j;
                }
            }
        }
    }

    static class Node implements Comparable<Node> {

        int i;
        int j;
        int garbage;
        int nearGarbage;

        public Node(int hh, int ww, int garbage, int nearGarbage) {
            this.i = hh;
            this.j = ww;
            this.garbage = garbage;
            this.nearGarbage = nearGarbage;
        }


        @Override
        public int compareTo(Node node) {
            if (this.garbage > node.garbage) {
                return 1;
            }
            if (this.garbage < node.garbage) {
                return -1;
            }
            if (this.garbage == node.garbage) {
                if (this.nearGarbage > node.nearGarbage) {
                    return 1;
                }
                if (this.nearGarbage < node.nearGarbage) {
                    return -1;
                }
            }
            return 0;
        }
    }
}
