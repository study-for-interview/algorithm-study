import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BJ_1941 {

    static int N = 5, res = 0, selected[];
    static char map[][];
    static boolean visited[], adjVisited[];
    static int dx[] = { -1, 0, 1, 0 };
    static int dy[] = { 0, 1, 0, -1 };
    static Queue<Integer> q;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new char[N][N];
        for (int i = 0; i < N; i++)
            map[i] = br.readLine().toCharArray();

        // 이차원 배열을 일차원 배열로 표현
        visited = new boolean[N*N];
        // 선택된 칠공주
        selected = new int[7];

        // 칠공주 조합을 찾으러
        find(0, 0, 0);

        System.out.println(res);
    }

    public static void find(int idx, int cnt, int cntS) {
        // 7명의 여학생으로 구성,
        if (cnt == 7) {
            // '솜'파 학생이 4명 이상이라면
            if (cntS >= 4) {
                // 인접 확인
                if(checkAdj())
                    res++;
                return;
            }
            // '솜'파가 4명 이상이 아니면 그냥 return..
            return;
        }

        for (int i = idx; i < N*N; i++) {
            visited[i] = true;

            selected[cnt] = i;
            // '솜'파일 경우,
            if(map[i/N][i%N] == 'S') find(i + 1, cnt + 1, cntS + 1);
            else find(i + 1, cnt + 1, cntS);

            visited[i] = false;
        }
    }

    public static boolean checkAdj() {
        int cnt = 1;
        adjVisited = new boolean[N*N];
        q = new LinkedList<>();
        // 임의 한 명 위치를 Queue에 넣고
        q.add(selected[0]);
        // 7명이 모두 인접해있는지 확인해보자.
        while(!q.isEmpty()) {
            int now = q.poll();
            adjVisited[now] = true;

            for (int d = 0; d < 4; d++) {
                int xx = (now/N) + dx[d];
                int yy = (now%N) + dy[d];
                // 범위를 벗어나면 pass
                if(xx < 0 || yy < 0 || xx >= N || yy >= N) continue;
                // 이미 확인한 애면 pass
                if(adjVisited[xx*N+yy]) continue;
                // 인접한 앤데 공주가 아니면 pass
                if(!visited[xx*N+yy]) continue;
                // 인접한 애가 같은 공주네?
                cnt++;
                adjVisited[xx*N+yy] = true;
                q.add(xx*N+yy);
            }
        }
        // 임의 한 명 이랑 인접한 공주가 모두 7명이 되면 true
        if(cnt == 7) return true;
        else return false;
    }
}

