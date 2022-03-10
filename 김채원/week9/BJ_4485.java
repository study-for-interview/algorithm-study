import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_4485 {

    // 좌표와 가중치 class
    static class point implements Comparable<point> {

        int row, col, cost;

        public point(int row, int col, int cost) {
            super();
            this.row = row;
            this.col = col;
            this.cost = cost;
        }

        @Override
        public int compareTo(point o) {
            return this.cost - o.cost; // 오름차순 정렬 ( return 값이 양수일때 자리바꿈 )
        }

    }

    static int N; // map의 크기, 최소루피값
    static int[][] map; // 입력 받는 map
    static int[][] dijk; // 최소비용을 저장
    static int[] dy = { 0, 1, -1, 0 }; // 우 하 상 좌
    static int[] dx = { 1, 0, 0, -1 };

    // 범위 검사
    static boolean isValid(int x, int y) {
        if (x < 0 || x >= N || y < 0 || y >= N)
            return false;
        return true;
    }

    public static int dijkstra() {
        PriorityQueue<point> pq = new PriorityQueue<point>();
        dijk[0][0] = map[0][0]; // 초기 값
        pq.offer(new point(0, 0, map[0][0])); // 시작 좌표

        while (!pq.isEmpty()) {
            point p = pq.poll();

            // 사방탐색
            for (int k = 0; k < 4; k++) {
                int nextRow = p.row + dy[k];
                int nextCol = p.col + dx[k];

                // 범위 검사
                if (isValid(nextRow, nextCol)) {
                    if (dijk[nextRow][nextCol] > dijk[p.row][p.col] + map[nextRow][nextCol]) { // 기존의 가중치보다 작은 경우
                        dijk[nextRow][nextCol] = dijk[p.row][p.col] + map[nextRow][nextCol]; // 가중치를 교환
                        pq.offer(new point(nextRow, nextCol, dijk[nextRow][nextCol])); // 큐에 추가
                    }
                }
            }
        }
        return dijk[N - 1][N - 1];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        int cnt = 0; // 반복횟수
        while (true) {

            N = Integer.parseInt(br.readLine());
            if (N == 0)
                break;
            map = new int[N][N];
            dijk = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    dijk[i][j] = Integer.MAX_VALUE;
                }

            } // end of input
            cnt++; // 반복 횟수 증가
            sb.append("Problem " + cnt + ": " + dijkstra() + "\n"); // 출력문
        }
        ; // end of testcase;
        System.out.println(sb); // 출력
        br.close();
    }// end of main

}