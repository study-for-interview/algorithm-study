import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1149 {

	static int red = 0;
	static int green = 1;
	static int blue = 2;
	static int N;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		int[][] arr = new int[N+1][3];

		for (int d = 1; d < N+1; d++) {
			StringTokenizer str = new StringTokenizer(br.readLine());

			int R = arr[d - 1][red];
			int G = arr[d - 1][green];
			int B = arr[d - 1][blue];

			arr[d][red] = Math.min(G, B)+Integer.parseInt(str.nextToken());
			arr[d][green] = Math.min(R,B)+Integer.parseInt(str.nextToken());
			arr[d][blue] = Math.min(R,G)+Integer.parseInt(str.nextToken());
		}

		System.out.println(Math.min(arr[N][red],Math.min(arr[N][green],arr[N][blue])));
	}

}
