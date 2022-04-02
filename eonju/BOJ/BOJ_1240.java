import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class BOJ_1240 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = bufferedReader.readLine().split(" ");
        int nodeQuantity = Integer.parseInt(input[0]);
        int targetQuantity = Integer.parseInt(input[1]);

        HashMap<Integer, List<Edge>> graph = new HashMap<Integer, List<Edge>>();
        for (int i = 1; i <= nodeQuantity; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int i = 0; i < nodeQuantity - 1; i++) {
            input = bufferedReader.readLine().split(" ");
            int nodeA = Integer.parseInt(input[0]);
            int nodeB = Integer.parseInt(input[1]);
            int weight = Integer.parseInt(input[2]);

            graph.get(nodeA).add(new Edge(nodeB, weight));
            graph.get(nodeB).add(new Edge(nodeA, weight));
        }

        for (int i = 0; i < targetQuantity; i++) {
            input = bufferedReader.readLine().split(" ");
            int nodeA = Integer.parseInt(input[0]);
            int nodeB = Integer.parseInt(input[1]);

            System.out.println(bfs(nodeA, nodeB, graph));
        }
    }

    public static int bfs(int startNode, int targetNode, HashMap<Integer, List<Edge>> graph) {
        Queue<Edge> queue = new LinkedList<>();
        boolean[] visited = new boolean[graph.size() + 1];
        queue.add(new Edge(startNode, 0));
        visited[startNode] = true;

        while (!queue.isEmpty()) {
            Edge poll = queue.poll();

            for (Edge edge : graph.get(poll.end)) {
                if (visited[edge.end]) {
                    continue;
                }

                if (edge.end == targetNode) {
                    return poll.weight + edge.weight;
                }

                queue.add(new Edge(edge.end, edge.weight + poll.weight));
                visited[edge.end] = true;
            }

        }

        return 0;
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
