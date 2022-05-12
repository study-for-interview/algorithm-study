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

public class BJ_14630 {

    static int N;
    static int arr[][];
    static String[] s;
    static int len;
    static int[] dis;
    static int start;
    static int end;

    public void dijkstra() {
        PriorityQueue<Edge> pQ = new PriorityQueue<>();
        pQ.offer(new Edge(start, 0));
        dis[start] = 0;

        while (!pQ.isEmpty()) {
            Edge tmp = pQ.poll();
            int now = tmp.vex;
            int nowCost = tmp.cost;
            if (nowCost > dis[now]) continue;
            for (int i = 0; i < N; i++) {
                int next = i;
                int nextCost = arr[now][i];
                if (dis[next] > nowCost + nextCost) {
                    dis[next] = nowCost + nextCost;
                    pQ.offer(new Edge(next, nowCost + nextCost));
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        s = new String[N];

        for (int i = 0; i < N; i++) {
            s[i] = br.readLine();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken()) - 1;
        end = Integer.parseInt(st.nextToken()) - 1;

        len = s[0].length();
        dis = new int[N];
        Arrays.fill(dis, Integer.MAX_VALUE);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int sum = 0;
                for (int k = 0; k < len; k++) {
                    sum += (s[i].charAt(k) - s[j].charAt(k)) * (s[i].charAt(k) - s[j].charAt(k));
                }
                arr[i][j] = sum;
                arr[j][i] = sum;
            }
        }

        T.dijkstra();
        System.out.println(dis[end]);
    }
}