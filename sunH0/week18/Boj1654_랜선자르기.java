package 이분탐색;

import java.util.*;
import java.io.*;

public class Boj1654_랜선자르기 {

    static int K,N;
    static int[] lines;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        lines = new int[K];
        long max=0;
        for(int i=0;i<K;i++){
            lines[i] = Integer.parseInt(br.readLine());
            if(max<lines[i]) max = lines[i];
        }
        
        max++;

        upperBound(max);        

    }

    static void upperBound(long max) {

        long min = 0; 
		long mid = 0;

		while (min < max) { 
			
			mid = (max + min) / 2;
			
			long count = 0;
 
			for (int i = 0; i < lines.length; i++) {
				count += (lines[i] / mid);
			}
			

			if(count < N) {
				max = mid;
			}
			else {
				min = mid + 1;
			}
 
		}
		
		System.out.println(min - 1);
	}
}

    
/* 
이분탐색를 활용해 주어진 배열을 나눌수 있는 최적의 길이를 계산해야 하는 문제(파라메트릭 서치)
나눠지는 개수 중 최대의 길이를 구해야 하기 때문에 초과된 수를 구하는 upperbound를 구한 후 -1을 해주었다.
max++를 해야하는 것이 함정 : mid가 0이 되는 현상을 방지
*/

