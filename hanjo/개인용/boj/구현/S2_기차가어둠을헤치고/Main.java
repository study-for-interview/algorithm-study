package 구현.S2_기차가어둠을헤치고;

import java.util.*;
import java.io.*;

public class Main {

    public static int n;
    public static int m;
    public static int[][] train;
    public static final int SEAT = 20;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        train = new int[n][SEAT];

        for (int a = 0; a < m; a++) {
            st = new StringTokenizer(br.readLine());

            int command = Integer.parseInt(st.nextToken());
            int i = Integer.parseInt(st.nextToken()) - 1;

            if (command <= 2) {
                int x = Integer.parseInt(st.nextToken()) - 1;
                if (command == 1) command1(i, x);
                if (command == 2) command2(i, x);
            } 
            else {
                if (command == 3) command3(i);
                if (command == 4) command4(i);
            }   
        }

        int count = 1;
        for(int a=1; a<n; a++){

            int duplicate = 0;
            for(int b=0; b<a; b++){
                duplicate = 0;
                // 일일히 좌석 검사
                for(int s=0; s<SEAT; s++){
                    if(train[a][s] == train[b][s])
                        duplicate ++;
                    else
                        break;
                }
                if(duplicate == SEAT)
                    break;
            }

            if(duplicate != SEAT)
                count++;
        }

        System.out.println(count);
    }

    public static void command1(int i, int x) {
        train[i][x] = 1;
    }

    public static void command2(int i, int x) {
        train[i][x] = 0;
    }

    public static void command3(int i) {
        for (int a = SEAT-1; a > 0; a--) {
            train[i][a] = train[i][a-1];
        }
        train[i][0] = 0;
    }

    public static void command4(int i) {
        for (int a = 0; a < SEAT-1; a++) {
            train[i][a] = train[i][a+1];
        }
        train[i][SEAT-1] = 0;
    }

}

/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/15787
 * 성공여부 : 성공
 * 풀이시간 : 1h
 * 
 * 시간복잡도 : 정렬시 O(n) / 검사시 O(n*n!*20)
 * 메모리 : 46812 KB
 * 소요 시간 : 648 ms
 * ================================================================================
 * 
 * 그냥 구현문제
 * for문으로만 조지는 문제였음... 영양가 없음
 * 마지막 검사조건에서 머리가 안돌아가서 시간 오래걸림. 이런문제에 한시간 쓴게 자괴감듬
 * (30분내로 거뜬히 구현해야 할것 같은 문제)
 * 
 */