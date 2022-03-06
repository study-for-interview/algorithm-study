package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1238 파티 다익스트라
 */
public class BOJ_1238 {

    // N : 학생 수 , M 단방향 도로, X : 목표지점
    static int N, M, X;
    static ArrayList<ArrayList<Town>> towns;
    static ArrayList<ArrayList<Town>> reverseTowns;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        towns = new ArrayList<>();
        reverseTowns = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            towns.add(new ArrayList<>());
            reverseTowns.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            towns.get(start).add(new Town(end, time));
            reverseTowns.get(end).add(new Town(start, time));
        }

        int[] dijkstra = dijkstra(towns);
        int[] reverse = dijkstra(reverseTowns);

        int result = 0;
        for (int i = 1; i <= N; i++) {
            result = Math.max(result, dijkstra[i] + reverse[i]);
        }

        System.out.println(result);
    }

    private static int[] dijkstra(ArrayList<ArrayList<Town>> towns) {
        PriorityQueue<Town> pq = new PriorityQueue<>();
        pq.offer(new Town(X, 0));

        boolean[] check = new boolean[N + 1];
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[X] = 0;

        while (!pq.isEmpty()) {
            Town town = pq.poll();
            int cur = town.end;

            if (!check[cur]) {
                check[cur] = true;

                for (Town t : towns.get(cur)) {
                    if (!check[t.end] && dist[t.end] > (dist[cur] + t.time)) {
                        dist[t.end] = dist[cur] + t.time;
                        pq.add(new Town(t.end, dist[t.end]));
                    }
                }
            }
        }
        return dist;
    }

    static class Town implements Comparable<Town> {

        int end;
        int time;

        public Town(int end, int time) {
            this.end = end;
            this.time = time;
        }

        @Override
        public int compareTo(Town t) {
            return this.time - t.time;
        }
    }
}
