import java.util.*;
import java.math.*;
public class BJ_9095 {

    static int dp[] = new int [11];

    public static void main(String[] args)   {
        Scanner sc = new Scanner(System.in);


        int t = sc.nextInt();
        dp[1] =1; //초기 값 초기화
        dp[2]=2;
        dp[3]=4;

        for(int j=4;j<=10;j++) { // 4부터 반복
            dp[j] = dp[j-3] + dp[j-2] + dp[j-1]; // 점화식
        }

        for(int i=0;i<t;i++) {
            int n = sc.nextInt();

            System.out.println(dp[n]);
        }





    }

}