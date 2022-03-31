import java.io.*;
import java.util.*;

// 50점
class BJ_3649 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BJ_3649 T = new BJ_3649();
        String input;

        while ((input = br.readLine()) != null) {
            int n = Integer.parseInt(input) * 10000000; //구멍너비
            int m = Integer.parseInt(br.readLine()); // 레고 조각수
            int[] ans = new int[2];

            int[] arr = new int[m];
            for (int i = 0; i < m; i++) {
                arr[i] = Integer.parseInt(br.readLine());
            }

            if (m <= 1) {
                System.out.println("danger");
                continue;
            }

            Arrays.sort(arr);

            boolean check = false;
            for (int i = m - 1; i > 0; i--) {
                if (arr[i] < n / 2) break;
                int target = n - arr[i];

                int i1 = Arrays.binarySearch(arr,0,i,target);
                if (i1 != -1) {
                    ans[0] = target;
                    ans[1] = arr[i];
                    check = true;
                    break;
                }
            }

            Arrays.sort(ans);


            if (!check) {
                System.out.println("danger");
            } else {
                System.out.println("Yes " + ans[0] + " " + ans[1]);
            }
        }
    }
}
