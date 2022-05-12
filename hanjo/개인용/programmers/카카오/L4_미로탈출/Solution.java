package 카카오.L4_미로탈출;

import java.util.*;

class Solution {

    public static class Node implements Comparable<Node>{
        public int num;
        public int weight;
        public int mask;

        public Node(int num, int weight, int mask){
            this.num = num;
            this.weight = weight;
            this.mask = mask;
        }

        @Override
        public int compareTo(Node o){
            return this.weight - o.weight;
        }
    }

    public static final int INF = Integer.MAX_VALUE;

    public int n;
    public int trapNum;
    public Map<Integer, Integer> trapMap;
    public Map<Integer, List<Node>> graph;
    public int[][] dist;

    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        this.n = n;
        this.trapNum = traps.length;

        // 함정 map [번호, 인덱스]
        trapMap = new HashMap<>();
        for(int i=0; i<trapNum; i++){
            trapMap.put(traps[i], i);
        }
        
        // 인접리스트, 거리 배열 초기화
        graph = new HashMap<>();
        dist = new int[n+1][1 << trapNum];
        for(int i=1; i<=n; i++){
            graph.put(i, new ArrayList<>());
            Arrays.fill(dist[i], INF);
        }

        // 인접리스트 업데이트
        for(int[] road : roads){
            graph.get(road[0]).add(new Node(road[1], road[2], 0));  // 정방향
            graph.get(road[1]).add(new Node(road[0], road[2], 1));  // 역방향
        }

        // 다익스트라로 최단경로 구하기 
        dijkstra(start);

        // end 중 최소값 찾기
        int min = INF;
        for(int i=0; i< 1<<trapNum; i++){
            min = Math.min(min, dist[end][i]);
        }

        for(int[] d : dist){
            System.out.println(Arrays.toString(d));
        }

        return min;
    }

    public void dijkstra(int start){
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(start, 0, 0));
        dist[start][0] = 0;

        while(!queue.isEmpty()){
            Node cur = queue.poll();

            for(Node next : graph.get(cur.num)){

                // 
                int direction = next.mask;
                if(direction != getEdgeDir(cur.num, next.num, cur.mask)){
                    continue;
                }

                //
                int nextMask = cur.mask;
                if(trapMap.containsKey(next.num)){
                    nextMask ^= 1 << trapMap.get(next.num);
                }
                
                // 최단경로인지 검사
                int nextDist = dist[cur.num][cur.mask] + next.weight;
                if(nextDist >= dist[next.num][nextMask]){
                    continue;
                }

                dist[next.num][nextMask] = nextDist;
                queue.offer(new Node(next.num, next.weight, nextMask));
            }
        }
    }

    public int getEdgeDir(int cur, int next, int mask){

        int curStatus = 0;
        int nextStatus = 0;

        if(trapMap.containsKey(cur)){
            curStatus = mask & (1 << trapMap.get(cur));
        }
        if(trapMap.containsKey(next)){
            nextStatus = mask & (1 << trapMap.get(next));
        }

        if(curStatus == nextStatus){
            return 0;
        }
        // else if(curStatus == 1 && nextStatus == 1){
        //     return 0;
        // }
        else{
            return 1;
        }
    }


    public static void main(String[] args) {
        // 5
        System.out.println(new Solution().solution(
            3, 1, 3, new int[][]{{1,2,2},{3,2,3}}, new int[]{2}
        ));
        // 4
        System.out.println(new Solution().solution(
            4, 1, 4, new int[][]{{1,2,1},{3,2,1}, {2,4,1}}, new int[]{2,3}
        ));
    }
}

// 실패..