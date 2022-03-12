package 정렬.S2_좌표압축;

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        int[] coord = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            coord[i] = Integer.parseInt(st.nextToken());
        }

        solution(n, coord);
    }

    public static void solution(int n, int[] coord){
        // 중복제거
        HashSet<Integer> set = new HashSet<>();
        for(int i=0; i<n; i++){
            set.add(coord[i]);
        }

        // 정렬
        ArrayList<Integer> list = new ArrayList<>(set);
        Collections.sort(list);

        // 맵에 저장
        HashMap<Integer, Integer> hm = new HashMap<>();
        for(int i=0; i<list.size(); i++){
            hm.put(list.get(i), i);
        }

        // 숫자부여
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++){
            int zip = hm.get(coord[i]);
            sb.append(zip).append(" ");
        }

        System.out.print(sb);
    }

    // ------------------------------------------------

    // 실패한 풀이 -> 시간초과 났음
    public static int[] try1(int n, int[] coord){
        // 중복제거
        HashSet<Integer> set = new HashSet<>();
        for(int num : coord){
            set.add(num);
        }

        // 정렬
        ArrayList<Integer> list = new ArrayList<>(set);
        Collections.sort(list);

        // 숫자부여
        for(int i=0; i<n; i++){
            // !! 리스트에서 값 탐색시 시간이 많이 소요되기 때문에 실패함 !!
            int zip = list.indexOf(coord[i]);
            coord[i] = zip;
        }
    
        return coord;
    }
}

/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/18870
 * 성공여부 : 성공
 * 풀이시간 : 50m
 * 
 * 시간복잡도 : O(n*log(n))
 * 메모리 : 252636 KB
 * 소요 시간 : 2184 ms
 * ================================================================================
 * 
 * 문제 자체는 쉬우나 시간초과를 해결해야했음
 * 정렬보다는 탐색에서 시간초과가 일어남
 * -> 원래는 ArrayList에서 값을 탐색해서 O(n) 시간이 걸렸지만, map으로 수정하여 O(1) 시간소요
 * -> 기본 String을 사용해 출력하면 시간초과나서 StringBuffer를 사용함
 * 
 * ================================================================================
 * 
 * Arrays.sort vs Collections.sort
 * 
 * < Arrays.sort >
 * 정렬방식 : DualPivotQuicksort
 * 시간복잡도 : 평균 - O(n*log(n)) / 최악 - O(n^2)
 * 
 * < Collections.sort >
 * 정렬방식 : TimeSort (삽입 + 합병)
 * 시간복잡도 : O(n*log(n))
 * 
 */