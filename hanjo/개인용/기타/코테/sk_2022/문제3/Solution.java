package 코테.sk_2022.문제3;

import java.util.*;

public class Solution {

    public static class Coord {

        public int x;
        public int y;

        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static final long MOD = 10_000_019L;

    public static long solution(int width, int height, int[][] diagonals) {
        // 거리 dp 배열 초기화 (누적합)
        long[][] dist = new long[height + 1][width + 1];
        Arrays.fill(dist[0], 1);
        for (int x = 0; x < height + 1; x++) {
            dist[x][0] = 1;
        }
        for (int x = 1; x < height + 1; x++) {
            for (int y = 1; y < width + 1; y++) {
                dist[x][y] = dist[x - 1][y] + dist[x][y - 1];
                dist[x][y] %= MOD;
            }
        }

        // 대각선 순회
        long count = 0;
        for (int[] diagonal : diagonals) {
            Coord coord1 = new Coord(diagonal[1] - 1, diagonal[0]);
            Coord coord2 = new Coord(diagonal[1], diagonal[0] - 1);

            // 시작점 -> 대각선 정점 1
            long frontCount1 = dist[coord1.x][coord1.y];
            // 대각선 정점 2 -> 끝
            long backCount1 = dist[height - coord2.x][width - coord2.y];

            // 시작점 -> 대각선 정점 2
            long frontCount2 = dist[coord2.x][coord2.y];
            // 대각선 정점 1 -> 끝
            long backCount2 = dist[height - coord1.x][width - coord1.y];

            count += (frontCount1 * backCount1) + (frontCount2 * backCount2);
        }
        return count%MOD;
    }

    public static void main(String[] args) {
        // 12
        System.out.println(solution(2, 2, new int[][]{
            {1, 1}, {2, 2}
        }));
        // 3225685 -> long 범위 초과... 어떻게 해결..?
        System.out.println(solution(51, 37, new int[][]{
            {17, 19}
        }));
        // 2
        System.out.println(solution(1, 1, new int[][]{
            {1, 1}
        }));
        // 18
        System.out.println(solution(3, 3, new int[][]{
            {2, 2}
        }));
        // 45
        System.out.println(solution(5, 3, new int[][]{
            {2, 2}
        }));
    }
}

// 가장 어려웠던 문제. dp로 경로의 수를 구해놓고, 대각선을 순회하면서 모든 경우의 수를 구한다