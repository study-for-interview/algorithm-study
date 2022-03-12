package 구현.S2_내선물을받아줘2;

import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        String map = br.readLine();

        System.out.println(solution(n, map));
    }

    public static int solution(int n, String map){

        map = map.replace("EW", "X");
        return n - map.length();
    }
}

/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/15886
 * 성공여부 : 성공
 * 풀이시간 : 20m
 * 
 * 시간복잡도 : 문자열교체시간?
 * 메모리 : 14060 KB
 * 소요 시간 : 132 ms
 * ================================================================================
 * 
 * 문제만 이해하면 되는 문제
 * 지도를 그려보고 루프가 생기는 곳이 최종 도착지이므로 루프만 찾으면 됐음
 * 
 */