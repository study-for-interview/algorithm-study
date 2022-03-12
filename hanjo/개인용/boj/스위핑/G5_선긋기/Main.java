package 스위핑.G5_선긋기;

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        int[][] coords = new int[n][2];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            coords[i][0] = Integer.parseInt(st.nextToken());
            coords[i][1] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(coords));
    }


    public static int solution(int[][] coords){
        
        Arrays.sort(coords, (o1, o2) -> o1[0] - o2[0]);

        int count = 0;
        int[] cur = new int[]{coords[0][0], coords[0][1]};
        for(int[] coord : coords){
            if(cur[1] >= coord[0]){
                if(cur[1] <= coord[1]){
                    cur[1] = coord[1];
                }
            }
            else{
                count += cur[1] - cur[0];
                cur[0] = coord[0];
                cur[1] = coord[1];
            }
        }

        count += cur[1] - cur[0];
        return count;
    }
}

/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/2170
 * 성공여부 : 성공
 * 풀이시간 : 1h
 * 
 * 시간복잡도 : 정렬(DualPivotQuicksort) -> O(n*logn) / 로직 -> O(n)
 * 메모리 : 278196 KB
 * 소요 시간 : 2876 ms
 * ================================================================================
 * 
 * 정렬만 되어있다면 쉬운 문제
 * 하지만 맨처음에 cur 좌표와 coord 좌표의 좌우 모든 케이스를 고려하지 않고 코딩해서 시간 다잡아먹음.. 
 * 처음 구현방식 구상할때 꼼꼼하게 케이스를 체크할 필요있음
 * 
 */ 