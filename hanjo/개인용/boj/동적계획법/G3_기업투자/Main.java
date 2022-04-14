package 동적계획법.G3_기업투자;
// package week11.동적계획법_G3_기업투자;

// import java.io.*;
// import java.util.*;

// public class Main {

//     public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         StringTokenizer st;

//         st = new StringTokenizer(br.readLine());
//         int n = Integer.parseInt(st.nextToken());   // 투자금액
//         int m = Integer.parseInt(st.nextToken());   // 기업 수

//         int[][] company = new int[n][m+1];
//         for(int i=0; i<n; i++){
//             st = new StringTokenizer(br.readLine());
//             company[i][0] = Integer.parseInt(st.nextToken());
//             company[i][1] = Integer.parseInt(st.nextToken());
//             company[i][2] = Integer.parseInt(st.nextToken());
//         }
//     }

//     public static void solution(int n, int m, int[][] company){
//         int[][] dp = new int[n+1][m+1];
        
//         for(int x=1; x<n+1; x++){
//             for(int y=1; y<m+1; y++){
//                 for(int k=1; k<=y; k++){
                
//                 }
//             }
            
//         }
        
//     }
    
// }
