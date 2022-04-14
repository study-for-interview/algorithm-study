package UnionFind;

import java.util.*;
import java.io.*;

public class Boj7511 {

    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int d = 0; d < t; d++) {
            sb.append("Scenario ").append(d + 1).append(":").append('\n');
            int n = Integer.parseInt(br.readLine()); 
            int k = Integer.parseInt(br.readLine()); 
            StringTokenizer st;
            parents = new int[n + 1];

            for (int i = 0; i <= n; i++) {
                parents[i] = i;
            }

            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int user1 = Integer.parseInt(st.nextToken());
                int user2 = Integer.parseInt(st.nextToken());
                union(user1, user2);
            }

            int m = Integer.parseInt(br.readLine());
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                sb.append(find(a) == find(b) ? 1 : 0).append('\n');
            }
            
            if (d < t - 1)
                sb.append('\n');
        }
        System.out.print(sb);
    }

    static int find(int x) {
        if (parents[x] == x) {
            return x;
        }
        return parents[x] = find(parents[x]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) return;
        parents[b] = a;
    }
}


    


