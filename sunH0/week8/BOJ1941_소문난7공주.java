import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
 
public class BOJ1941_소문난7공주 {
 
    static int N = 5, res = 0, selected[];
    static char map[][];
    static boolean visited[], mapVisited[];
    static int dx[] = { -1, 0, 1, 0 };
    static int dy[] = { 0, 1, 0, -1 };
    static Queue<Integer> q;
 
    public static void main(String[] args) throws IOException {
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        map = new char[N][N];
        for (int i = 0; i < N; i++) 
            map[i] = br.readLine().toCharArray();
 
        
        visited = new boolean[N*N];
        selected = new int[7];
        
        comb(0, 0, 0);
        
        System.out.println(res);
    }
 
    public static void comb(int idx, int cnt, int cntS) {

        if (cnt == 7) {
            if (cntS >= 4) {
                if(bfs())
                    res++;
                return
            }
            return;
        }
        
        for (int i = idx; i < N*N; i++) {
            visited[i] = true;
            
            selected[cnt] = i;

            if(map[i/N][i%N] == 'S') comb(i + 1, cnt + 1, cntS + 1);
            else comb(i + 1, cnt + 1, cntS);
            
            visited[i] = false;
        }
    }
    
    public static boolean bfs() {
        int cnt = 1;
        mapVisited = new boolean[N*N];
        q = new LinkedList<>();
        q.add(selected[0]);

        while(!q.isEmpty()) {
            int now = q.poll();
            visited[now] = true;
            
            for (int d = 0; d < 4; d++) {
                int xx = (now/N) + dx[d];
                int yy = (now%N) + dy[d];

                if(xx < 0 || yy < 0 || xx >= N || yy >= N) continue;
                if(visited[xx*N+yy]) continue;
                if(!visited[xx*N+yy]) continue;

                cnt++;
                mapVisited[xx*N+yy] = true;
                q.add(xx*N+yy);
            }
        }
        
        if(cnt == 7) return true;
        else return false;
    }

