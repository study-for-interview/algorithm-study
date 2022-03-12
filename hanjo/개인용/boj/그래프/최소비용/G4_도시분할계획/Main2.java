package 그래프.최소비용.G4_도시분할계획;

import java.io.*;
import java.util.*;

public class Main2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
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

    public static class Node implements Comparable<Node>{
        public int x;
        public int y;
        public int weight;
        
        public Node(int x, int y, int weight){
            this.x = x;
            this.y = y;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o){
            return this.weight - o.weight;
        }
    }

    public static int N;
    public static PriorityQueue<Node> candidates;
    public static ArrayList<Integer> path = new ArrayList<>();

    public static int solution(int n, int[][] edges){

        N = n;
        candidates = new PriorityQueue<>();
        for(int[] edge : edges){
            candidates.add(new Node(edge[0], edge[1], edge[2]));
        }

        kruskal();

        return path.stream()
            .sorted(Comparator.reverseOrder())
            .skip(1)
            .mapToInt(Integer::intValue)
            .sum();
    }

    public static int[] parent;

    public static void kruskal(){
        
        parent = new int[N+1];
        for(int i=1; i<=N; i++){
            parent[i] = i;
        }

        while(!candidates.isEmpty()){
            Node next = candidates.poll();
            // 사이클 검사
            if(findRoot(next.x) != findRoot(next.y)){
                union(next.x, next.y);
                path.add(next.weight);
            }
        }
    }

    public static int findRoot(int x){
        if(x == parent[x]){
            return x;
        }
        parent[x] = findRoot(parent[x]);
        return parent[x];
    }

    public static void union(int x, int y){
        int rootX = findRoot(x);
        int rootY = findRoot(y);
        
        if(rootX < rootY){
            parent[rootY] = rootX;
        }
        else{
            parent[rootX] = rootY;
        }
    }
}

/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/1647
 * 성공여부 : 성공
 * 풀이시간 : 10m
 * 
 * 시간복잡도 : 우선순위큐 힙정렬 -> O(M*logM) => 백만*log(백만)
 * 메모리 : 332556 KB
 * 소요 시간 : 1484 ms
 * ================================================================================
 * 
 * 프림 알고리즘으로 푼 다음에 크루스칼로 풀었다.
 * MST를 구하는 방식만 다르므로 빠르게 풀 수 있었음.
 * 
 * < 프림 알고리즘과 차이점 >
 * 
 * - 프림에서는 인접리스트를 사용해 그래프를 구현해야함
 * - 반면 크루스칼에서는 간선정보를 리스트에 저장하고 정렬함
 * 
 */