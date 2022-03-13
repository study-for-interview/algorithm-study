package 그래프.탐색.G4_트리의지름;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        if(n == 1){
            System.out.println(0);
            return;
        }
        
        int[][] edges = new int[n-1][3];
        for(int i=0; i<n-1; i++){
            st = new StringTokenizer(br.readLine());
            edges[i][0] = Integer.parseInt(st.nextToken());
            edges[i][1] = Integer.parseInt(st.nextToken());
            edges[i][2] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(n, edges));
    }

    public static class Node {
        public int num;
        public int weight;

        public Node(int num, int weight){
            this.num = num;
            this.weight = weight;
        }
    }

    public static HashMap<Integer, ArrayList<Node>> graph;
    public static boolean[] isVisited;
    public static Node maxNode = new Node(0, 0);

    public static int solution(int n, int[][] edges){
        
        graph = new HashMap<>();
        for(int i=1; i<=n; i++){
            graph.put(i, new ArrayList<>());
        }
        // '양방향' 인접리스트로 생성함
        for(int[] edge : edges){
            graph.get(edge[0]).add(new Node(edge[1], edge[2]));
            graph.get(edge[1]).add(new Node(edge[0], edge[2]));
        }
        isVisited = new boolean[n+1];
        Arrays.fill(isVisited, false);

        // 루트에서 가장 먼 노드를 찾는다.
        dfs(1, 0);
        
        // 해당 노드에서 가장 먼 노드를 찾는다
        Arrays.fill(isVisited, false);
        maxNode.weight = 0;
        dfs(maxNode.num, 0);
        
        return maxNode.weight;
    }

    public static void dfs(int cur, int weightSum){
        
        isVisited[cur] = true;

        for(Node next :  graph.get(cur)){
            if(!isVisited[next.num]){
                dfs(next.num, weightSum + next.weight);
            }
        }

        if(weightSum > maxNode.weight){
            maxNode.num = cur;
            maxNode.weight = weightSum;
        }
    }
}

/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/1967
 * 성공여부 : 실패 (구글링)
 * 풀이시간 : 1H
 * 
 * 시간복잡도 : dfs : O(V^2) 두번함
 * 메모리 : 24472 KB
 * 소요 시간 : 272 ms
 * ================================================================================
 * 
 * 트리의 지름을 찾기 위해 2번의 DFS를 해야하는 문제
 * 트리의 지름은 트리 내에 존재하는 가장 큰 경로임
 * 
 * 따라서 지름을 구하는 방법을 구글링 했음
 * 
 * 1. 루트노드에서 가장 먼(경로의 가중치 합이 큰) 노드(1)를 찾음 -> 첫번째 DFS
 * 2. 해당 노드에서 또 가장 먼 노드(2)를 찾음 -> 두번째 DFS
 * 3. 그럼 1번 노드와 2번 노드의 경로가 지름이 된다.
 * 
 * (시행착오는 Try1 참고)
 * 
 */