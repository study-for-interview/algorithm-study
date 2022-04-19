import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;

class Main {

    static int N;
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < t; i++) {
            N = Integer.parseInt(bufferedReader.readLine());
            comb(1, 1, "1");
            answer.append("\n");
        }

        System.out.println(answer.toString());
    }

    static void comb(int startNumber, int depth, String sik) {
        if (depth == N) {
            if (calculate(sik) == 0) {
                answer.append(sik + "\n");
            }
            return;
        }

        comb(startNumber + 1, depth + 1, sik + ' ' + (startNumber + 1));
        comb(startNumber + 1, depth + 1, sik + '+' + (startNumber + 1));
        comb(startNumber + 1, depth + 1, sik + '-' + (startNumber + 1));

    }


    public static int calculate(String sik) {
        sik = sik.replaceAll(" ", "");

        Iterator<Integer> iterator = Arrays.stream(sik.split("[+,-]"))
            .map(Integer::parseInt)
            .collect(toList())
            .iterator();

        int result = iterator.next();

        for (int i = 0; i < sik.length(); i++) {
            if (sik.charAt(i) == '+') {
                result += iterator.next();
            } else if (sik.charAt(i) == '-') {
                result -= iterator.next();
            }
        }

        return result;
    }

}
