import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

    private static boolean[] sosu;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(bufferedReader.readLine());

        sosu = new boolean[10000];
        Arrays.fill(sosu, true);

        sosu[0] = false;
        sosu[1] = false;

        for (int i = 2; i < 10000; i++) {
            if (!sosu[i]) {
                continue;
            }
            for (int j = 2 * i; j < 10000; j += i) {
                sosu[j] = false;
            }
        }

        for (int i = 0; i < testCase; i++) {
            String[] input = bufferedReader.readLine().split(" ");
            String targetA = input[0];
            String targetB = input[1];

            int answer = change(targetA, targetB);
            if (answer == -1) {
                System.out.println("Impossible");
            }
        }

    }

    public static int change(String targetA, String targetB) {
        int numberA = Integer.parseInt(targetA);
        int numberB = Integer.parseInt(targetB);

        if (!sosu[numberA] || !sosu[numberB]) {
            return -1;
        } else if (targetA.equals(targetB)) {
            return 0;
        }

    }
}
