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
            .toArray();

        Arrays.sort(numbers);

        int answer = 0;
        for (int i = 0; i < n; i++) {
            int target = numbers[i];

            if(isGood(i, target, numbers)){
                answer++;
            }
        }

        System.out.println(answer);
    }

    public static boolean isGood(int targetIdx, int target, int[] numbers) {
        for (int j = 0; j < targetIdx; j++) {
            for (int k = j + 1; k < targetIdx; k++) {
                if (numbers[j] + numbers[k] == target) {
                    return true;
                }
            }
        }

        return false;
    }
}
