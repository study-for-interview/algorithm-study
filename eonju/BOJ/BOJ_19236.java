import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    private static int[][] move = {{0, 0}, {-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}};
    private static Location sharkLocation;
    private static Fish[][] map;
    private static boolean[][] visited;
    private static Location[] fishLocation;
    private static Shark shark;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        map = new Fish[4][4];
        fishLocation = new Location[17];

        for (int i = 0; i < 4; i++) {
            String[] input = bufferedReader.readLine().split(" ");
            int idx = 0;
            for (int j = 0; j < input.length; j += 2) {
                int number = Integer.parseInt(input[j]);
                int direction = Integer.parseInt(input[j + 1]);

                fishLocation[number] = new Location(i, idx);
                map[i][idx] = new Fish(number, direction);
                idx++;
            }
        }

        sharkLocation = new Location(0, 0);
        shark = new Shark(map[0][0]);

        moveFish();

        visited = new boolean[4][4];
        visited[0][0] = true;
        int max = moveShark(sharkLocation.i, sharkLocation.j, shark.direction, shark.sum);

        System.out.println(max);
    }

    public static int moveShark(int nowI, int nowJ, int direction, int sum) {
        int max = sum;
        for (int i = 1; i <= 4; i++) {
            int moveI = move[direction][0] * i;
            int moveJ = move[direction][1] * i;
            int nextI = nowI + moveI;
            int nextJ = nowJ + moveJ;

            if (nextI < 0 || nextJ < 0 || nextI >= 4 || nextJ >= 4) {
                continue;
            }

            if (map[nowI][nowJ].number == 0 || visited[nowI][nowJ]) {
                continue;
            }

            visited[nextI][nextJ] = true;
            Fish nextFish = map[nextI][nextJ];
            max = Math.max(max, moveShark(nextI, nextJ, nextFish.direction, nextFish.number + sum));
            visited[nextI][nextJ] = false;
        }

        return max;
    }

    public static void moveFish() {
        for (int i = 1; i < fishLocation.length; i++) {
            Location location = fishLocation[i];
            int nowI = location.i;
            int nowJ = location.j;

            Fish nowFish = map[nowI][nowJ];

            while (true) {
                int direction = nowFish.direction;
                int nextI = nowI + move[direction][0];
                int nextJ = nowJ + move[direction][1];

                if (nextI < 0 || nextJ < 0 || nextI >= 4 || nextJ >= 4) {
                    nowFish.turnDirection();
                    continue;
                }

                if (sharkLocation.i == nextI && sharkLocation.j == nextJ) {
                    nowFish.turnDirection();
                    continue;
                }

                Fish nextFish = map[nextI][nextJ];
                if (nextFish.number == 0) {
                    swapFish(nowFish, nextFish, fishLocation[nowFish.number], new Location(nextI, nextJ));
                } else {
                    swapFish(nowFish, nextFish, fishLocation[nowFish.number], fishLocation[nextFish.number]);
                }
                break;
            }
        }
    }

    public static void swapFish(Fish fishA, Fish fishB, Location locationA, Location locationB) {
        int tempNumber = fishA.number;
        int tempDirection = fishA.direction;

        fishA.number = fishB.number;
        fishA.direction = fishB.direction;
        fishB.number = tempNumber;
        fishB.direction = tempDirection;

        fishLocation[fishA.number] = locationB;
        fishLocation[fishB.number] = locationA;

    }

    static class Shark {

        int sum;
        int direction;

        public void eatFish(Fish fish) {
            this.sum += fish.number;
            this.direction = fish.direction;
            fish.number = 0;
            fish.direction = 0;
        }

        public Shark(Fish fish) {
            eatFish(fish);
        }
    }

    static class Fish {

        int number;
        int direction;

        public Fish(int number, int direction) {
            this.number = number;
            this.direction = direction;
        }

        public void turnDirection() {
            if (this.direction + 1 >= 9) {
                this.direction = 1;
            } else {
                this.direction++;
            }
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
