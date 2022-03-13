package 그래프.최단경로.G4_특정한최단경로;

import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        int[][] edges = new int[e][3];
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            edges[i][0] = Integer.parseInt(st.nextToken());
            edges[i][1] = Integer.parseInt(st.nextToken());
            edges[i][2] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        System.out.println(solution(n, e, edges, v1, v2));
    }

    public static class Vertex implements Comparable<Vertex> {
        public int num;
        public int weight;

        public Vertex(int num, int weight) {
            this.num = num;
            this.weight = weight;
        }

        @Override
        public int compareTo(Vertex o) {
            return this.weight - o.weight;
        }
    }

    public final static int INF = Integer.MAX_VALUE;
    public static int N;
    public static HashMap<Integer, ArrayList<Vertex>> graph;

    public static int solution(int n, int e, int[][] edges, int v1, int v2) {
        // 전역변수 설정
        N = n;

        // 인접리스트 만들기
        graph = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            graph.put(i, new ArrayList<Vertex>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(new Vertex(edge[1], edge[2]));
            graph.get(edge[1]).add(new Vertex(edge[0], edge[2]));
        }

        // shortest path 1 : 1 -> v1 -> v2 -> n 
        int sp1 = sumShortestPath(v1, v2);

        // shortest path 2 : 1 -> v2 -> v1 -> n
        int sp2 = sumShortestPath(v2, v1);

        int answer = Math.min(sp1, sp2);
        return answer == INF ? -1 : answer;
    }

    public static int sumShortestPath(int firstV, int secondV){
        int path1 = dijkstra(1, firstV);
        int path2 = dijkstra(firstV, secondV);
        int path3 = dijkstra(secondV, N);
        if(path1 == INF || path2 == INF || path3 == INF) 
            return INF;
        return path1 + path2 + path3;
    }

    public static int dijkstra(int from, int to) {

        int[] distance = new int[N + 1];
        Arrays.fill(distance, 1, N+1, INF);
        distance[from] = 0;

        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        pq.offer(new Vertex(from, 0));

        while (!pq.isEmpty()) {
            Vertex curV = pq.poll();

            for (Vertex nextV : graph.get(curV.num)) {
                int newDistance = distance[curV.num] + nextV.weight;
                if (distance[nextV.num] > newDistance) {
                    distance[nextV.num] = newDistance;
                    pq.offer(nextV);
                }
            }
        }

        return distance[to];
    }
}


/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/1504
 * 성공여부 : 실패
 * 풀이시간 : 3h (INF 엣지케이스 때문에 시간너무씀)
 * 
 * 시간복잡도 : O()
 * 메모리 : 86284 KB
 * 소요 시간 : 1112 ms / 우선순위큐 사용시 - 964 ms
 * ================================================================================
 * 
 * 다익스트라를 어떻게 활용하는지가 중요한 문제
 * 필수 방문 정점이 있기 때문에 케이스를 나눠서 최단경로를 구하는 방법을 떠올러야 함
 * 
 * 1. 인접리스트를 어떻게 만들지 생각하기 -> 해시맵 사용
 * 2. 정점 정보를 어떻게 저장할지 생각하기 -> 정점 클래스를 만듬
 * 3. 우선순위 큐의 우선순위 설정해주기 -> 정점 클래스에서 comparable 상속
 * 
 * <시행착오>
 * 무한대를 표현하는 INF를 Integer.MAX_VALUE로 표현했는데, 
 * int의 범위를 넘어가게 될때 최소값으로 넘어가는 현상이 발생
 * 
 * ================================================================================
 * 
 * Comparable vs Comparator
 * 공통점 : 둘다 -1, 0, 1 을 리턴하여 비교
 * 차이점 : Comparable은 클래스 내부에서 비교함 (자기자신과 매개변수 o 비교)
 *          Comparator는 두개의 매개변수를 비교함 (매개변수 o1, o2를 비교)
 * 
 * Comparable - compareTo (기준이 클래스 자기자신)
 * 1 : 자신보다 비교할 대상(o)이 더 크다
 * 0 : 둘이 같다
 * -1 : 자신보다 비교할 대상(o)이 더 작다
 * 
 * Comparator (기준이 o1)
 * 1 : o1이 o2보다 크다
 * 0 : 둘이 같다
 * -1 : o1이 o2보다 작다
 * 
 * PriorityQueue 에서 결과를 받으면...
 * 1(비교대상이 큰 경우): 비교대상을 앞으로 보냄 -> 내림차순
 * 0 : 비교대상을 뒤로 보냄
 * -1(비교대상이 작은 경우) : 비교대상을 뒤로 보냄 -> 오름차순
 *
 */

