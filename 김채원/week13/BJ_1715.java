import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_1715 {
    static int n;
    static ArrayList<Integer> arr;

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            pq.add(Integer.parseInt(br.readLine()));
        }

        int ans= 0;
        while (!pq.isEmpty()) {
            if (pq.size() >= 2) {
                int a = pq.poll();
                int b = pq.poll();
                pq.add(a + b);
                ans += a + b;
            } else {
                pq.poll();
            }
        }

        System.out.println(ans);
    }
}