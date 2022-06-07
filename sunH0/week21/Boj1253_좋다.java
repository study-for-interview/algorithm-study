package 투포인터;

import java.util.*;
import java.io.*;

public class Boj1253_좋다 {
    static int N;
    static int[] nums;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        nums = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);

        int cnt = 0;

        

        for(int i=N-1; i>=0; i--){
            int start = 0;
            int end = N-1;

            while(start<end){
                if(start==i) { start++; continue; }
                if(end==i){ end--; continue; }

                int sum = nums[start]+nums[end];
                if(sum==nums[i]) {
                    cnt++; break;
                }else if(sum>nums[i]) end--;
                else start++;
            }

        }


        System.out.println(cnt);


    }
    
}
