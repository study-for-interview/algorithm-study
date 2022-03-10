import java.io.*;
import java.util.*;

public class Boj1670_정상회담2 {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        long[] dp = new long[N + 1];

        dp[0] = 1;
        dp[1] = 1;
        int div = 987654321;

        for(int i=2; i<=N; i+=2) {
            for(int j =0; j<=i-2;j+=2){
                dp[i] += dp[j] * dp[i-j-2];
            }
        }

        System.out.println(dp[N] % div);

    }

}

// dp(6) = dp(6-2) * dp(0) + dp(6-4)*dp(2) + dp(6-6)*dp(4)