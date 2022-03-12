package 이분탐색.S4_숫자카드2;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        int[] cards = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());

        int[] numbers = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(cards, numbers));
    }

    
    public static String solution(int[] cards, int[] numbers){

        Arrays.sort(cards);

        StringBuilder sb = new StringBuilder();
        for(int number : numbers){
            int answer = upperBound(cards, number) - lowerBound(cards, number);
            sb.append(answer).append(" ");
        }

        return sb.toString();
    }

    public static int lowerBound(int[] cards, int number) {
        int left = 0;
        int right = cards.length;

        while (left < right) {
            int mid = (left + right) / 2;
            if (cards[mid] >= number) {
                right = mid;
            } 
            else {
                left = mid + 1;
            }
        }

        return left;
    }

    public static int upperBound(int[] cards, int number) {
        int left = 0;
        int right = cards.length;

        while (left < right) {
            int mid = (left + right) / 2;
            if (cards[mid] > number) {
                right = mid;
            } 
            else {
                left = mid + 1;
            }
        }

        return left;
    }
}

/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/10816
 * 성공여부 : 성공
 * 풀이시간 : 1h
 * 
 * 시간복잡도 : O(M*logN)
 * 메모리 : 124220 KB
 * 소요 시간 : 1576 ms
 * ================================================================================
 * 
 * lower bound와 upper bound 개념을 활용하는 문제
 * left와 right를 어떻게 설정할지
 * 탐색은 언제 끝날지를 잘 생각해야함
 * 
 * < 기존 이분탐색과 다른점 >
 * 내가 구현하던 기존 이분탐색은 L, R을 mid 보다 1 크거나 적게 설정했지만
 * lower/upper bound는 L을 mid + 1로 설정해서 오른쪽으로만 밀리게 함
 * 
 * 
 */