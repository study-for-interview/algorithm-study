import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

class Main {

    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int computerQuantity = Integer.parseInt(bufferedReader.readLine());
        int lineQuantity = Integer.parseInt(bufferedReader.readLine());

        parent = new int[computerQuantity + 1];
        for (int i = 1; i <= computerQuantity; i++) {
            parent[i] = i;
        }

        PriorityQueue<Edge> queue = new PriorityQueue<>(Comparator.comparingInt(Edge::getWeight));
        for (int i = 0; i < lineQuantity; i++) {
            String[] input = bufferedReader.readLine().split(" ");
            int targetA = Integer.parseInt(input[0]);
            int targetB = Integer.parseInt(input[1]);
            int weight = Integer.parseInt(input[2]);

            queue.add(new Edge(Math.min(targetA, targetB), Math.max(targetA, targetB), weight));
        }

        int answer = 0;

        while (!queue.isEmpty()) {
            Edge poll = queue.poll();
            int a = poll.getStart();
            int b = poll.getEnd();
            int weight = poll.getWeight();

            int parentA = find(a);
            int parentB = find(b);

            if (parentA != parentB) {
                union(a, b);
                answer += weight;
            }
        }

        System.out.println(answer);
    }

    public static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA != rootB) {
            parent[rootB] = a;
        }
    }

    public static int find(int a) {
        if (parent[a] == a) {
            return a;
        }

        return parent[a] = find(parent[a]);
    }

    static class Edge {

        private int start;
        private int end;
        private int weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        public int getWeight() {
            return weight;
        }
    }
}
