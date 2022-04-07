package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/8980 택배
 */
public class BOJ_8980 {

    static int n, c, m;
    static int[] truckWeight = new int[2002];
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        m = Integer.parseInt(br.readLine());
        PriorityQueue<Box> pq = new PriorityQueue<>();
        List<Box> boxes = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            boxes.add(new Box(start, end, weight));
        }
        Collections.sort(boxes);

        for (Box box : boxes) {
            int alreadyLoadCount = 0;
            for (int i = box.start; i < box.end; i++) {
                alreadyLoadCount = Math.max(alreadyLoadCount, truckWeight[i]);
            }
            int canLoadCount = Math.min(box.weight, c - alreadyLoadCount);
            answer += canLoadCount;

            for (int i = box.start; i < box.end; i++) {
                truckWeight[i] += canLoadCount;
            }
        }

        System.out.println(answer);
    }

    static class Box implements Comparable<Box> {

        int start;
        int end;
        int weight;

        public Box(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Box o) {
            if (this.end == o.end) {
                return this.start - o.start;
            }
            return this.end - o.end;
        }
    }
}
