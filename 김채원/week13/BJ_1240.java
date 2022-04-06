import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Edge implements Comparable<Edge> {
    public int vex;
    public int cost;

    Edge(int vex, int cost) {
        this.vex = vex;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge ob) {
        return this.cost - ob.cost;
    }
}


public class BJ_1240 {
    static int n, m, now;
    static ArrayList<ArrayList<Edge>> graph;
    static int[][] dp;
    static boolean[] ch;

    static boolean dfs(int start, int end, int sum) {
        if (start == end) {
            System.out.println(sum);
            return true;
        }

        for (Edge ob : graph.get(start)) {
            if (!ch[ob.vex]) {
                ch[ob.vex] = true;
                boolean check = dfs(ob.vex, end, sum + ob.cost);
                ch[ob.vex] = false;
                if (check) {
                    return check;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList<ArrayList<Edge>>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<Edge>());
        }
        ch = new boolean[n + 1];
        dp = new int[n + 1][n + 1];
        now = 0;

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Edge(b, v));
            graph.get(b).add(new Edge(a, v));
        }


        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ch[a] = true;
            dfs(a,b,0);
            ch[a] = false;
        }
    }
}