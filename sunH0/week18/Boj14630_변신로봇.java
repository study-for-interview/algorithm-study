package dijkstra;

import java.util.*;
import java.io.*;

public class Boj14630_변신로봇 {

    static int start,end,N;
    static PriorityQueue<Node> pq;
    static String[] arr;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new String[N+1];
        for(int i=1;i<=N;i++){
            arr[i] = br.readLine();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        visited = new boolean[N+1];

        
        dijk();

    }

    static void dijk() {

        pq = new PriorityQueue<Node>((Node a,Node b) -> a.w-b.w);
        pq.add(new Node(start,0));

        while(!pq.isEmpty()){
            Node now = pq.poll();
            int idx = now.idx;

            if(idx==end){
                System.out.println(now.w);
                return;
            }

            if(visited[idx]) continue;
            visited[idx] = true;

            for(int i=1;i<=N;i++){
                if(idx!=i){
                    pq.add(new Node(i,now.w+getCost(arr[idx], arr[i])));
                }
            }

        }
        

    }

    public static int getCost(String a , String b){
        int sum = 0 ;
        for(int i=0;i<a.length();i++){
                int v = (a.charAt(i)-b.charAt(i));
                sum += v*v;
        }
        return sum;
    }

    static class Node{
        int idx;
        int w;
        public Node(int idx,int w){
                this.idx = idx;
                this.w = w;
        }
    }       
    
}

/* 
1. 각 변신단계를 노드로 생각하여 다익스트라
2. 어차피 모든 노드로 이동 가능하니 간선이 의미가 없음
3. 현재 노드에서 모든 노드로 이동한 경우를 큐에 삽입
4. 어차피 우선순위 큐에서 가중치에 따라 정렬됨 & 방문처리
5. 가장 먼저 도착지에 도착한 방법이 최소 가중치를 갖게 된다.
*/
