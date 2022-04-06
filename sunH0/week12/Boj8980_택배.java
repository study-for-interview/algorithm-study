package Greedy;

import java.util.*;
import java.io.*;

public class Boj8980_택배 {

    static class Delivery implements Comparable<Delivery> {
        int start;
        int end;
        int box;

        Delivery(int start, int end, int box) {
            this.start = start;
            this.end = end;
            this.box = box;
        }

        @Override
        public int compareTo(Delivery o) {
            if(this.end==o.end) return this.start -  o.start;
            else return this.end - o.end;
            
        }

    }

    public static void main (String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(br.readLine());

        Delivery deliveries[] = new Delivery[M];     
	
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int box = Integer.parseInt(st.nextToken());

            deliveries[i] = new Delivery(start, end, box);
        }

        Arrays.sort(deliveries);

        int max,now;
        int ans = 0;
        int arr[] = new int[N+1];

        for(int i=0; i<M;i++){
            int start = deliveries[i].start;
            int end = deliveries[i].end;
            int box = deliveries[i].box;
            
            max = 0;
            for(int j=start;j<end;j++){
                max = Math.max(arr[j], max);
            }

            now = Math.min(C-max,box);

            for(int k=start;k<end;k++){
                arr[k] += now;
            }
            ans += now;
        }

        System.out.println(ans);

        }

        
                
    }
    
        
// 출발 마을 기준으로 정렬하면 도착 지점이 먼 경우 트럭 공간이 낭비 된다. => 도착 지점을 기준으로 정렬하는 것이 핵심
