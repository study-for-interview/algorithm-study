import java.io.*;
import java.util.*;

public class Boj21941_문자열제거 {

    private static String S;
    private static int N;
    private static Map<String, Integer> map;
    private static int[] dp;
    
    public static void main(String args[]) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
      S = br.readLine();
      int M = Integer.parseInt(br.readLine());

      N = S.length();
      map = new HashMap<>();
      dp = new int[S.length()];
      Arrays.fill(dp, -1);

      for (int i=0;i<M;i++) {
          StringTokenizer st = new StringTokenizer(br.readLine());
          String str = st.nextToken();
          int score = Integer.parseInt(st.nextToken());
          
          if (str.length() >= score) continue;
          map.put(str, score);
      }

      System.out.println(dfs(0));

    }
    
    private static int dfs(int idx) {
        if (idx >= N) {
            return 0;
        }
        
        if (dp[idx] != -1) {
            return dp[idx];
        }
           
        for (String str : map.keySet()) {
            int length = str.length();
            if (S.startsWith(str, idx)) {
                dp[idx] = Math.max(dp[idx], dfs(idx + length) + map.get(str));
            }
        }
        
        dp[idx] = Math.max(dp[idx], dfs(idx + 1) + 1);
        
        return dp[idx];
    }
}

// 미리 알지 못했다면 dp 인 줄 몰랐을듯..
// https://www.acmicpc.net/problem/21941
