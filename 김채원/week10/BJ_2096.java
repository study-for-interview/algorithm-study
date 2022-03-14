
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ_2096 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[] maxDp = new int[3];
		int[] minDp = new int[3];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			int x1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int x3 = Integer.parseInt(st.nextToken());

			if (i == 0) {
				maxDp[0] = minDp[0] = x1;
				maxDp[1] = minDp[1] = x2;
				maxDp[2] = minDp[2] = x3;
			} else {
				// 최댓값을 구하는 dp 배열
				int beforeMaxDp_0 = maxDp[0], beforeMaxDp_2 = maxDp[2];
				maxDp[0] = Math.max(maxDp[0], maxDp[1]) + x1;
				maxDp[2] = Math.max(maxDp[1], maxDp[2]) + x3;
				maxDp[1] = Math.max(Math.max(beforeMaxDp_0, maxDp[1]), beforeMaxDp_2) + x2;

				// 최솟값을 구하는 dp 배열
				int beforeMinDp_0 = minDp[0], beforeMinDp_2 = minDp[2];
				minDp[0] = Math.min(minDp[0], minDp[1]) + x1;
				minDp[2] = Math.min(minDp[1], minDp[2]) + x3;
				minDp[1] = Math.min(Math.min(beforeMinDp_0, minDp[1]), beforeMinDp_2) + x2;
			}
		}

		bw.write(Math.max(maxDp[0], Math.max(maxDp[1], maxDp[2])) + " ");
		bw.write(Math.min(minDp[0], Math.min(minDp[1], minDp[2])) + "\n");
		bw.flush();
		bw.close();
		br.close();
	}

}