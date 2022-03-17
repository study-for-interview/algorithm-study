import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

class Main {

    private static TreeMap<Integer, List<String>> tree;
    private static String[] visited;
    private static int K;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(bufferedReader.readLine());

        visited = bufferedReader.readLine().split(" ");

        tree = new TreeMap<>();
        for (int i = 1; i <= K; i++) {
            tree.put(i, new ArrayList<>());
        }

        solve(1, 0, visited.length - 1);

        for (Integer key : tree.keySet()) {
            List<String> numbers = tree.get(key);
            String answer = String.join(" ", numbers);
            System.out.println(answer);
        }

    }

    public static void solve(int level, int startIdx, int endIdx) {
        if (level == K) {
            tree.get(level).add(visited[startIdx]);
            return;
        }

        int midIdx = (startIdx + endIdx) / 2;
        tree.get(level).add(visited[midIdx]);

        solve(level + 1, startIdx, midIdx - 1);
        solve(level + 1, midIdx + 1, endIdx);
    }
}
