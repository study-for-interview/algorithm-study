package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/17406 배열 돌리기4
 */
public class BOJ_17406 {
    static int N, M, K;
    static int[][] map, console;
    static int minRowSum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 초기 배열 정보
        map = new int[N][M];
        // 연산을 담아둘 배열
        console = new int[K][3];
        minRowSum = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());

            console[k] = new int[] { r, c, s };
        }

        // 연산의 순열 구함
        permutationConsole(new boolean[K], new LinkedList<>());

        bw.write(minRowSum + "\n");
        bw.flush();
        bw.close();

    }

    static void permutationConsole(boolean[] visited, LinkedList<Integer> perm) {
        if (perm.size() == K) {
            // 원본 배열 복사하여 돌리기 수행
            int[][] nMap = copyMap();

            // 뽑힌 순열대로 배열 돌리기 수행
            for (Integer idx : perm) {
                int r = console[idx][0];
                int c = console[idx][1];
                int s = console[idx][2];

                calcPoint(r, c, s, nMap);
            }

            // 돌려진 배열로 배열 값 구하기
            minRowSum = Math.min(minRowSum, calcArr(nMap));
            return;
        }

        for (int i = 0; i < K; i++) {
            if (!visited[i]) {
                visited[i] = true;
                perm.add(i);

                permutationConsole(visited, perm);

                visited[i] = false;
                perm.removeLast();
            }
        }
    }

    static void calcPoint(int r, int c, int s, int[][] nMap) {
        for (int i = 0; i < s; i++) {
            // 가장 왼쪽 윗 칸 좌표
            int x1 = r - s + i;
            int y1 = c - s + i;
            // 가장 오른쪽 아랫 칸 좌표
            int x2 = r + s - i;
            int y2 = c + s - i;

            rotate(nMap, x1, y1, x2, y2);
        }
    }

    static void rotate(int[][] nMap, int x1, int y1, int x2, int y2) {
        int temp, pastTemp;

        // 진행 1 : 윗변
        temp = nMap[x1][y2];
        if (y2 - y1 >= 0)
            System.arraycopy(nMap[x1], y1, nMap[x1], y1 + 1, y2 - y1);

        // 진행 2 : 오른쪽변
        pastTemp = temp;
        temp = nMap[x2][y2];

        for (int x = x2; x > x1; x--) {
            if (x - 1 == x1) {
                nMap[x][y2] = pastTemp;
                continue;
            }
            nMap[x][y2] = nMap[x - 1][y2];
        }

        // 진행 3 : 아랫변
        pastTemp = temp;
        temp = nMap[x2][y1];

        for (int y = y1; y < y2; y++) {
            if (y + 1 == y2) {
                nMap[x2][y] = pastTemp;
                continue;
            }

            nMap[x2][y] = nMap[x2][y + 1];
        }

        // 진행 4 : 왼쪽변
        pastTemp = temp;

        for (int x = x1; x < x2; x++) {
            if (x + 1 == x2) {
                nMap[x][y1] = pastTemp;
                continue;
            }

            nMap[x][y1] = nMap[x + 1][y1];
        }
    }

    static int calcArr(int[][] nMap) {
        int[] rowSum = new int[N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                rowSum[i] += nMap[i][j];
            }
        }

        Arrays.sort(rowSum);

        return rowSum[0];
    }

    static int[][] copyMap() {
        int[][] nMap = new int[N][M];

        for (int i = 0; i < N; i++) {
            System.arraycopy(map[i], 0, nMap[i], 0, M);
        }

        return nMap;
    }
}
