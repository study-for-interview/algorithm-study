import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class Main {

    private static int size;
    private static List<Consumer> consumers;
    private static int[][] map;
    private static int[][] diffMap;
    private static Location texi;
    private static int gas;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = bufferedReader.readLine().split(" ");
        size = Integer.parseInt(input[0]);
        int consumerQuantity = Integer.parseInt(input[1]);
        gas = Integer.parseInt(input[2]);

        map = new int[size][size];
        diffMap = new int[size][size];
        consumers = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            input = bufferedReader.readLine().split(" ");
            for (int j = 0; j < size; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }

        input = bufferedReader.readLine().split(" ");
        texi = new Location(Integer.parseInt(input[0]) - 1, Integer.parseInt(input[1]) - 1);

        for (int i = 0; i < consumerQuantity; i++) {
            input = bufferedReader.readLine().split(" ");
            int startI = Integer.parseInt(input[0]) - 1;
            int startJ = Integer.parseInt(input[1]) - 1;
            int endI = Integer.parseInt(input[2]) - 1;
            int endJ = Integer.parseInt(input[3]) - 1;

            consumers.add(new Consumer(startI, startJ, endI, endJ));
        }

        while (!consumers.isEmpty()) {
            bfs(texi.i, texi.j);

            Consumer consumer = getMinDiffConsumer();
            int consumeGas = 0;

            if (gas - diffMap[consumer.startI][consumer.startJ] < 0
                || diffMap[consumer.startI][consumer.startJ] == -1) {
                System.out.println(-1);
                return;
            } else {
                texi = new Location(consumer.startI, consumer.startJ);
                gas = gas - diffMap[consumer.startI][consumer.startJ];
            }

            bfs(texi.i, texi.j);
            if (gas - diffMap[consumer.endI][consumer.endJ] < 0 || diffMap[consumer.endI][consumer.endJ] == -1) {
                System.out.println(-1);
                return;
            } else {
                texi = new Location(consumer.endI, consumer.endJ);
                consumers.remove(consumer);
                consumeGas += diffMap[consumer.endI][consumer.endJ];
                gas = gas - diffMap[consumer.endI][consumer.endJ];
                gas = gas + consumeGas * 2;
            }
        }

        System.out.println(gas);
    }

    public static void fillMinus() {
        for (int i = 0; i < diffMap.length; i++) {
            Arrays.fill(diffMap[i], -1);
        }
    }

    public static Consumer getMinDiffConsumer() {
        Consumer consumer = null;

        int minDiff = Integer.MAX_VALUE;
        for (int i = 0; i < consumers.size(); i++) {
            Consumer target = consumers.get(i);

            if (minDiff > diffMap[target.startI][target.startJ]) {
                consumer = target;
                minDiff = diffMap[target.startI][target.startJ];
                continue;
            }

            if (minDiff == diffMap[target.startI][target.startJ]) {
                if (target.startI < consumer.startI) {
                    consumer = target;
                    continue;
                }

                if (target.startI == consumer.startI && target.startJ < consumer.startJ) {
                    consumer = target;
                }
            }
        }

        return consumer;
    }

    public static void bfs(int startI, int startJ) {
        fillMinus();
        int[] moveHeight = {-1, 1, 0, 0};
        int[] moveWidth = {0, 0, 1, -1};
        boolean[][] visited = new boolean[size][size];
        LinkedList<Location> queue = new LinkedList<>();

        queue.add(new Location(startI, startJ));
        visited[startI][startJ] = true;
        diffMap[startI][startJ] = 0;

        while (!queue.isEmpty()) {
            Location poll = queue.poll();
            int nowI = poll.i;
            int nowJ = poll.j;

            for (int i = 0; i < 4; i++) {
                int nextI = nowI + moveHeight[i];
                int nextJ = nowJ + moveWidth[i];

                if (!isInBound(nextI, nextJ)) {
                    continue;
                }

                if (visited[nextI][nextJ]) {
                    continue;
                }

                if (map[nextI][nextJ] == 1) {
                    continue;
                }

                diffMap[nextI][nextJ] = diffMap[nowI][nowJ] + 1;
                queue.add(new Location(nextI, nextJ));
                visited[nextI][nextJ] = true;
            }
        }
    }

    public static boolean isInBound(int i, int j) {
        return i >= 0 && j >= 0 && i < size && j < size;
    }

    static class Consumer {

        int startI;
        int startJ;
        int endI;
        int endJ;

        public Consumer(int startI, int startJ, int endI, int endJ) {
            this.startI = startI;
            this.startJ = startJ;
            this.endI = endI;
            this.endJ = endJ;
        }
    }

    static class Location {

        int i;
        int j;

        public Location(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
}
