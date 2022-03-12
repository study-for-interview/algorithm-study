package 탐욕법.G5_행복유치원;

import java.util.*;
import java.io.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] line = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            line[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(k, line));
    }

    public static int solution(int k, int[] line){

        ArrayList<Integer> diff = new ArrayList<>();
        for(int i=0; i<line.length-1; i++){
            diff.add(line[i+1] - line[i]);
        }
        Collections.sort(diff, Collections.reverseOrder());

        int sum = 0;
        for(int i=k-1; i<diff.size(); i++){
            sum += diff.get(i);
        }

        return sum;
    }
}

/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/13164
 * 성공여부 : 실패
 * 풀이시간 : 30m
 * 
 * 시간복잡도 : ??
 * 메모리 : 59940 KB
 * 소요 시간 : 756 ms
 * ================================================================================
 * 
 * 리얼 탐욕법스러운 문제
 * 주어진 배열을 보고 여기서 어떻게 효율적인 방법을 찾아낼까를 고민해야함
 * 물론 떠오르지 않아서 구글링했는데, 막상 해답을 보고나니 좀 허무했음
 * 
 * - 원생사이의 모든 차 diff를 구해서 배열에 넣는다
 * - diff는 조들의 비용도 될 수 있지만 각 조의 경계값도 의미한다.
 * - 각 조의 경계값이 가장 큰 경우에 앞뒤로 조가 만들어져야 효율적
 * - k개의 조가 만들어진다면 k-1개의 경계값이 존재함
 * 
 * 창의적인 방법만 잘 떠오른다면(?) 술술 풀리는 카테고리인 것 같다...
 * 
 */