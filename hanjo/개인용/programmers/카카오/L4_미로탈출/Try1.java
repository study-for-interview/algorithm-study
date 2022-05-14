package 카카오.L4_미로탈출;

import java.util.*;

class Try1 {

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

        @Override
        public String toString(){
            return num + "";
        }
    }

    public static final int INF = Integer.MAX_VALUE;
    public int n;
    public int[] traps;
    public int trapNum;
    public Map<Integer, List<Node>> graph;
    public boolean[][][] isValid;
    public int[][] dist;

    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        this.n = n;
        this.traps = traps;
        this.trapNum = traps.length;

        // 초기화
        graph = new HashMap<>();
        dist = new int[n+1][1 << trapNum];
        isValid = new boolean[n+1][n+1][1 << trapNum];
        for(int i=1; i<=n; i++){
            graph.put(i, new ArrayList<>());
            Arrays.fill(dist[i], INF);
        }
        // 인접리스트 업데이트
        for(int[] road : roads){
            graph.get(road[0]).add(new Node(road[1], road[2], 0));
            graph.get(road[1]).add(new Node(road[0], road[2], 0));
            Arrays.fill(isValid[road[0]][road[1]], true);
        }

        // 다익스트라로 최단경로 구하기
        dijkstra(start);

        // for(int[] d : dist){
        //     System.out.println(Arrays.toString(d));
        // }

        //
        int min = INF;
        for(int i=0; i< 1<<trapNum; i++){
            min = Math.min(min, dist[end][i]);
        }

        return min;
    }

    public void dijkstra(int start){
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(start, 0, 0));
        dist[start][0] = 0;

        while(!queue.isEmpty()){

            // System.out.println(queue);


            Node cur = queue.poll();

            // System.out.println(cur.num + " " + cur.mask);
            // for(int x=0; x<=n; x++){
            //     String temp = "";
            //     for(int y=0; y<=n; y++){
            //         temp += isValid[x][y][cur.mask] + " ";
            //     }
            //     System.out.println(temp);
            // }


            
            for(Node next : graph.get(cur.num)){
                // cur -> next 간선이 유효한지 검사
                if(!isValid[cur.num][next.num][cur.mask]){
                    continue;
                }
                // 다음방문이 함정이면?
                int nextMask = cur.mask;
                for(int i=0; i<trapNum; i++){
                    if(traps[i] == next.num){
                        // System.out.println(next.num + " <---");

                        nextMask |= 1 << i;
                        // isValid 업데이트
                        int trap = traps[i];
                        List<int[]> targets = new ArrayList<>();
                        for(int x=1; x<=n; x++){
                            for(int y=1; y<=n; y++){
                                if(x == trap || y == trap && !(x == trap && y == trap)){
                                    targets.add(new int[]{y, x});
                                }
                                else{
                                    isValid[x][y][nextMask] = isValid[x][y][cur.mask];
                                }
                            }
                        }
                        for(int[] target : targets){
                            isValid[target[0]][target[1]][nextMask] = true;
                        }
                        break;
                    }
                }
               
                // 효율적인지 검사
                int nextDist = dist[cur.num][cur.mask] + next.weight;
                if(nextDist >= dist[next.num][nextMask]){
                    continue;
                }

                dist[next.num][nextMask] = nextDist;
                queue.offer(new Node(next.num, next.weight, nextMask));
            }
            // System.out.println();

        }
    }


    public static void main(String[] args) {
        // 5
        System.out.println(new Try1().solution(
            3, 1, 3, new int[][]{{1,2,2},{3,2,3}}, new int[]{2}
        ));
        // 4
        System.out.println(new Try1().solution(
            4, 1, 4, new int[][]{{1,2,1},{3,2,1}, {2,4,1}}, new int[]{2,3}
        ));
    }
}

/**
 * 
 * 시간초과 + 메모리 너무 큼
 * 
 */