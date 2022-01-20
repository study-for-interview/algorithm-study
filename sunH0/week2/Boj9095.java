import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj9095 {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int max=0;
		for (int d = 0; d < N; d++) {
			arr[d] = Integer.parseInt(br.readLine());
			if (max < arr[d]) {
				max = arr[d];
			}
		}

		int[] dp = new int[max+1];
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;
		dp[4] = 7;

		for (int i = 5; i <= max; i++) {
			dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
		}

		for (int d = 0; d < N; d++) {
			System.out.println(dp[arr[d]]);
		}
	}

}
