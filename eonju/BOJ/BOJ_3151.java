import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[] numbers = new int[n];

        String[] input = bufferedReader.readLine().split(" ");
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Integer.parseInt(input[i]);
        }

        Arrays.sort(numbers);

        long answer = 0L;

        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] > 0) {
                break;
            }

            int start = i + 1;
            int end = n - 1;
            while (start < end) {
                int s = 1;
                int e = 1;

                int current = numbers[i] + numbers[start] + numbers[end];

                if (current == 0) {
                    if (numbers[start] == numbers[end]) {
                        answer += comb(end - start + 1);
                        break;
                    }

                    while (start + 1 < end && numbers[start] == numbers[start + 1]) {
                        s++;
                        start++;
                    }

                    while (start < end - 1 && numbers[end] == numbers[end - 1]) {
                        e++;
                        end--;
                    }

                    answer += s * e;
                }

                if (current > 0) {
                    end--;
                } else {
                    start++;
                }
            }
        }

        System.out.println(answer);
    }

    public static int comb(int x) {
        return x * (x - 1) / 2;
    }
}
