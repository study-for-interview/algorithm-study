package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/7511
 * 소셜 네트워킹 어플리케이션
 */
public class BOJ_7511 {

    static int n, m, k;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 1; i <= tc; i++) {
            n = Integer.parseInt(br.readLine());
            k = Integer.parseInt(br.readLine());
            parent = new int[n];
            Arrays.fill(parent, -1);
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < k; j++) {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                union(a, b);
            }

            m = Integer.parseInt(br.readLine());
            sb.append(String.format("Scenario %d:%n", i));

            for (int j = 0; j < m; j++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                if (find(u) == find(v)) {
                    sb.append(1).append("\n");
                } else {
                    sb.append(0).append("\n");
                }
            }
            System.out.println(sb);
        }
    }

    private static void union(int u, int v) {
        u = find(u);
        v = find(v);
        if (u == v) {
            return;
        }
        if (parent[u] < parent[v]) {
            parent[u] += parent[v];
            parent[v] = u;
        } else {
            parent[v] += parent[u];
            parent[u] = v;
        }
    }

    private static int find(int n) {
        if (parent[n] < 0) {
            return n;
        }
        return parent[n] = find(parent[n]);
    }
}
