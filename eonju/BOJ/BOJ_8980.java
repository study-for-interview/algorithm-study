import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int cityQuantity = Integer.parseInt(st.nextToken());
        int maxWeight = Integer.parseInt(st.nextToken());
        int boxQuantity = Integer.parseInt(br.readLine());

        Box[] deliveries = new Box[boxQuantity + 1];
        for (int i = 1; i <= boxQuantity; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            deliveries[i] = new Box(start, end, weight);
        }

        Arrays.sort(deliveries, 1, boxQuantity + 1);

        int[] weight = new int[cityQuantity + 1];
        for (int i = 1; i < cityQuantity; i++) {
            weight[i] = maxWeight;
        }

        int ans = 0;
        for (int i = 1; i <= boxQuantity; i++) {
            Box d = deliveries[i];

            int maxBoxNum = Integer.MAX_VALUE;

            for (int j = d.start; j < d.end; j++) {
                maxBoxNum = Math.min(maxBoxNum, weight[j]);
            }

            if (maxBoxNum >= d.weight) {
                for (int j = d.start; j < d.end; j++) {
                    weight[j] -= d.weight;
                }
                ans += d.weight;
            } else {
                for (int j = d.start; j < d.end; j++) {
                    weight[j] -= maxBoxNum;
                }
                ans += maxBoxNum;
            }
        }

        bw.write(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    static class Box implements Comparable<Box> {

        int start;
        int end;
        int weight;

        Box(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Box arg0) {
            if (end == arg0.end) {
                return start - arg0.start;
            }
            return end - arg0.end;
        }
    }

}
