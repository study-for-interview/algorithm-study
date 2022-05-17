package 투포인터;

import java.io.*;
import java.util.*;

public class Boj3151_합이0 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

        Arrays.sort(arr);

        long cnt= 0;
        int start,end,fix;

        for(int i=0;i<N;i++){
            if(arr[i]>0) break;

            fix = arr[i];
            start = i+1;
            end = N-1;

            while(start<end){
                int sum = fix + arr[start]+ arr[end];

                if(sum==0) {
                    int s=1;
                    int e=1;
                    
                    if(arr[start]==arr[end]) {
                        cnt += comb(end-start+1);
                        break;
                    }
                    
                    while(start+1<end && arr[start]==arr[start+1]){
                        start++;
                        s++;                        
                    }

                    while(start<end-1 && arr[end]==arr[end-1]){
                        end--;
                        e++;                        
                    }

                    cnt += s*e;
                    start++;
                }
                else if(sum<0) start++;
                else if(sum>0) end--;                
            }

        }

        System.out.println(cnt);

    }

    static int comb(int n){
        return n*(n-1)/2;
    }
}
