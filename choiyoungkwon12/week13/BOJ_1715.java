package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ_1715 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            int number = Integer.parseInt(br.readLine());
            pq.add(number);
        }

        int sum = 0;
        while (pq.size() >= 2) {
            Integer num = pq.poll();
            Integer num2 = pq.poll();
            num += num2;
            sum += num;
            pq.add(num);
        }

        System.out.print(sum);
    }
}
