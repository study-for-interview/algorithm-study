package 완전탐색.S4_한수;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        System.out.println(solution(n));
    }
    
    public static int solution(int n){
        
        int count = 0;
        for(int i=1; i<=n; i++){
            if(i < 100){
                count++;
                continue;
            }
            String[] arr = String.valueOf(i).split("");
            int n1 = Integer.parseInt(arr[0]);
            int n2 = Integer.parseInt(arr[1]);
            int n3 = Integer.parseInt(arr[2]);
            if(n1-n2 == n2-n3){
                count++;
            }
        }
        return count;
    }
}


/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/1065
 * 날짜 : 220330
 * 성공여부 : 성공
 * 풀이시간 : 20m
 * 
 * 시간복잡도 : 
 * 메모리 : 16920 KB
 * 소요 시간 : 184 ms
 * ================================================================================
 * 
 * 그냥 완전탐색문제
 * 
 * 조건이 1 ~ 1000으로 정해져있어서 쉬움
 * 
 * 두자리수까지는 무조건 한수
 * 세자리수는 등차수열인지 확인해야함
 * 네자리수는 1000밖에 없고 이것은 한수가 아니므로 생각안해도됨
 * 
 * 즉 세자리수일때만 검사해주면 된다.. ㅋㅋ
 * 
 */