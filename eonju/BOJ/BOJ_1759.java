import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {

    private static int alphabetQuantity;
    private static int passwordLength;
    private static String[] alpha;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = bufferedReader.readLine().split(" ");
        passwordLength = Integer.parseInt(input[0]);
        alphabetQuantity = Integer.parseInt(input[1]);

        alpha = bufferedReader.readLine().split(" ");
        Arrays.sort(alpha, Comparator.naturalOrder());

        visited = new boolean[alphabetQuantity];

        makeString(0, new StringBuilder(), 0);
    }

    public static void makeString(int idx, StringBuilder sb, int depth) {
        if (depth == passwordLength) {
            if (isOK(sb)) {
                System.out.println(sb);
            }
            return;
        }

        if (idx >= alphabetQuantity) {
            return;
        }

        for (int i = idx; i < alphabetQuantity; i++) {
            if (visited[i]) {
                continue;
            }

            StringBuilder temp = new StringBuilder(sb);
            String target = alpha[i];
            temp.append(target);
            visited[i] = true;
            makeString(i + 1, temp, depth + 1);
            visited[i] = false;
        }
    }

    public static boolean isOK(StringBuilder sb) {
        if (sb.length() < 3) {
            return false;
        }

        int a = 0;
        int b = 0;
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == 'a' || sb.charAt(i) == 'e' || sb.charAt(i) == 'i' || sb.charAt(i) == 'o'
                || sb.charAt(i) == 'u') {
                a++;
                continue;
            }

            b++;
        }

        if (a < 1 || b < 2) {
            return false;
        }

        return true;
    }

}
