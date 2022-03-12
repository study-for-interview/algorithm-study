package 백트래킹.G5_NQueen;

import java.util.*;
import java.io.*;

public class Main {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        System.out.println(solution(n));
    }


    public static int[] QLocations;
    public static int N;
    public static int answer = 0;

    public static int solution(int n){
        N = n;
        QLocations = new int[N];
        
        dfs(0);
        
        return answer;
    }

    public static void dfs(int row){
        if(row == N){
            answer++;
            return;
        }

        for(int y=0; y<N; y++){
            QLocations[row] = y;
            if(canLocate(row)){
                dfs(row+1);
            }
        }
    }

    public static boolean canLocate(int row){
        
        for(int x=0; x<row; x++){
            if(QLocations[x] == QLocations[row]){
                return false;
            }
            else if(Math.abs(row - x) == Math.abs(QLocations[row] - QLocations[x])){
                return false;
            }
        }
        return true;
    }

}


/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/9663
 * 성공여부 : 실패
 * 풀이시간 : 2h
 * 
 * 시간복잡도 : O(n^n)
 * 메모리 : 14504 KB
 * 소요 시간 : 5476 ms
 * ================================================================================
 * 
 * 강의도 들어보고 구글링한 정답을 참고도 해보았지만 이해하기 힘든 문제임
 * 나중에 아무것도 안본 상태에서 스스로 풀어볼 필요 있음
 * 
 * DFS 도중 조건검사 후 필요없는 부분은 쳐내는 것이 중요 -> 백트래킹
 * 
 */