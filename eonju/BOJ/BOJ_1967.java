import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

class Main {

    private static int nodeQuantity;
    private static HashMap<Integer, List<Edge>> tree;
    private static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        nodeQuantity = Integer.parseInt(bufferedReader.readLine());

        tree = new HashMap<>();
        for (int i = 1; i <= nodeQuantity; i++) {
            tree.put(i, new ArrayList<>());
        }

        for (int i = 0; i < nodeQuantity - 1; i++) {
            String[] input = bufferedReader.readLine().split(" ");
            int nodeA = Integer.parseInt(input[0]);
            int nodeB = Integer.parseInt(input[1]);
            int weight = Integer.parseInt(input[2]);

            tree.get(nodeA).add(new Edge(nodeB, weight));
            tree.get(nodeB).add(new Edge(nodeA, weight));
        }

        int max = 0;
        int idx = 0;
        dist = new int[nodeQuantity + 1];

        dijkstra(1);
        for (int i = 1; i < dist.length; i++) {
            if (max < dist[i]) {
                max = dist[i];
                idx = i;
            }
        }

        max = 0;

        dijkstra(idx);
        for (int i = 1; i < dist.length; i++) {
            if (max < dist[i]) {
                max = dist[i];
            }
        }

        System.out.println(max);
    }

    public static void dijkstra(int start) {
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Edge> queue = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.weight));
        queue.add(new Edge(start, 0));
        dist[start] = 0;

        while (!queue.isEmpty()) {
            Edge poll = queue.poll();

            if (poll.weight > dist[poll.end]) {
                continue;
            }

            List<Edge> edges = tree.get(poll.end);
            if (edges.isEmpty()) {
                continue;
            }

            for (Edge edge : edges) {
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
