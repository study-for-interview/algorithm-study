import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    private static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int numberCnt = Integer.parseInt(bufferedReader.readLine());

        int[] numbers = new int[numberCnt];

        String[] input = bufferedReader.readLine().split(" ");
        for (int i = 0; i < input.length; i++) {
            numbers[i] = Integer.parseInt(input[i]);
        }

        answer = 0;
        count(1, numbers, numbers[0], numbers[numbers.length - 1]);
        System.out.println(answer);
    }

    public static void count(int idx, int[] numbers, int sum, int target) {
        if (idx == numbers.length - 1) {
            if (sum == target) {
                answer++;
            }

            return;
        }

        if (sum + numbers[idx] >= 0 && sum + numbers[idx] <= 20) {
            count(idx + 1, numbers, sum + numbers[idx], target);
        }

        if (sum - numbers[idx] >= 0 && sum - numbers[idx] <= 20) {
            count(idx + 1, numbers, sum - numbers[idx], target);
        }
    }
}
