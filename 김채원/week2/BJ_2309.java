import java.io.*;
import java.util.*;


public class BJ_2309{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = 9;
        int sum = 0;
        int[] arr = new int[n];
        int x = 0, y = 0;

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            sum += arr[i];
        }
        Arrays.sort(arr);

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (sum - arr[i] - arr[j] == 100) {
                    x = i;
                    y = j;
                    break;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (i == x || i == y) continue;
            System.out.println(arr[i]);
        }
        bw.flush();
        bw.close();
        br.close();
    }
}



