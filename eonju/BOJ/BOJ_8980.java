import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = bufferedReader.readLine().split(" ");

        int cityQuantity = Integer.parseInt(input[0]);
        int maxWeight = Integer.parseInt(input[1]);
        int boxQuantity = Integer.parseInt(bufferedReader.readLine());

        int[] city = new int[cityQuantity + 1];
        PriorityQueue<Box> queue = new PriorityQueue<>(Comparator.comparingInt(x -> x.diff));

        for (int i = 0; i < boxQuantity; i++) {
            input = bufferedReader.readLine().split(" ");
            int start = Integer.parseInt(input[0]);
            int end = Integer.parseInt(input[1]);
            int weight = Integer.parseInt(input[2]);

            queue.add(new Box(start, end, weight));
        }

        while (!queue.isEmpty()) {
            Box poll = queue.poll();

            if (poll.diff == 1) {
                if (poll.weight <= maxWeight) {
                    city[poll.end] = poll.weight;
                } else {
                    city[poll.end] = maxWeight;
                }

                continue;
            }

            int sum = 0;
            for (int i = poll.start + 1; i <= poll.end; i++) {
                sum = sum + city[i];
            }

            if (sum < maxWeight) {
                city[poll.end] = city[poll.end] + (maxWeight - sum);
            }
        }

        int answer = 0;
        for (int i = 2; i < city.length; i++) {
            answer = answer + city[i];
        }

        System.out.println(answer);
    }

    static class Box {

        int start;
        int end;
        int diff;
        int weight;

        public Box(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.diff = end - start;
            this.weight = weight;
        }
    }
}
