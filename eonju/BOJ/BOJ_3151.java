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

            int leftIdx = i + 1;
            int rightIdx = n - 1;
            while (leftIdx < rightIdx) {
                int leftCnt = 1;
                int rightCnt = 1;

                int current = numbers[i] + numbers[leftIdx] + numbers[rightIdx];

                if (current == 0) {
                    if (numbers[leftIdx] == numbers[rightIdx]) {
                        answer += comb(rightIdx - leftIdx + 1);
                        break;
                    }

                    while (leftIdx + 1 < rightIdx && numbers[leftIdx] == numbers[leftIdx + 1]) {
                        leftCnt++;
                        leftIdx++;
                    }

                    while (leftIdx < rightIdx - 1 && numbers[rightIdx] == numbers[rightIdx - 1]) {
                        rightCnt++;
                        rightIdx--;
                    }

                    answer += leftCnt * rightCnt;
                }

                if (current > 0) {
                    rightIdx--;
                } else {
                    leftIdx++;
                }
            }
        }

        System.out.println(answer);
    }

    public static int comb(int x) {
        return x * (x - 1) / 2;
    }
}
