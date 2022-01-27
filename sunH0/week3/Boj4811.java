public class Boj4811 {

    static long[][] dp;
    
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        dp = new long[31][31];

        dp[1][0] = 1;
        dp[2][0] = 2;
        dp[3][0] = 5;
        
        eat(30, 0);
        
        int n = 0;
        while(true) {
            
            n = Integer.parseInt(br.readLine());
            if(n == 0) break;
            
            System.out.println(dp[n][0]);
        }
        
    }
 
    private static long eat(int one, int half) {
        
        if(one == 0) return 1;

        if(dp[one][half] != 0) return dp[one][half]; 
        
        long cnt = 0;

        if(one != 0) {
            cnt += eat(one - 1, half + 1);
        }

        if(half != 0) {
            cnt += eat(one, half - 1);
        }
        
        return dp[one][half] = cnt;
    }
}