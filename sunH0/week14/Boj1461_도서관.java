package Greedy;

import java.util.*;
import java.io.*;

public class Boj1461_도서관 {
    

    public static void main (String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // PriorityQueue<Integer> minus = new PriorityQueue<>(Collections.reverseOrder());
        // PriorityQueue<Integer> plus = new PriorityQueue<>(Collections.reverseOrder());

        ArrayList<Integer> plus = new ArrayList<>();
        ArrayList<Integer> minus = new ArrayList<>();

        st =  new StringTokenizer(br.readLine());

        for(int i=0; i<N; i++){
            
            int num = Integer.parseInt(st.nextToken());

            if(num>0) plus.add(num);
            else minus.add(Math.abs(num));

        }

        Collections.sort(plus, Collections.reverseOrder());
        Collections.sort(minus, Collections.reverseOrder());
        
        int max=0;
        
        if(plus.size()==0) max = minus.get(0);
        else if(minus.size()==0) max=plus.get(0);
        else max = Math.max(plus.get(0),minus.get(0));

        int plusBatch = (plus.size()/M) +1;
        int minusBatch = (minus.size()/M) +1;
        int ans = 0;
        
        if(plus.size() != 0){
            for(int i=0;i<plus.size();i=i+M){
                ans += plus.get(i)*2;        
            }
        }


        if(minus.size() != 0){
            for(int i=0;i<minus.size();i=i+M){
                ans += minus.get(i)*2;        
            }
        }

        System.out.println(ans-max);
    }

}

/* 
1. 제일 작은 음수와 제일 큰 양수 의 절대 값 비교 : 절대값이 가장 큰 방향이 마지막 코스
2. 첫번째 방향은 절대값 큰 수부터 들수 있는 만큼 묶기 / 마지막 묶음은 땡처리  / 묶음의 가장 큰 수 *2 해서 적립 큐??
3. 두번째 방향은 절대값 큰 수 부터 들 수 있는 만큼 묶기 / 마지막 묶음 땡터리 / 가장 큰 묶음 + (나머지 묶음 *2 ) 적립

*/
