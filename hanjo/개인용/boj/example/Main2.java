package example;

import java.io.*;
import java.util.*;

public class Main2 {

    public static int solution(int a, int b){
        return a - b;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());


        System.out.println(solution(a, b));
    }
    
}


/**
 * ================================================================================
 * 링크 : 
 * 성공여부 : 
 * 풀이시간 : 
 * 
 * 시간복잡도 : 
 * 메모리 : KB
 * 소요 시간 : ms
 * ================================================================================
 * 
 * 
 */