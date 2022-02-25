package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/13335 트럭
 */
public class BOJ_13335 {

    private static int n, w, l;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s);
        n = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());

        String truckInfo = br.readLine();
        st = new StringTokenizer(truckInfo);
        Queue<Integer> trucks = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            trucks.add(Integer.valueOf(st.nextToken()));
        }

        // 다리 길이만큼 0으로 초기화
        Queue<Integer> bridge = new LinkedList<>();
        for (int i = 0; i < w; i++) {
            bridge.add(0);
        }

        int count = 0;
        int sum = 0;

        while (!bridge.isEmpty()) {

            // sum은 다리가 견디고 있는 현재 트럭의 무게의 합
            sum -= bridge.poll();

            // 트럭이 다 안지나갔으면 true
            if (!trucks.isEmpty()) {

                // 다리의 하중이 견딜 수 있으면 O
                if (sum + trucks.peek() <= l) {
                    int truck = trucks.poll();
                    sum += truck;
                    bridge.offer(truck);

                // 없으면 0
                } else {
                    bridge.offer(0);
                }
            }
            count++;
        }

        System.out.println(count);
    }
}
