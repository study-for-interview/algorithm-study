import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

    private static int[] map;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] input = bufferedReader.readLine().split(" ");

        int height = Integer.parseInt(input[0]);
        int width = Integer.parseInt(input[1]);

        map = new int[width];
        map = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();


    }

    public static void solve(int left, int right) {
        if (right >= map.length) {
            return;
        }

        while (map[left] <= 0) {
            left++;
        }

        right = left + 1;

        while (map[right] <= 0) {
            right++;
        }

        if (Math.abs(left - right) <= 1) {
            solve(right, right);
        } else {

        }
    }
}
