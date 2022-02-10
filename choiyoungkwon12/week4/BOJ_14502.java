package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/14502 연구소
 */
public class BOJ_14502 {

    private static int N, M, MAX; // 가로 세로 최대값
    private static int[][] map; // 입력 받을 맵
    private static int[][] wall; // 벽을 놓을 맵

    private static int[] dx = {0, 0, -1, 1};
    private static int[] dy = {-1, 1, 0, 0};
    private static ArrayList<VirusPoint> virusPoints;

    public static boolean checkedRange(int row, int col) {
        return row >= 0 && row < N && col >= 0 && col < M;
    }

    public static int[][] copy(int[][] arr) {
        int[][] copy = new int[N][M];

        for (int i = 0; i < N; i++) {
            if (M >= 0) {
                System.arraycopy(arr[i], 0, copy[i], 0, M);
            }
        }
        return copy;
    }

    public static void makeWall(int depth) {
        if (depth == 3) {
            spreadVirus();
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (wall[i][j] == 0) {
                    wall[i][j] = 1;
                    makeWall(depth + 1);
                    wall[i][j] = 0;
                }
            }
        }
    }

    public static void spreadVirus(){

        int[][] copyWall = copy(wall); // 바이러스를 확장 시킬 복사 맵

        // virus를 담는 과정
        Queue<VirusPoint> vq = new LinkedList<>(); // virus를 담는 큐
        for (int i = 0; i < virusPoints.size(); i++) {
            vq.offer(new VirusPoint(virusPoints.get(i).row, virusPoints.get(i).col));
        }

        // virus 전염 시작
        while (!vq.isEmpty()) {
            VirusPoint virusPoint = vq.poll();
            int row = virusPoint.row;
            int col = virusPoint.col;

            for (int k = 0; k < 4; k++) {
                int nextRow = row + dy[k];
                int nextCol = col + dx[k];

                // 범위 && 빈칸인 경우
                if (checkedRange(nextRow, nextCol) && copyWall[nextRow][nextCol] == 0) {
                    copyWall[nextRow][nextCol] = 2;
                    vq.offer(new VirusPoint(nextRow, nextCol));
                }

            }
        } // end of spread

        int safetyZone = countSafe(copyWall);
        MAX = Math.max(MAX, safetyZone);
    }

    // 안전구역 크기
    public static int countSafe(int[][] copyWall) {
        int sc = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copyWall[i][j] == 0) {
                    sc++;
                }
            }
        }
        return sc;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        virusPoints = new ArrayList<>();
        map = new int[N][M];
        MAX = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                // 입력을 받으면서 바이러스 초기값 탐색
                if (map[i][j] == 2) {
                    virusPoints.add(new VirusPoint(i, j));
                }
            }
        } // end of input

        // 3개의 벽을 세우기 위한 copy map
        wall = copy(map);

        // 벽 세우기 시작
        makeWall(0);

        System.out.println(MAX);
    }
}

class VirusPoint {

    int row;
    int col;

    public VirusPoint(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
