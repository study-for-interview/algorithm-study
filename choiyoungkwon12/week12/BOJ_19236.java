package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/**
 * https://www.acmicpc.net/problem/19236 청소년 상어
 */
public class BOJ_19236 {

    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
    static int maxSum = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[][] arr = new int[4][4];
        List<Fish> fishs = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 4; j++) {
                Fish fish = new Fish(i, j, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) - 1);
                fishs.add(fish);
                arr[i][j] = fish.id;
            }
        }

        fishs.sort(Comparator.comparingInt(o -> o.id));

        Fish fish = fishs.get(arr[0][0] - 1);
        Shark shark = new Shark(0, 0, fish.dir, fish.id);
        fish.isAlive = false;
        arr[0][0] = -1;

        dfs(arr, shark, fishs);
        System.out.println(maxSum);
    }

    private static void dfs(int[][] arr, Shark shark, List<Fish> fishes) {
        if (maxSum < shark.eatSum) {
            maxSum = shark.eatSum;
        }

        for (Fish fish : fishes) {
            moveFish(fish, arr, fishes);
        }

        for (int i = 1; i < 4; i++) {
            int nx = shark.x + dx[shark.dir] * i;
            int ny = shark.y + dy[shark.dir] * i;

            if (0 <= nx && nx < 4 && 0 <= ny && ny < 4 && arr[nx][ny] > 0) {
                int[][] copy = copyArr(arr);
                List<Fish> fishCopy = copyFishes(fishes);

                copy[shark.x][shark.y] = 0;
                Fish f = fishCopy.get(arr[nx][ny] - 1);
                Shark newShark = new Shark(f.x, f.y, f.dir, shark.eatSum + f.id);
                f.isAlive = false;
                copy[f.x][f.y] = -1;

                dfs(copy, newShark, fishCopy);
            }
        }
    }

    private static List<Fish> copyFishes(List<Fish> fishes) {
        return fishes.stream().map(fish -> new Fish(fish.x, fish.y, fish.id, fish.dir, fish.isAlive))
            .collect(Collectors.toList());
    }

    private static int[][] copyArr(int[][] arr) {
        int[][] temp = new int[4][4];

        for (int j = 0; j < 4; j++) {
            for (int k = 0; k < 4; k++) {
                temp[j][k] = arr[j][k];
            }
        }

        return temp;
    }

    private static void moveFish(Fish fish, int[][] arr, List<Fish> fishes) {
        if (!fish.isAlive) {
            return;
        }

        for (int i = 0; i < 8; i++) {
            int nextDir = (fish.dir + i) % 8;
            int nx = fish.x + dx[nextDir];
            int ny = fish.y + dy[nextDir];

            if (0 <= nx && nx < 4 && 0 <= ny && ny < 4 && arr[nx][ny] > -1) {
                arr[fish.x][fish.y] = 0;

                if (arr[nx][ny] == 0) {
                    fish.x = nx;
                    fish.y = ny;
                } else {
                    Fish temp = fishes.get(arr[nx][ny] - 1);
                    temp.x = fish.x;
                    temp.y = fish.y;
                    arr[fish.x][fish.y] = temp.id;

                    fish.x = nx;
                    fish.y = ny;
                }

                arr[nx][ny] = fish.id;
                fish.dir = nextDir;
                return;
            }
        }
    }

    static class Fish {

        int x, y, id, dir;
        boolean isAlive = true;

        public Fish(int x, int y, int id, int dir) {
            this.x = x;
            this.y = y;
            this.id = id;
            this.dir = dir;
        }

        Fish(int x, int y, int id, int dir, boolean isAlive) {
            this.x = x;
            this.y = y;
            this.id = id;
            this.dir = dir;
            this.isAlive = isAlive;
        }
    }

    static class Shark {

        int x, y, dir, eatSum;

        Shark(int x, int y, int dir, int eatSum) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.eatSum = eatSum;
        }
    }
}
