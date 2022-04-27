import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

class Edge{
    public int vex;
    public int cost;
    public int isRoad;

    Edge(int vex, int cost, int isRoad) {
        this.vex = vex;
        this.cost = cost;
        this.isRoad = isRoad;
    }
}

class State implements Comparable<State> {
    public int vex;
    public int cost;
    public int state;

    public State(int vex, int cost, int state) {
        this.vex = vex;
        this.cost = cost;
        this.state = state;
    }

    @Override
    public int compareTo(State ob) {
        return this.cost - ob.cost;
    }
}


class Solution {
    static int n, m;
    static ArrayList<ArrayList<Edge>> graph;
    static int d[][] = new int[1004][1<<10];
    static int trapidx[] = new int[1004];

    static boolean bitmask(int state, int node) { // 현재 상태에서 node를 방문했는지 확인
        if(((1<<node) & state) == 0) return false;
        return true;
    }

    static public void dijkstra(int v, int end) {
        PriorityQueue<State> pQ = new PriorityQueue<>();
        int state = 0;
        pQ.offer(new State(v,0,state));
        d[v][state] = 0;

        while (!pQ.isEmpty()) {
            State tmp = pQ.poll();
            int now = tmp.vex;
            int nowCost = tmp.cost;
            int nowState = tmp.state;
            if(nowCost > d[now][nowState]) continue;

            for (Edge ob : graph.get(now)) {
                int rev = 0;
                if(trapidx[now] != 0 && bitmask(nowState, trapidx[now]-1)) rev ^= 1; // 현재 cur.idx번 trap을 밟은 상태라면 rev 상태를 뒤집음
                if(trapidx[ob.vex] != 0 && bitmask(nowState, trapidx[ob.vex]-1)) rev ^= 1; // 현재 nxt.x번 trap을 밟은 상태라면 rev 상태를 뒤집음
                if(ob.isRoad != rev) continue; //갈수 없는 길은 건너뜀
                int nextState = nowState;
                if(trapidx[ob.vex] != 0) nextState = (nextState ^ (1<<trapidx[ob.vex]-1));
                if (d[ob.vex][nextState] > nowCost + ob.cost) {
                    d[ob.vex][nextState] = nowCost + ob.cost;
                    pQ.offer(new State(ob.vex, nowCost + ob.cost, nextState));
                }
            }
        }
    }

    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        int answer = Integer.MAX_VALUE;
        graph = new ArrayList<ArrayList<Edge>>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<Edge>());
        }

        for (int i = 0; i < d.length; i++) {
            Arrays.fill(d[i], Integer.MAX_VALUE);
        }

        for (int i = 0; i < traps.length; i++) {
            trapidx[traps[i]] = i+1;
        }

        for (int i = 0; i < roads.length; i++) {
            int a = roads[i][0];
            int b = roads[i][1];
            int vex = roads[i][2];

            graph.get(a).add(new Edge(b, vex,  0));
            if (trapidx[a] != 0 || trapidx[b] != 0) graph.get(b).add(new Edge(a, vex,  1));
        }


        dijkstra(start,end);
        for (int i = 0; i < (1 << 10); i++) {
            answer = Math.min(answer, d[end][i]);
        }

        System.out.println(answer);
        return answer;
    }

    public static void main(String[] args) {
        Solution T = new Solution();
//        String tstring = "this is {template} {template} is {state}";
//        String[][] variables = {{"template", "string"}, {"state", "changed"}};

//        int n = 3;
//        int start = 1;
//        int end = 3;
//        int roads[][] = {{1, 2, 2}, {3, 2, 3}};
//        int[] traps = {2};

        int n = 4;
        int start = 1;
        int end = 4;
        int roads[][] = {{1, 2, 1}, {3, 2, 1},{2,4,1}};
        int[] traps = {2,3};

        T.solution(n, start, end, roads, traps);

    }
}
