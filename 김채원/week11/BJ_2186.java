import java.io.*;
import java.util.*;


class Main {
    static int[][][] dp;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int ans;

    public int solution(int N, int M, int K, String[] arr, String str, int x, int y, int cnt) {

        if (cnt == str.length() - 1) return dp[x][y][cnt] = 1;
        if (dp[x][y][cnt] != -1) return dp[x][y][cnt];
        dp[x][y][cnt] = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 1; j <= K; j++) {
                int nx = x + dx[i] * j;
                int ny = y + dy[i] * j;
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if (arr[nx].charAt(ny) == str.charAt(cnt+1)) {
                    dp[nx][ny][cnt] += solution(N, M, K, arr, str, nx, ny, cnt + 1);
                }
            }
        }

        return dp[x][y][cnt];
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Main T = new Main();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken()); // 이동거리

        String[] arr = new String[N];

        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine();
        }


        String str = br.readLine();
        ans = 0;
        dp = new int[N][M][str.length()];
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i].charAt(j) == str.charAt(0)) {
                    ans += T.solution(N, M, K, arr, str, i, j, 0);
                }
            }
        }

        System.out.println(ans);
    }
}
