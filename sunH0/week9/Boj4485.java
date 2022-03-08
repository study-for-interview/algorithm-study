import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.util.Arrays; 
import java.util.PriorityQueue; 
import java.util.StringTokenizer; 

class Boj4485 { 
    static int n; 
    static int[][] arr; 
    static int[][] dist; 
    static int[] dx = {-1, 1, 0, 0}; 
    static int[] dy = {0, 0, -1, 1}; 

    public static void main(String[] args) throws Exception { 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
        StringTokenizer st; 
        StringBuilder sb = new StringBuilder(); 
        int c = 1; 

        while(true) { 
            n = Integer.parseInt(br.readLine()); 
            if(n==0) break; 

            arr = new int[n][n]; 
            dist = new int[n][n]; 

            for(int i=0;i<n;i++) { 
                st = new StringTokenizer(br.readLine()); 

                for(int j=0;j<n;j++) { 
                    arr[i][j] = Integer.parseInt(st.nextToken()); 
                    dist[i][j] = Integer.MAX_VALUE; } 
                } 

                dijkstra(); 
                sb.append("Problem ").append(c).append(": ").append(dist[n-1][n-1]).append("\n"); 
                c ++ ; 
            }
            System.out.println(sb); 
        } 

        public static void dijkstra() { 
            PriorityQueue<Node> q = new PriorityQueue<>(); 
            q.add(new Node(0, 0, arr[0][0])); 

            dist[0][0] = arr[0][0]; 

            while(!q.isEmpty()) { 
                Node now = q.poll(); 
                int x = now.x; 
                int y= now.y; 

                for(int i=0;i<4;i++) { 
                    int nx = x+dx[i]; 
                    int ny = y+dy[i]; 

                    if(0>nx||0>ny||n<=nx||n<=ny) continue; 

                    if(dist[x][y]+arr[nx][ny]<dist[nx][ny]) { 
                        dist[nx][ny] = dist[x][y]+arr[nx][ny]; 
                        q.add(new Node(nx, ny, dist[nx][ny])); 
                    }
                } 
            } 
        } 
    } 

class Node implements Comparable<Node>{ 
    int x, y, cost; 

    public Node(int x, int y, int cost) { 
        this.x = x; 
        this.y = y; 
        this.cost = cost; 
    } 

    @Override 
    public int compareTo(Node o) { 
            return this.cost-o.cost; 
    } 
}


