package 그래프.최소비용.G5_소셜네트워킹어플리케이션;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int testCase = Integer.parseInt(br.readLine());
        for(int i=0; i<testCase; i++){
            System.out.printf("Scenario %d:\n", i+1);

            int n = Integer.parseInt(br.readLine());
            int k = Integer.parseInt(br.readLine());
            
            int[][] relations = new int[k][2];
            for(int j=0; j<k; j++){
                st = new StringTokenizer(br.readLine());
                relations[j][0] = Integer.parseInt(st.nextToken());
                relations[j][1] = Integer.parseInt(st.nextToken());
            }

            int m = Integer.parseInt(br.readLine());
            int[][] targets = new int[m][2];
            for(int j=0; j<m; j++){
                st = new StringTokenizer(br.readLine());
                targets[j][0] = Integer.parseInt(st.nextToken());
                targets[j][1] = Integer.parseInt(st.nextToken());
            }

            solution(n, relations, targets);
        }
    }
    
    public static void solution(int n, int[][] relations, int[][] targets){

        int[] parent = new int[n];
        for(int i=0; i<n; i++){
            parent[i] = i;
        }

        for(int[] relation : relations){
            union(relation[0], relation[1], parent);
        }

        StringBuilder sb = new StringBuilder();

        for(int[] target : targets){
            int isRelate = 0;
            if(findRoot(target[0], parent) == findRoot(target[1], parent)){
                isRelate = 1;
            }
            sb.append(isRelate).append("\n");
            // System.out.println(isRelate);
        }
        
        System.out.println(sb.toString());
    }

    public static int findRoot(int a, int[] parent){
        if(a == parent[a]){
            return a;
        }
        parent[a] = findRoot(parent[a], parent);
        return parent[a];
    }

    public static void union(int a, int b, int[] parent){
        int rootA = findRoot(a, parent);
        int rootB = findRoot(b, parent);

        if(rootA > rootB){
            parent[rootA] = rootB;
        }
        else{
            parent[rootB] = rootA;
        }
    }
}


/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/7511
 * 날짜 : 220413
 * 성공여부 : 성공
 * 풀이시간 : 40m
 * 
 * 시간복잡도 : 찾기에서 O(1)
 * 메모리 : 168772 KB
 * 소요 시간 : 1000 ms
 * ================================================================================
 * 
 * union find 정석 문제
 * 
 * 유니온 파인드 개념만 사용되는 문제임. 올만에 보니까 또 헷갈렸음
 * 
 * 마지막에 a b가 서로 관계있는지 비교할때 그냥 parent 배열 값으로 비교해도 될줄 알았는데,
 * 다시 findRoot 해줘서 비교해야 했음
 * 당연히 비교할때마다 parent가 업데이트 되므로.. union이 끝난 마지막 값이 완벽한게 아님.
 * (물론 타고가면 동일한 root를 가리키게 됨.)
 * 
 * 출력때 StringBuilder 안쓰면 시간 3배 걸렸음 (별 상관은 없음)
 * 
 */