package MST;

import java.util.*;
import java.io.*;

public class Boj1922_네트워크연결 {

    static int N;
    static int E;
    static int[] parent;
    static PriorityQueue<Node> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        E = Integer.parseInt(br.readLine());

        parent = new int[N+1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        pq = new PriorityQueue<>();

        for (int i=0; i< E; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

            pq.add(new Node(from, to, weight));
        }

		int total =0;

        while(!pq.isEmpty()){
           Node node = pq.poll();        

           if(union(node.a,node.b)) total += node.w;     
   
       }
       System.out.println(total);

    }

    static int find(int a){
        if(a==parent[a]) return a;
        
        parent[a] = find(parent[a]);
        return parent[a];
    }

    static boolean union(int a, int b){
        int x = find(a);
        int y = find(b);

        if(x==y) return false;

        // if(x < y) parent[y] = x;
        // else parent[x] = y;

        parent[y]=x;
        return true;        
    }

    
    static class Node implements Comparable<Node>{
        int a;
        int b;
        int w;
        public Node(int a, int b, int w){
                this.a = a;
                this.b = b;
                this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return o.w >= this.w ? -1 : 1;
        }
    }   
    
}
