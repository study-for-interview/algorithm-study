import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Main {

    private static int N;
    private static HashMap<Integer, List<Edge>> nodes;
    private static boolean[] visited;
    private static int maxIdx;
    private static int maxValue;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(bufferedReader.readLine());
        nodes = new HashMap<>();

        for (int i = 1; i <= N; i++) {
            nodes.put(i, new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            String[] input = bufferedReader.readLine().split(" ");

            int nodeA = Integer.parseInt(input[0]);
            int nodeB = Integer.parseInt(input[1]);
            int weight = Integer.parseInt(input[2]);

            nodes.get(nodeA).add(new Edge(nodeB, weight));
            nodes.get(nodeB).add(new Edge(nodeA, weight));
        }

        visited = new boolean[N + 1];
        visited[1] = true;
        maxIdx = 1;
        maxValue = 0;
        dfs(1, 0);

        visited = new boolean[N + 1];
        visited[maxIdx] = true;
        maxValue = 0;
        dfs(maxIdx, 0);

        System.out.println(maxValue);
    }

    public static void dfs(int start, int sum) {
        List<Edge> edges = nodes.get(start);

        if (maxValue < sum) {
            maxValue = sum;
            maxIdx = start;
        }

        for (Edge edge : edges) {
            if (!visited[edge.end]) {
                visited[edge.end] = true;
                dfs(edge.end, sum + edge.weight);
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
