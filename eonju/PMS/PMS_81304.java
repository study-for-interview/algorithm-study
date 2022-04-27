import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

class Solution {

    static int INF = Integer.MAX_VALUE;
    static int[][] dist;
    static List<Node>[] list, rList;
    static Map<Integer, Integer> trapList;

    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        list = new ArrayList[n + 1];
        rList = new ArrayList[n + 1];

        for (int i = 1; i < n + 1; i++) {
            list[i] = new ArrayList<>();
            rList[i] = new ArrayList<>();
        }

        trapList = new HashMap<>();
        for (int i = 0; i < traps.length; i++) {
            trapList.put(traps[i], 1 << (i + 1));
        }

        for (int i = 0; i < roads.length; i++) {
            int from = roads[i][0];
            int to = roads[i][1];
            int w = roads[i][2];

            list[from].add(new Node(to, w, 0));
            rList[to].add(new Node(from, w, 0));
        }

        int len = 1 << 11;
        dist = new int[n + 1][len];
        for (int i = 1; i < n + 1; i++) {
            Arrays.fill(dist[i], INF);
        }

        dijkstra(start, end);

        int answer = INF;
        for (int ca : dist[end]) {
            answer = Math.min(answer, ca);
        }
        return answer;
    }

    static void dijkstra(int start, int end) {
        Queue<Node> queue = new PriorityQueue<>();
        dist[start][0] = 0;
        queue.add(new Node(start, 0, 0));

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int to = node.end;
            int w = node.weight;
            int status = node.status;

            if (to == end) {
                return;
            }

            // f1 (pos node) ? trap o : (trap x 또는 일반)
            boolean f1 = false;
            if (trapList.containsKey(to)) {
                if ((status & trapList.get(to)) == trapList.get(to)) {
                    f1 = true;
                }
            }

            // 정방향 (tt, ff)
            // f2(nxt node) ? trap o : (trap x 또는 일반)
            boolean f2 = false;
            for (Node nxt : list[to]) {
                int nStatus = status;
                if (trapList.containsKey(nxt.end)) {
                    f2 = ((status & trapList.get(nxt.end)) != 0);
                    nStatus = trapSwitch(f2, status, trapList.get(nxt.end));

                    if (f1 & f2 || (!f1 & !f2)) { // (!f1^f2)
                        if (dist[nxt.end][status] > w + nxt.weight) {
                            dist[nxt.end][status] = w + nxt.weight;
                            queue.add(new Node(nxt.end, dist[nxt.end][status], nStatus));
                        }
                    }
                } else {
                    if (!f1) {
                        if (dist[nxt.end][status] > w + nxt.weight) {
                            dist[nxt.end][status] = w + nxt.weight;
                            queue.add(new Node(nxt.end, dist[nxt.end][status], nStatus));
                        }
                    }
                }
            }

            // 역방향 (tf, ft)
            // f2(nxt node) ? trap o : (trap x 또는 일반)
            f2 = false;
            for (Node nxt : rList[to]) {
                int nStatus = status;
                if (trapList.containsKey(nxt.end)) {
                    f2 = ((status & trapList.get(nxt.end)) != 0);
                    nStatus = trapSwitch(f2, status, trapList.get(nxt.end));
                }
                if (f1 ^ f2) {
                    if (dist[nxt.end][status] > w + nxt.weight) {
                        dist[nxt.end][status] = w + nxt.weight;
                        queue.add(new Node(nxt.end, dist[nxt.end][status], nStatus));
                    }
                }
            }
        }
    }

    static int trapSwitch(boolean flag, int now, int node) {
        if (flag) {             // 다음 노드가 trap인데 활성화 되어있는 경우
            return now ^ node;
        } else {                 // 다음 노드가 trap인데 활성화 되어있지 않은 경우
            return now | node;
        }
    }

    static class Node implements Comparable<Node> {

        int end;
        int weight;
        int status;

        public Node(int end, int weight, int status) {
            this.end = end;
            this.weight = weight;
            this.status = status;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }
}
