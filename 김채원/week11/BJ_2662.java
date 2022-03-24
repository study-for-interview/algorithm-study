import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ_2662 {

    static int N, M;
    static int[][] info, dp, invest;
    static int[] path;

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            info = new int[N + 1][M + 1];
            invest = new int[N + 1][M + 1];
            path = new int[M + 1];
            for (int i = 1; i <= N; ++i) {
                st = new StringTokenizer(br.readLine());
                st.nextToken();
                for (int j = 1; j <= M; ++j) {
                    int benefit = Integer.parseInt(st.nextToken());
                    info[i][j] = benefit;
                }
            }

            /**
             * dp[i][j] : 1~j번째 기업까지 i원을 사용했을 경우 최대 이익
             * 구하고자 하는 값, dp[N][M] : 1~M번째 기업까지 N원을 사용했을 경우 최대 이익
             */
            dp = new int[N + 1][M + 1];

            solve();
            getPath(N, M);

            bw.write(dp[N][M] + "\n");
            for (int i = 1; i <= M; ++i) {
                bw.write(path[i] + " ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void solve() {
        //j : 기업, i : j기업에 투자할 금액, k : j-1기업까지 투자한 금액
        for (int j = 1; j <= M; ++j) {
            for (int i = 0; i <= N; ++i) {
                for (int k = N - i; k >= 0; --k) {
                    //j기업까지 i+k원을 투자한 이익보다
                    //j-1기업까지 k원을 투자한 이익 + j기업에 i원을 투자한 금액이 더 크다면
                    if (dp[i + k][j] < dp[k][j - 1] + info[i][j]) {
                        dp[i + k][j] = dp[k][j - 1] + info[i][j];
                        invest[i + k][j] = i; //투자 액수 저장(경로를 추적하기 위해)
                    }
                }
            }
        }
    }

    public static void getPath(int n, int m) {
        if (m == 0) return;
        path[m] = invest[n][m];
        getPath(n - path[m], m - 1);
    }
}