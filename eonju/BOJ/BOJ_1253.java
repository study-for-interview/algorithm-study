import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[] numbers = Arrays.stream(bufferedReader.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .sorted()
            .toArray();

        if (numbers.length < 3) {
            System.out.println(0);
            return;
        }

        int answer = 0;

        for (int i = 2; i < n; i++) {
            int target = numbers[i];
            int left = 0;
            int right = i - 1;

            while (left < right) {
                if (target == numbers[left] + numbers[right]) {
                    answer++;
                    break;
                }

                if (target > numbers[left] + numbers[right]){
                    left ++;
                    continue;
                }

                if(target < numbers[left] + numbers[right]){
                    right--;
                }
            }
        }

        System.out.println(answer);
    }
}
