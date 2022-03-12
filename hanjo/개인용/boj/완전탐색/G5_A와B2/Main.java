package 완전탐색.G5_A와B2;

import java.io.*;

public class Main {

    public static String deleteA(String t) {
        int lastIdx = t.length() - 1;
        StringBuilder sb = new StringBuilder(t);
        return sb.deleteCharAt(lastIdx).toString();
    }

    public static String reverseAndDeleteB(String t) {
        int lastIdx = t.length() - 1;
        StringBuilder sb = new StringBuilder(t);
        return sb.reverse().deleteCharAt(lastIdx).toString();
    }

    public static int reculsive(String s, String t) {

        // 두 문자열 길이가 같아질 때 검사한다
        if (s.length() == t.length()) {
            return s.equals(t) ? 1 : 0;
        }

        // 첫 문자와 마지막 문자에 따라 조건이 달라짐
        char firstT = t.charAt(0);
        char lastT = t.charAt(t.length() - 1);

        if (firstT == 'A' && lastT == 'A') {
            return reculsive(s, deleteA(t));
        } 
        else if (firstT == 'A' && lastT == 'B') {
            return 0;
        } 
        else if (firstT == 'B' && lastT == 'A') {
            int result1 = reculsive(s, deleteA(t));
            int result2 = reculsive(s, reverseAndDeleteB(t));
            return result1 == 1 || result2 == 1 ? 1 : 0;
        } 
        else {
            return reculsive(s, reverseAndDeleteB(t));
        }
    }

    public static int solution(String s, String t) {
        int answer = reculsive(s, t);
        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String s = br.readLine();
        String t = br.readLine();

        System.out.println(solution(s, t));
    }

}

/**
 * 링크 : https://www.acmicpc.net/problem/12919 
 * 성공여부 : X (재귀까지는 생각했으나 두 조건을 동시 실행해야 하는 경우에서 뇌정지가 와서 결국 구글링 함)
 * 풀이시간 : 1h +
 * 
 * 시간복잡도 : O(n) (재귀가 n번 돌때)
 * 메모리 : 17724 KB
 * 소요 시간 : 212 ms
 * 
 * StringBuilder - reverse : 문자열을 거꾸로 돌리기
 * StringBuilder - deleteCharAt : 문자열에서 인덱스에 해당하는 값을 삭제
 */