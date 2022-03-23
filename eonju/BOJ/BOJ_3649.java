import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main2 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String input;

        while ((input = bufferedReader.readLine()) != null) {
            int want = Integer.parseInt(input) * 10000000;
            int legoQuantity = Integer.parseInt(bufferedReader.readLine());
            int[] legos = new int[legoQuantity];

            for (int i = 0; i < legoQuantity; i++) {
                legos[i] = Integer.parseInt(bufferedReader.readLine());
            }
            Arrays.sort(legos);

            int left = 0;
            int right = legoQuantity - 1;
            boolean flag = false;

            while (left < right) {
                int sum = legos[left] + legos[right];

                if (sum == want) {
                    flag = true;
                    break;
                } else if (sum < want) {
                    left++;
                } else {
                    right--;
                }
            }

            if (flag) {
                System.out.println("yes " + legos[left] + " " + legos[right]);
            } else {
                System.out.println("danger");
            }
        }
    }

}
