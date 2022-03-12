package 그래프.최소비용.G4_도시분할계획;

import java.io.*;
import java.util.*;

public class Main {

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
        public int num;
        public int weight;
        
        public Node(int num, int weight){
            this.num = num;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o){
            return this.weight - o.weight;
        }
    }

    public static int N;
    public static HashMap<Integer, ArrayList<Node>> graph;
    public static ArrayList<Integer> path = new ArrayList<>();

    public static int solution(int n, int[][] edges){

        N = n;
        graph = new HashMap<>();
        for(int i=1; i<=n; i++){
            graph.put(i, new ArrayList<>());
        }
        for(int[] edge : edges){
            graph.get(edge[0]).add(new Node(edge[1], edge[2]));
            graph.get(edge[1]).add(new Node(edge[0], edge[2]));
        }

        prim();

        return path.stream()
            .sorted(Comparator.reverseOrder())
            .skip(1)
            .mapToInt(Integer::intValue)
            .sum();
    }

    public static void prim(){

        boolean[] isVisited = new boolean[N+1];

        PriorityQueue<Node> queue = new PriorityQueue<>();

        queue.offer(new Node(1, 0));

        while(!queue.isEmpty()){
            Node cur = queue.poll();
            
            // 다른 후보 간선으로 방문했을 수도 있음
            if(isVisited[cur.num]){
                continue;
            }
            isVisited[cur.num] = true;
            path.add(cur.weight);

            for(Node next : graph.get(cur.num)){
                queue.offer(next);
            }
        }
    }
}

/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/1647
 * 성공여부 : 성공
 * 풀이시간 : 1H
 * 
 * 시간복잡도 : 프림 - O(M*logN) => 백만*log십만
 * 메모리 : 359700 KB
 * 소요 시간 : 3004 ms
 * ================================================================================
 * 
 * 최소신장트리(MST) 문제임.
 * 프림이나 크루스칼 둘다 사용해도 상관없다.
 * 현재 파일은 프림 알고리즘으로 풀고 Main2에서 크루스칼 알고리즘으로 품
 * 
 * 문제를 처음 읽고서 이해가 잘 되지 않았음
 * 결론은 일단 모든 집들을 최소비용으로 연결해놓고, 그 경로를 두개로 나누는게(마을 분리) 목표임
 * 마을을 나눌 때 두 마을은 연결되지 않아도 된다고 했으니, 경로에서 가장 값이 큰 간선을 없애버리면 된다 
 * 
 * 프림 알고리즘 구현은 처음해봤는데, 일반 BFS처럼 풀려다가 시행착오를 겪었다
 * 
 * < 일반 BFS (트리가 아닌 그래프) >
 * 내가 현재 방문한 정점과 연결된 정점 중 방문하지 않은 정점은 몽땅 큐에 넣는다
 * 딱 큐에 넣을 때 방문체크를 해줘야 다음 방문정점에서 큐에 들어있는 놈을 또 큐에 넣지 않음
 * 
 * < 프림 알고리즘 >
 * 우선순위 큐를 쓰기 때문에 연결정점은 모두 후보가 됨 -> 일단 연결된 놈들 다 때려넣음
 * 그리고 가장 가중치가 작은 정점 순으로 방문하면서 그 때 체크해준다.
 * 
 */