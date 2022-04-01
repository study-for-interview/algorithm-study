import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());

        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.naturalOrder());
        for (int i = 0; i < N; i++) {
            queue.add(Integer.parseInt(bufferedReader.readLine()));
        }
        int sum = 0;
        if (queue.size() >= 2) {
            while (queue.size() != 1) {
                Integer first = queue.poll();
                Integer second = queue.poll();
                sum += (first + second);
                queue.add(first + second);
            }
        }

        System.out.println(sum);
    }

}
