import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

class BOJ_1238 {

    private static int N;
    private static int M;
    private static int X;
    private static HashMap<Integer, List<Edge>> graph;
    private static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = bufferedReader.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        X = Integer.parseInt(input[2]);

        graph = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            input = bufferedReader.readLine().split(" ");
            int targetA = Integer.parseInt(input[0]);
            int targetB = Integer.parseInt(input[1]);
            int weight = Integer.parseInt(input[2]);

            graph.get(targetA).add(new Edge(targetB, weight));
        }

        dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dijkstra(X);
        int[] answers = dist.clone();

        for (int i = 1; i <= N; i++) {
            Arrays.fill(dist, Integer.MAX_VALUE);
            dijkstra(i);
            answers[i] += dist[X];
        }

        int max = answers[1];
        for (int i = 1; i <= N; i++) {
            if (max < answers[i]) {
                max = answers[i];
            }
        }

        System.out.println(max);
    }

    public static void dijkstra(int start) {
        PriorityQueue<Edge> queue = new PriorityQueue<>(Comparator.comparingInt(Edge::getWeight));

        dist[start] = 0;
        queue.add(new Edge(start, 0));

        while (!queue.isEmpty()) {
            Edge poll = queue.poll();

            if (poll.getWeight() > dist[poll.getEnd()]) {
                continue;
            }

            List<Edge> edges = graph.get(poll.getEnd());
            for (Edge edge : edges) {
                if (poll.getWeight() + edge.getWeight() < dist[edge.getEnd()]) {
                    dist[edge.getEnd()] = poll.getWeight() + edge.getWeight();
                    queue.add(new Edge(edge.getEnd(), dist[edge.getEnd()]));
                }
            }
        }
    }

    static class Edge {

        private int end;
        private int weight;

        public Edge(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }

        public int getEnd() {
            return end;
        }

        public int getWeight() {
            return weight;
        }
    }
}
