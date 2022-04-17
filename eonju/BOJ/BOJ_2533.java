import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());

        HashMap<Integer, List<Integer>> tree = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            tree.put(i, new ArrayList<>());
        }

        int maxSize = 0;
        for (int i = 0; i < N - 1; i++) {
            String[] input = bufferedReader.readLine().split(" ");
            int nodeA = Integer.parseInt(input[0]);
            int nodeB = Integer.parseInt(input[1]);

            tree.get(nodeA).add(nodeB);
            tree.get(nodeB).add(nodeA);

            maxSize = Math.max(maxSize, tree.get(nodeA).size());
            maxSize = Math.max(maxSize, tree.get(nodeB).size());
        }

        boolean[] visited = new boolean[N + 1];
        int answer = 0;
        for (int i = maxSize; i > 0; i--) {
            for (Integer key : tree.keySet()) {
                if (tree.get(key).size() == i) {
                    if (!visited[key]) {
                        visited[key] = true;
                        for (Integer child : tree.get(key)) {
                            visited[child] = true;
                        }
                        answer++;
                    }
                }
            }
        }

        System.out.println(answer);
    }
}
