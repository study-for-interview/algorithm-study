package 완전탐색.S5_날짜계산;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int e = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        System.out.println(solution(e, s, m));
    }

    public static final int E = 15;
    public static final int S = 28;
    public static final int M = 19;
    
    public static int solution(int e, int s, int m){

        int year = 1;

        while(e != 1 || s != 1 || m != 1){
            if(--e == 0){
                e = E;
            }
            if(--s == 0){
                s = S;
            }
            if(--m == 0){
                m = M;
            }
            year++;
        }

        return year;
    }
}

/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/1476
 * 성공여부 : 성공
 * 풀이시간 : 5m
 * 
 * 시간복잡도 : 결과값만큼
 * 메모리 : 14180 KB
 * 소요 시간 : 128 ms
 * ================================================================================
 * 
 * 뭔 문제인가 했는데 그냥 연도를 역으로 1 1 1로 만들면 되는 문제였음
 * 
 */