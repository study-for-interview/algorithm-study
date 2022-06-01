import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

    private static boolean[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = bufferedReader.readLine().split(" ");
        int mapHeight = Integer.parseInt(input[0]);
        int mapWidth = Integer.parseInt(input[1]);
        int stickerQuantity = Integer.parseInt(input[2]);

        map = new boolean[mapHeight][mapWidth];

        Sticker[] stickers = new Sticker[stickerQuantity];

        for (int i = 0; i < stickerQuantity; i++) {
            input = bufferedReader.readLine().split(" ");
            int height = Integer.parseInt(input[0]);
            int width = Integer.parseInt(input[1]);
            int[][] shape = new int[height][width];

            for (int j = 0; j < height; j++) {
                shape[j] = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }

            stickers[i] = new Sticker(height, width, shape);
        }

        for (int i = 0; i < stickers.length; i++) {
            findLocationAndShape(stickers[i]);
        }

        int answer = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j]) {
                    answer++;
                }
            }
        }

        System.out.println(answer);
    }

    public static void findLocationAndShape(Sticker sticker) {
        while (sticker.turnCnt <= 3) {
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    if (isInBound(sticker.height, sticker.width, i, j) && check(i, j, sticker)) {
                        return;
                    }
                }
            }
            sticker.turn();
        }
    }

    public static boolean check(int height, int width, Sticker sticker) {
        int[][] shape = sticker.shape;
        boolean[][] tmpMap = copyMap();

        for (int i = height; i < height + sticker.height; i++) {
            for (int j = width; j < width + sticker.width; j++) {
                if (map[i][j] && shape[i - height][j - width] == 1) {
                    return false;
                } else if (!map[i][j] && shape[i - height][j - width] == 1) {
                    tmpMap[i][j] = true;
                }
            }
        }

        map = tmpMap;
        return true;
    }

    public static boolean[][] copyMap() {
        boolean[][] copyMap = new boolean[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            copyMap[i] = Arrays.copyOf(map[i], map[i].length);
        }

        return copyMap;
    }

    public static boolean isInBound(int stickerHeight, int stickerWidth, int height, int width) {
        return height + stickerHeight <= map.length && width + stickerWidth <= map[0].length;
    }

    static class Sticker {

        int height;
        int width;
        int[][] shape;
        int turnCnt;

        public Sticker(int height, int width, int[][] shape) {
            this.height = height;
            this.width = width;
            this.shape = shape;
            this.turnCnt = 0;
        }

        public void turn() {
            turnCnt++;
            int[][] newShape = new int[width][height];

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    newShape[j][height - i - 1] = shape[i][j];
                }
            }

            this.height = newShape.length;
            this.width = newShape[0].length;
            this.shape = newShape;
        }
    }
}
