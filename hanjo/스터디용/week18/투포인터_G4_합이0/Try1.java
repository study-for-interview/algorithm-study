package week18.투포인터_G4_합이0;

import java.io.*;
import java.util.*;

public class Try1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int[] a = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            a[i] = Integer.parseInt(st.nextToken());
        }
        
        System.out.println(new Solution().solution(a));
    }

    public static class Solution {

        public int n;
        public int[] a;
        public int count;
    
        public int solution(int[] a){
            this.n = a.length;
            this.a = a;
            
            dfs(0, 0, 3);
    
            return count;
        }
    
        public void dfs(int depth, int sum, int r){
            if(r == 0){
                if(sum == 0){
                    count++;
                }
                return;
            }
            if(depth == n){
                return;
            }
            
            dfs(depth+1, sum, r);
            dfs(depth+1, sum+a[depth], r-1);
        }
    }
}





/**
 * 
 * dfs 조합찾기 풀이
 * 시간초과 난다
 * 
 */