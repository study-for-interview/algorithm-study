import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {

    private static HashMap<Integer, List<Edge>> graph;

    public static void main(String[] args) {
        solution(7, 4, 3, 1, new int[][]{{5, 7, 9}, {4, 6, 4}, {3, 6, 1}, {3, 2, 3}, {2, 1, 6}});
    }

    public static int solution(int n, int s, int a, int b, int[][] fares) {
        graph = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int i = 0; i < fares.length; i++) {
            int[] fare = fares[i];

            graph.get(fare[0]).add(new Edge(fare[1], fare[2]));
            graph.get(fare[1]).add(new Edge(fare[0], fare[2]));
        }

        int[] distA = new int[n + 1];
        Arrays.fill(distA, Integer.MAX_VALUE);

        int[] distB = new int[n + 1];
        Arrays.fill(distB, Integer.MAX_VALUE);

        int[] distS = new int[n + 1];
        Arrays.fill(distS, Integer.MAX_VALUE);

        dijkstra(distA, a);
        dijkstra(distB, b);
        dijkstra(distS, s);

        int min = distA[s] + distB[s];
        for (int i = 1; i <= n; i++) {
            if (min > distA[i] + distB[i] + distS[i]) {
                min = distA[i] + distB[i] + distS[i];
            }
        }

        return min;
    }

    public static void dijkstra(int[] dist, int start) {
        PriorityQueue<Edge> queue = new PriorityQueue<>(Comparator.comparing(x -> x.weight));
        queue.add(new Edge(start, 0));
        dist[start] = 0;

        while (!queue.isEmpty()) {
            Edge poll = queue.poll();
            if (poll.weight > dist[poll.end]) {
                continue;
            }

            for (Edge edge : graph.get(poll.end)) {
                if (edge.weight + poll.weight < dist[edge.end]) {
                    dist[edge.end] = edge.weight + poll.weight;
                    queue.add(new Edge(edge.end, dist[edge.end]));
                }
            }
        }

    }


    static class Edge {

        int end;
        int weight;

        public Edge(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }
    }

}
