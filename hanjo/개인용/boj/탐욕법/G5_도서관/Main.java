package 탐욕법.G5_도서관;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] library = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            library[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(solution(m, library));
    }

    public static int solution(int m, int[] library){
        // 배열 나누고 정렬
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        left.add(0);
        right.add(0);
        for(int book : library){
            if(book < 0){
                left.add(Math.abs(book));
            }
            else{
                right.add(book);
            }
        }
        Collections.sort(left);
        Collections.sort(right);

        // 탐욕스럽게 계산
        int leftLast = left.size()-1;
        int rightLast = right.size()-1;

        int count = 0;
        for(int i=leftLast; i>=0; i-=m){
            count += left.get(i) * 2;
        }
        for(int i=rightLast; i>=0; i-=m){
            count += right.get(i) * 2;
        }
        int max = Math.max(left.get(leftLast), right.get(rightLast));

        return count - max;
    }
}


/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/1461
 * 날짜 : 220408
 * 성공여부 : 성공
 * 풀이시간 : 1h
 * 
 * 시간복잡도 : 
 * 메모리 : 14176 KB
 * 소요 시간 : 132 ms
 * ================================================================================
 * 
 * 탐욕스러운 방법을 찾는 문제
 * 
 * 맨처음에 떠오른 생각은
 * 1. (절댓값이 최대값 ~ M) 까지는 왕복이 필요없음 => 최댓값만큼 count
 * 2. 음수 양수 리스트를 나누자 -> 리스트에서 (끝 ~ M) 씩 제거하고 끝*2 만큼 count
 * 
 * 근데 1번에서 반례를 찾음
 * -50 0 2 50 이렇게 주어질때 최댓값이 양쪽으로 잡히는데 둘중 어느 쪽을 제거해야할지 선택해야했음
 * 이걸 판단하기는 좀 어려워서 더 생각해보니 걍 2번을 싹다하고 최댓값 한번 빼주면 됐다
 * 
 */