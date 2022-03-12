package 이분탐색.G5_공유기설치;

import java.io.*;
import java.util.*;

public class Main {

    public static int solution(int n, int c, int[] map) {

        Arrays.sort(map);

        // 공유기 최소거리 이분탐색
        int left = 1;
        int right = map[n - 1] - map[0];
        int answer = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            int lastLoc = map[0];
            int wifiCount = 1;

            for (int i = 0; i < n; i++) {
                int curLoc = map[i];
                // 공유기 설치
                if (mid <= curLoc - lastLoc) {
                    lastLoc = curLoc;
                    wifiCount++;
                }
            }

            // 공유기 최소거리 단축
            if (wifiCount < c) {
                right = mid - 1;
            } 
            // 공유기 최소거리 연장
            else {
                answer = mid;
                left = mid + 1;
            }

        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] map = new int[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            map[i] = Integer.parseInt(st.nextToken());
        }

        
        System.out.println(solution(n, c, map));
    }

}

/**
 * 링크 : https://www.acmicpc.net/problem/2110 
 * 성공여부 : 실패
 * 풀이시간 : 3h
 * 
 * 시간복잡도 : O(n*log(10억))
 * 메모리 : 27144KB
 * 소요 시간 : 344ms
 * 
 * 문제풀이의 접근방식이 가장 중요했던 문제
 * 이분탐색 개념 공부필요 (parametric search / upper / lower bownd)
 */