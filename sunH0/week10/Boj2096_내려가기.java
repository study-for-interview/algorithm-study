package DP;

import java.util.*;
 
public class Boj2096_내려가기 {
 
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        int N = scan.nextInt();
        int[][] arr = new int[N][3];

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < 3; j++) {
                arr[i][j] = scan.nextInt();
            }
        }
 
        int[][] minDp = new int[N][3];
        int[][] maxDp = new int[N][3];

        for(int i = 0; i < N; i++) {

            if(i==0) {
                maxDp[i][0] = arr[i][0];
                maxDp[i][1] = arr[i][1];
                maxDp[i][2] = arr[i][2];
                
                minDp[i][0] = arr[i][0];
                minDp[i][1] = arr[i][1];
                minDp[i][2] = arr[i][2];

                continue;
            }
            maxDp[i][0] += Math.max(maxDp[i - 1][0], maxDp[i - 1][1]) + arr[i][0];
            maxDp[i][1] += Math.max(Math.max(maxDp[i - 1][0], maxDp[i - 1][1]), maxDp[i - 1][2]) + arr[i][1];
            maxDp[i][2] += Math.max(maxDp[i - 1][1], maxDp[i - 1][2]) + arr[i][2];
            
            minDp[i][0] += Math.min(minDp[i - 1][0], minDp[i - 1][1]) + arr[i][0];
            minDp[i][1] += Math.min(Math.min(minDp[i - 1][0], minDp[i - 1][1]), minDp[i - 1][2]) + arr[i][1];
            minDp[i][2] += Math.min(minDp[i - 1][1], minDp[i - 1][2]) + arr[i][2];
        }
        
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for(int i = 0; i < 3; i++) {
            min = Math.min(min, minDp[N-1][i]);
            max = Math.max(max, maxDp[N-1][i]);
        }

        System.out.println(max + " " + min);
    }
}
