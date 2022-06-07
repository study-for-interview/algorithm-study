import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    private static boolean[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = bufferedReader.readLine().split(" ");
        int height = Integer.parseInt(input[0]);
        int width = Integer.parseInt(input[1]);

        map = new boolean[width][height];

        input = bufferedReader.readLine().split(" ");
        for (int i = 0; i < input.length; i++) {
            fillMap(i, Integer.parseInt(input[i]));
        }

        int answer = count(width, height);
        System.out.println(answer);
    }

    public static void fillMap(int idx, int height) {
        for (int i = 0; i < height; i++) {
            map[idx][i] = true;
        }
    }

    public static int count(int height, int width) {
        int cnt = 0;

        for (int i = 0; i < width; i++) {
            int left = -1;
            int right = -1;
            int tmp = 0;

            for (int j = 0; j < height; j++) {
                if (map[j][i] && left == -1) {
                    left = j;
                    continue;
                }
                if (map[j][i] && left != -1 && right == -1) {
                    cnt += tmp;
                    tmp = 0;
                    right = j;
                    continue;
                }
                if (map[j][i] && left != -1 && right != -1) {
                    cnt += tmp;
                    tmp = 0;
                    left = right;
                    right = j;
                    continue;
                }
                if (!map[j][i] && left != -1) {
                    tmp++;
                }
            }
        }

        return cnt;
    }

}
