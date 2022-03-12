package 그래프.최소비용.G4_집합의표현;

import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] commands = new int[m][3];
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            commands[i][0] = Integer.parseInt(st.nextToken());
            commands[i][1] = Integer.parseInt(st.nextToken());
            commands[i][2] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(n, commands));
    }

    public static int[] parent;

    public static String solution(int n, int[][] commands){

        parent = new int[n+1];
        for(int i=0; i<n+1; i++){
            parent[i] = i;
        }

        StringBuilder sb = new StringBuilder();
        for(int[] command : commands){
            if(command[0] == 0){
                union(command[1], command[2]);
            }
            else{
                boolean result = isSameParent(command[1], command[2]);
                if(result){
                    sb.append("YES\n");
                }
                else{
                    sb.append("NO\n");
                }
            }
        }
        
        return sb.toString();
    }

    public static int findParent(int x){
        // 반복문으로 하면 65%에서 시간초과남. 
        // while(x != parent[x]){
        //     x = parent[x];
        // }
        // return x;

        if (x == parent[x]) {
            return x;
        }
        return parent[x] = findParent(parent[x]);
    }

    public static void union(int x, int y){
        // 최상위 부모를 찾아 그것만 비교하면 됨
        x = findParent(x);
        y = findParent(y);

        parent[x] = y;
 
        // if (x != y) {
        //     if (x < y) {
        //         parent[y] = x;
        //     } else {
        //         parent[x] = y;
        //     }
        // }
    }

    public static boolean isSameParent(int x, int y){
        return findParent(x) == findParent(y);
    }

}

/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/1717
 * 성공여부 : 실패 (구글링함)
 * 풀이시간 : 1H
 * 
 * 시간복잡도 : ?
 * 메모리 : 53936 KB
 * 소요 시간 : 456 ms
 * ================================================================================
 * 
 * 최소신장트리(MST)의 크루스칼 알고리즘에 등장하는 Union find라는 알고리즘 기초 문제
 * 배열에 부모 노드 인덱스를 저장하여 관계를 구현함
 * 인덱스 크기가 작은것을 우선순위로 하고 그 뒤로 붙이는 방식
 * 
 * 최상위 부모를 찾는 함수 findParent에서 while로 찾았더니 시간초과가 남
 * 이유를 아직 모르겠음
 * 
 */