import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_17135 {
    static int N, M, D;
    static int[][] map;
    static int[][] copyMap;
    static int ans;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        map = new int[N + 1][M + 1];
        copyMap = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                copyMap[i][j] = map[i][j];
            }
        }

        ArrayList<Integer> archer = new ArrayList<>();
        ans = 0;
        comb(1, M, 3, archer);

        bw.write(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    // map을 원래대로 변경.
    public static void init() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                map[i][j] = copyMap[i][j];
            }
        }
    }

    // 거리
    public static int distance(int r1, int r2, int c1, int c2) {
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }

    // 조합
    public static void comb(int start, int n, int r, ArrayList<Integer> archer) {
        if (r == 0) {
            init();
            attack(archer);
            return;
        }

        for (int i = start; i <= n; i++) {
            archer.add(i);
            comb(i + 1, n, r - 1, archer);
            archer.remove(archer.size() - 1);
        }
    }

    // 궁수가 적을 공격하는 함수.
    public static void attack(ArrayList<Integer> archer) {
        int res = 0;

        // 최대 N턴까지 진행할 수 있음.
        for (int n = 1; n <= N; n++) {
            boolean[][] visited = new boolean[N + 1][M + 1];
            for (int k = 0; k < archer.size(); k++) {
                int temp = archer.get(k); // 궁수가 서 있는 x좌표
                int minD = Integer.MAX_VALUE; // 최소 거리
                int minR = Integer.MAX_VALUE; // 최소 거리의 y좌표
                int minC = Integer.MAX_VALUE; // 최소 거리의 x좌표

                // 맵 전체를 탐색해서 최단거리를 찾아내는 것이 목적.
                for (int i = 1; i <= N; i++) {
                    for (int j = 1; j <= M; j++) {
                        if (map[i][j] == 1) { // 적이 있을 경우
                            if (minD >= distance(i, N + 1, j, temp)) {
                                // 현재 구한 최소 거리보다 더 짧은 거리가 발생할 경우
                                // 최단거리와 좌표들을 다시 초기화.
                                if (minD > distance(i, N + 1, j, temp)) {
                                    minD = distance(i, N + 1, j, temp);
                                    minR = i;
                                    minC = j;
                                } else {
                                    // 현재 구한 최소 거리와 지금 구한 최소 거리가 같을 경우,
                                    // 가장 왼쪽 적부터 처지해야하므로 x좌표가 더 작은지 검사해야 함.
                                    if (minC > j) {
                                        minR = i;
                                        minC = j;
                                    }
                                }
                            }
                        }
                    }
                }

                // 위에 과정을 모두 거친 후, 최소 거리가 D보다 작으면,
                // 그 좌표에 visited 처리를 해 준다.
                if (minD <= D) {
                    visited[minR][minC] = true;
                }
            }

            // visited가 true인 좌표만 적을 처지한다.
            // 궁수가 같은 적을 쏠 수도 있기때문에 바로 바로 map[i][j] = 0하면 안 된다.
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= M; j++) {
                    if (visited[i][j]) {
                        map[i][j] = 0;
                        res++;
                    }
                }
            }

            // 성 바로 위 줄을 모두 0으로 초기화.
            for (int i = 1; i <= M; i++) {
                map[N][i] = 0;
            }

            // i번째 줄을 i-1번째 줄로 초기화.
            for (int i = N; i >= 1; i--) {
                for (int j = 1; j <= M; j++) {
                    map[i][j] = map[i - 1][j];
                }
            }
        }

        ans = Math.max(ans, res);
    }

}
