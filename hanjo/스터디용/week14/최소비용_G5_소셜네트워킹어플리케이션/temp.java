package week14.최소비용_G5_소셜네트워킹어플리케이션;

import java.io.*;
import java.util.*;

public class temp {
    static int N, K, M;
    static int[] parent;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = stoi(br.readLine());
        for(int t = 1; t <= tc; t++) {
            N = stoi(br.readLine());
            K = stoi(br.readLine());
            parent = new int[N];
            Arrays.fill(parent, -1);
            StringBuilder sb = new StringBuilder();

            for(int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int a = stoi(st.nextToken());
                int b = stoi(st.nextToken());
                union(a, b);
            }

            M = stoi(br.readLine());
            sb.append(String.format("Scenario %d:%n", t));
            for(int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int u = stoi(st.nextToken());
                int v = stoi(st.nextToken());
                if(find(u) == find(v))
                    sb.append(1).append("\n");
                else
                    sb.append(0).append("\n");
            }
            System.out.println(sb);
        }
    }

    public static int find(int n) {
        if(parent[n] < 0)
            return n;
        return parent[n] = find(parent[n]);
    }

    public static void union(int u, int v) {
        u = find(u);
        v = find(v);
        if(u == v)
            return;
        if(parent[u] < parent[v]) {
            parent[u] += parent[v];
            parent[v] = u;
        } else {
            parent[v] += parent[u];
            parent[u] = v;
        }
    }

    public static int stoi(String s) {
        return Integer.parseInt(s);
    }
}