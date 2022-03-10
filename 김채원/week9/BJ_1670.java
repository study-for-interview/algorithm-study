import java.io.*;
import java.util.*;

public class BJ_1670 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        long[] dp = new long[100001];
        final long div = 987654321;

        dp[0] = 1;
        dp[2] = 1;

        for (int i = 4; i <= n; i++) {
            for (int j = 0; j <= i - 2; j += 2) { // j는 0번 사람을 누군가와 짝지었을때 나눠지는 두영역중 한영역에 남아있는 사람
                dp[i] += dp[j] * dp[i-j-2]; // 0번이랑 한명은 이미 짝지어져있어서 -2 해붐
                dp[i] %= div;
            }
        }

        System.out.println(dp[n]);

        bw.flush();
        bw.close();
        br.close();
    }

}