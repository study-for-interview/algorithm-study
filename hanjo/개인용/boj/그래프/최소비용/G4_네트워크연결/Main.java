package 그래프.최소비용.G4_네트워크연결;

import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());

        int[][] edges = new int[m][3];
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            edges[i][0] = Integer.parseInt(st.nextToken());
            edges[i][1] = Integer.parseInt(st.nextToken());
            edges[i][2] = Integer.parseInt(st.nextToken());
        }
        
        System.out.println(solution(n, edges));
    }

    public static class Edge implements Comparable<Edge>{
        public int x;
        public int y;
        public int weight;
        
        public Edge(int x, int y, int weight){
            this.x = x;
            this.y = y;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o){
            return this.weight - o.weight;
        }
    }

    public static int solution(int n, int[][] edges){

        PriorityQueue<Edge> candidates = new PriorityQueue<>();
        for(int[] edge : edges){
            candidates.add(new Edge(edge[0], edge[1], edge[2]));
        }

        int[] parent = new int[n+1];
        for(int i=0; i<n+1; i++){
            parent[i] = i;
        }

        int sum = 0;
        while(!candidates.isEmpty()){
            Edge newEdge = candidates.poll();
            if(find(parent, newEdge.x) != find(parent, newEdge.y)){
                union(parent, newEdge.x, newEdge.y);
                sum += newEdge.weight;
            }
        }

        return sum;
    }

    public static int find(int[] parent, int x){
        if(parent[x] == x){
            return x;
        }
        // 재귀의 끝에서 최상위 부모를 찾고
        // 재귀를 빠져나오면서 하위 자식까지 모두 업데이트시킴
        parent[x] = find(parent, parent[x]);
        return parent[x];
    }

    public static void union(int[] parent, int x, int y){
        x = find(parent, x);
        y = find(parent, y);
        if(x != y){
            if(x < y){
                parent[y] = x;
            }
            else{
                parent[x] = y;
            }
        }
    }
}


/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/1922
 * 성공여부 : 성공
 * 풀이시간 : 1H
 * 
 * 시간복잡도 : 우선순위큐 힙정렬 -> O(N*logN)
 * 메모리 : 53056 KB
 * 소요 시간 : 568 ms
 * ================================================================================
 * 
 * 크루스칼 알고리즘 정석 문제
 * 알고리즘 자체는 어렵지 않고 union find 사용법만 잘 익히면 될것 같다
 * find 함수에서 부모값 찾기 + 자식까지 모두 부모를 바라보게 만들어줘야하는게 뽀인트
 * 
 */