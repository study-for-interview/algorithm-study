import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;

import java.util.*;

/*
A에서 B 최단거리 구하고
시작지점에서 A에서 B 지나온 노드들 중에서 가장 짧게 걸리는거 더해주고
만약 A B 지나온 루트랑 겹치면 빼주기
 */
class Edge implements Comparable<Edge> {
    public int vex;
    public int cost;

    Edge(int vex, int cost) {
        this.vex = vex;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge ob) {
        return this.cost - ob.cost;
    }
}

class Solution {
    static ArrayList<ArrayList<Edge>> graph;
    static int[] dis;

    public void solution(int v) {
        PriorityQueue<Edge> pQ = new PriorityQueue<>();
        pQ.offer(new Edge(v, 0));
        dis[v] = 0;
        while (!pQ.isEmpty()) {
            Edge tmp = pQ.poll();
            int now = tmp.vex;
            int nowCost = tmp.cost;
            if (nowCost > dis[now]) continue;
            for (Edge ob : graph.get(now)) {
                if (dis[ob.vex] > nowCost + ob.cost) {
                    dis[ob.vex] = nowCost + ob.cost;
                    pQ.offer(new Edge(ob.vex, nowCost + ob.cost));
                }
            }
        }
    }

    static void dfs(int now, int a, int b) {

        for (int i = 0; i < 3; i++) { //같이 동행 / A만 / B만
            for (Edge ob : graph.get(now)) {

            }
        }
    }

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;
        graph = new ArrayList<ArrayList<Edge>>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<Edge>());
        }
        dis = new int[n + 1];
        Arrays.fill(dis, Integer.MAX_VALUE);
        for (int i = 0; i < fares.length; i++) {
            int x = fares[i][0];
            int y = fares[0][1];
            int z = fares[0][2];
            graph.get(x).add(new Edge(y, z));
        }
        return answer;
    }


    public static void main(String[] args) {
        Solution T = new Solution();

        int n = 6;
        int s = 4;
        int a = 6;
        int b = 2;

        int[][] fares = {{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}
                , {2, 3, 22}, {1, 6, 25}};
        T.solution(n, s, a, b, fares);

    }
}
