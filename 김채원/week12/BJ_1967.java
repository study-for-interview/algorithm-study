import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Edge{
    public int vex;
    public int cost;
    Edge(int vex, int cost) {
        this.vex = vex;
        this.cost = cost;
    }
}

public class Main {
    static int n, m;
    static int ans;
    static ArrayList<ArrayList<Edge>> graph;
    static boolean[] check;

    static void dfs(int v,int len){
        for (Edge ob : graph.get(v)) {
            if (!check[ob.vex]) {
                check[ob.vex] = true;
                dfs(ob.vex, len + ob.cost);
            }
        }
        ans = Math.max(ans, len);
    }

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        ans = Integer.MIN_VALUE;
        graph = new ArrayList<ArrayList<Edge>>();
        for(int i=0; i<=n; i++){
            graph.add(new ArrayList<Edge>());
        }
        for(int i=0; i<m; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Edge(b, c));
            graph.get(b).add(new Edge(a, c));
        }

        for (int i = 1; i <= n; i++) {
            check = new boolean[n];
            check[i] = true;
            dfs(i, 0);
        }

        //System.out.println(ans);
    }
}