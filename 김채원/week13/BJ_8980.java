import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Delivery implements Comparable<Delivery> {
    int from;
    int to;
    int size;
    boolean completed;


    public Delivery(int from, int to, int size, boolean completed) {
        this.from = from;
        this.to = to;
        this.size = size;
        this.completed = completed;
    }

    @Override
    public int compareTo(Delivery o) {
        if (this.to == o.to) return this.from - o.from;
        else return this.to - o.to;
    }
}


public class BJ_8980 {
    static int n, m, c, ans;
    static ArrayList<Delivery> deliveries;

    public void solution() {
        Collections.sort(deliveries);
        int[] arr = new int[n + 1];
        boolean ch = false;
        for (int i = 0; i < deliveries.size(); i++) {
            Delivery delivery = deliveries.get(i);

            int max = Integer.MIN_VALUE;
            for (int k = delivery.from; k < delivery.to; k++) {
                max = Math.max(max, arr[k]);
            }
            int freeSpace = c - max;
            int nextc = Math.min(freeSpace, delivery.size);
            for (int j = delivery.from; j < delivery.to; j++) {
                arr[j] += nextc;
            }
            ans += nextc;
        }

    }

    public static void main(String[] args) throws IOException {
        BJ_8980 T = new BJ_8980();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        ans = 0;

        deliveries = new ArrayList<>();

        m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());

            deliveries.add(new Delivery(from, to, size, false));
        }

        T.solution();
        System.out.println(ans);

    }
}