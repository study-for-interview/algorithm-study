import java.io.*;
import java.util.*;

public class BJ_12865 {
    static int n;
    static int m;
    static int dp[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        dp = new int[m+1];

        for (int i = 0; i < n; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st2.nextToken());
            int value = Integer.parseInt(st2.nextToken());
            for (int j = m ; j >= weight; j--) {
                dp[j] = Math.max(dp[j], dp[j - weight] + value);
            }
        }

        System.out.println(dp[m]);

        bw.flush();
        bw.close();
        br.close();
    }

}