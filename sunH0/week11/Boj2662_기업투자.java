package BOJ;

import java.io.*;
import java.util.StringTokenizer;

public class Boj2662_기업투자 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =new StringTokenizer(br.readLine());
		
		int N=Integer.parseInt(st.nextToken());
		int M=Integer.parseInt(st.nextToken());

		int input[][] = new int[M+1][N+1];
		int dp [][]  = new int[M+1][N+1];
		int inv [][] = new int[M+1][N+1];

		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			int nn = Integer.parseInt(st.nextToken());
			for(int j=1;j<=M;j++) {
				input[j][nn]=Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=1;i<=M;i++) {
			for(int j=1;j<=N;j++) {
				for(int k=0;k<=j;k++) {
					if(dp[i][j]<dp[i-1][j-k]+input[i][k]) {
						dp[i][j]=dp[i-1][j-k]+input[i][k];
						inv[i][j]=k;
					}
				}
				
			}
		}

		System.out.println(dp[M][N]);

		int answer[]= new int [21];
		for(int i=M,j=N;i>=1;i--) {
			answer[i]=inv[i][j];
			j-=inv[i][j];
		}
		
		for(int i=1;i<=M;i++) {
			System.out.print(answer[i]+" ");
		}
		
		
	}
    
}
