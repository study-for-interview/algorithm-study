import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    private static final int[][] moveDirection = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
    private static final int[] sameDirection = {0, 2, 4, 6};
    private static final int[] diffDirection = {1, 3, 5, 7};
    private static Fireball[][] map;
    private static int mapSize;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = bufferedReader.readLine().split(" ");
        mapSize = Integer.parseInt(input[0]);
        int fireballCnt = Integer.parseInt(input[1]);
        int moveCnt = Integer.parseInt(input[2]);

        map = new Fireball[mapSize][mapSize];

        for (int i = 0; i < fireballCnt; i++) {
            input = bufferedReader.readLine().split(" ");
            int height = Integer.parseInt(input[0]);
            int width = Integer.parseInt(input[1]);
            int weight = Integer.parseInt(input[2]);
            int speed = Integer.parseInt(input[3]);
            int direction = Integer.parseInt(input[4]);

            map[height][width] = new Fireball(weight, direction, speed);
        }

        for (int i = 0; i < moveCnt; i++) {
            int[][] fireballCntMap = move();
            arrangeMap(fireballCntMap);
        }

    }

    public static void arrangeMap(int[][] fireballCntMap) {
        for (int i = 0; i < fireballCntMap.length; i++) {
            for (int j = 0; j < fireballCntMap[i].length; j++) {
                if (fireballCntMap[i][j] >= 2) {
                    Fireball fireball = map[i][j];
                    int nextWeight = fireball.weight / 5;
                    int nextSpeed = fireball.speed / fireballCntMap[i][j];
                    if (fireball.direction == -1) {
                        for (int direction = 0; direction < diffDirection.length; direction++) {

                        }
                    } else {
                        for (int direction = 0; direction < sameDirection.length; direction++) {

                        }
                    }
                }
            }
        }
    }

    public static int[][] move() {
        int[][] fireballCntMap = new int[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] != null) {
                    Fireball fireball = map[i][j];
                    int[] direction = moveDirection[fireball.direction];
                    int nextHeight;
                    int nextWidth;
                    for (int k = 0; k < fireball.speed; k++) {
                        nextHeight = i + direction[0];
                        nextWidth = j + direction[1];

                        if (nextHeight == -1) {
                            nextHeight = mapSize - 1;
                        } else if (nextHeight >= mapSize) {
                            nextHeight = 0;
                        }

                        if (nextWidth == -1) {
                            nextWidth = mapSize - 1;
                        } else if (nextWidth >= mapSize) {
                            nextWidth = 0;
                        }

                        if (map[nextHeight][nextWidth] != null) {
                            merge(nextHeight, nextWidth, fireball.weight, fireball.direction, fireball.speed);
                        } else {
                            map[nextHeight][nextWidth] = new Fireball(fireball.weight, fireball.direction,
                                fireball.speed);
                        }
                        fireballCntMap[nextHeight][nextWidth] = fireballCntMap[nextHeight][nextWidth] + 1;
                    }
                }
            }
        }

        return fireballCntMap;
    }


    public static void merge(int height, int width, int weight, int direction, int speed) {
        Fireball fireball = map[height][width];
        if (isSameKindOfDirection(fireball.direction, direction)) {
            map[height][width] = new Fireball(fireball.weight + weight, fireball.direction, fireball.speed + speed);
        } else {
            map[height][width] = new Fireball(fireball.weight + weight, -1, fireball.speed + speed);
        }
    }

    public static boolean isSameKindOfDirection(int directionA, int directionB) {
        if (directionA == -1 || directionB == -1) {
            return false;
        }

        return directionA % 2 == 0 && directionB % 2 == 0;
    }

    static class Fireball {

        int weight;
        int direction;
        int speed;

        public Fireball(int weight, int direction, int speed) {
            this.weight = weight;
            this.direction = direction;
            this.speed = speed;
        }
    }
}
