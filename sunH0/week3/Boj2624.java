public class Boj2624 { 
    public static void main(String[] args) throws Exception { 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 

        int target, n; 

        target = Integer.parseInt(br.readLine()); 
        n = Integer.parseInt(br.readLine()); 

        int[][] list = new int[n][2]; 
        int[] dp = new int[target + 1]; 

        for (int x = 0; x < n; x++) { 
            StringTokenizer st = new StringTokenizer(br.readLine()); 
            list[x][0] = Integer.parseInt(st.nextToken()); 
            list[x][1] = Integer.parseInt(st.nextToken()); 
        } 

        dp[0] = 1; 
        for (int y = 0; y < n; y++) { 
            int base = list[y][0]; 
            for (int x = target; x >= base; x--) { 
                for (int w = 1; w <= list[y][1]; w++) { 
                    if (x - base * w < 0) { 
                        break; 
                    } 
                    dp[x] += dp[x - base * w]; 
                } 
            } 
        } 
        
        System.out.println(String.valueOf(dp[target])); 
    }
}

