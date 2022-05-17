import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class Main {

    private static HashMap<Integer, List<Node>> graph;
    private static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());

        graph = new HashMap<>();

        String[] types = new String[n + 1];
        for (int i = 1; i <= n; i++) {
            String input = bufferedReader.readLine();
            types[i] = input;
            graph.put(i, new ArrayList<>());
        }

        String[] inputs = bufferedReader.readLine().split(" ");
        int start = Integer.parseInt(inputs[0]);
        int end = Integer.parseInt(inputs[1]);

        makeGraph(types);

        dist = new int[n + 1];
        dijkstra(start);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < types[start].length(); i++) {
            sb.append("0");
        }
        int weight = getWeight(types[start], sb.toString());

        System.out.println(Math.min(dist[end], weight);
    }

    public static void dijkstra(int start) {
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparing(x -> x.weight));
        queue.add(new Node(start, 0));

        while (!queue.isEmpty()) {
            Node poll = queue.poll();

            if (poll.weight > dist[poll.end]) {
                continue;
            }

            for (Node node : graph.get(poll.end)) {
                if (dist[node.end] > poll.weight + node.weight) {
                    dist[node.end] = poll.weight + node.weight;
                    queue.add(new Node(node.end, dist[node.end]));
                }
            }
        }
    }

    public static void makeGraph(String[] types) {
        for (int i = 1; i < types.length - 1; i++) {
            for (int j = i + 1; j < types.length; j++) {
                int weight = getWeight(types[i], types[j]);
                graph.get(i).add(new Node(j, weight));
                graph.get(j).add(new Node(i, weight));
            }
        }
    }

    public static int getWeight(String numberA, String numberB) {
        String[] splitA = numberA.split("");
        String[] splitB = numberB.split("");

        int weight = 0;
        for (int i = 0; i < splitA.length; i++) {
            weight += Math.pow(Math.abs(Integer.parseInt(splitA[i]) - Integer.parseInt(splitB[i])), 2);
        }

        return weight;
    }

    static class Node {

        int end;
        int weight;

        public Node(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }
    }
}
