package 그래프.탐색.G4_트리의지름;

import java.io.*;
import java.util.*;

public class Try1 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        int[][] edges = new int[n-1][3];
        for(int i=0; i<n-1; i++){
            st = new StringTokenizer(br.readLine());
            edges[i][0] = Integer.parseInt(st.nextToken());;
            edges[i][1] = Integer.parseInt(st.nextToken());;
            edges[i][2] = Integer.parseInt(st.nextToken());;
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

    public static HashMap<Integer, ArrayList<Node>> tree;
    public static boolean[] isVisited;
    public static ArrayList<Integer> radius = new ArrayList<>();

    public static int solution(int n, int[][] edges){
        
        tree = new HashMap<>();
        for(int i=1; i<=n; i++){
            tree.put(i, new ArrayList<>());
        }
        for(int[] edge : edges){
            tree.get(edge[0]).add(new Node(edge[1], edge[2]));
        }
        isVisited = new boolean[n+1];
        Arrays.fill(isVisited, false);

        dfs(1, 0);

        Collections.sort(radius, Collections.reverseOrder());
        System.out.println(radius);
        
        return radius.get(0) + radius.get(1);
    }

    public static void dfs(int cur, int weightSum){
        
        isVisited[cur] = true;
        ArrayList<Node> children = tree.get(cur);

        if(children.isEmpty()){
            radius.add(weightSum);
            return;
        }
        
        for(Node next : children){
            if(!isVisited[next.num]){
                dfs(next.num, weightSum + next.weight);
            }
        }
    }
}

/**
 * < 시행착오 >
 * 
 * 루트 노드에서 말단 노드까지의 거리 합을 모두 구함
 * 그리고 거리 합 중 가장 큰 두개가 트리의 지름이 되는 줄 알았는데...
 * 
 * 그냥 트리 내에서 존재하는 모든 경로 중 거리가 가장 큰것이 지름이였다...
 * 
 */