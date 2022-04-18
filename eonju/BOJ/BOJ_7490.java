import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

class Main {

    private static ArrayList<Integer> numbers;
    private static ArrayList<String> answers;
    private static boolean[] visited;
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int testCaseQuantity = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < testCaseQuantity; i++) {
            N = Integer.parseInt(bufferedReader.readLine());
            numbers = new ArrayList<>();
            answers = new ArrayList<>();
            visited = new boolean[N + 1];
            for (int number = 1; number <= N; number++) {
                numbers.add(number);
            }

            visited[1] = true;
            backtracking(2, new StringBuilder().append(1));

            for (int idx = 0; idx < answers.size(); idx++) {
                System.out.println(answers.get(idx));
            }
            System.out.println();
        }
    }

    public static void backtracking(int depth, StringBuilder sb) {
        if (depth == N + 1) {
            if (calculate(sb) == 0) {
                answers.add(String.valueOf(sb));
            }
            return;
        }

        for (int i = depth; i <= N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                backtracking(depth + 1, new StringBuilder(sb).append("+").append(i));
                visited[i] = false;
                visited[i] = true;
                backtracking(depth + 1, new StringBuilder(sb).append(" ").append(i));
                visited[i] = false;
                visited[i] = true;
                backtracking(depth + 1, new StringBuilder(sb).append("-").append(i));
                visited[i] = false;
            }
        }
    }

    public static int calculate(StringBuilder sb) {
        LinkedList<String> queue = new LinkedList<>();

        String[] split = sb.toString().split("");
        for (int i = 0; i < split.length; i++) {
            String target = split[i];

            if (target.equals(" ")) {
                queue.add(String.valueOf(Integer.parseInt(queue.removeLast()) * Integer.parseInt(split[i + 1])));
                i++;
                continue;
            }

            queue.add(target);
        }

        int sum = Integer.parseInt(queue.poll());

        while (!queue.isEmpty()) {
            String poll = queue.poll();

            if (poll.equals("+")) {
                sum += Integer.parseInt(queue.poll());
            } else if (poll.equals("-")) {
                sum -= Integer.parseInt(queue.poll());
            }
        }

        return sum;
    }

}
