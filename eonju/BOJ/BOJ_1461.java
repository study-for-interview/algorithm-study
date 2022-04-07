import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class BOJ_1461 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = bufferedReader.readLine().split(" ");
        int bookQuantity = Integer.parseInt(input[0]);
        int maxHave = Integer.parseInt(input[1]);

        int[] books = Arrays.stream(bufferedReader.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();

        PriorityQueue<Integer> positiveQueue = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> negativeQueue = new PriorityQueue<>(Comparator.reverseOrder());

        for (int book : books) {
            if (book > 0) {
                positiveQueue.add(book);
            } else {
                negativeQueue.add(Math.abs(book));
            }
        }

        int end = 0;
        if (positiveQueue.isEmpty()) {
            end = negativeQueue.peek();
        } else if (negativeQueue.isEmpty()) {
            end = positiveQueue.peek();
        } else {
            end = Math.max(negativeQueue.peek(), positiveQueue.peek());
        }

        int answer = 0;
        while (!positiveQueue.isEmpty()) {
            Integer max = positiveQueue.poll();

            for (int i = 0; i < maxHave - 1; i++) {
                positiveQueue.poll();

                if (positiveQueue.isEmpty()) {
                    break;
                }
            }

            answer += max * 2;
        }

        while (!negativeQueue.isEmpty()) {
            Integer max = negativeQueue.poll();

            for (int i = 0; i < maxHave - 1; i++) {
                negativeQueue.poll();

                if (negativeQueue.isEmpty()) {
                    break;
                }
            }

            answer += max * 2;
        }

        System.out.println(answer - end);
    }
}
