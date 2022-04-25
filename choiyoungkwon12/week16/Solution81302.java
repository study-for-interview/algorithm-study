package Programmers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://programmers.co.kr/learn/courses/30/lessons/81302 거리두기 확인하기
 */
public class Solution81302 {

    public static void main(String[] args) {
        Solution81302 s = new Solution81302();
        int[] solution = s.solution(new String[][]{{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"}, {
            "POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"}, {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"}, {
            "OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"}, {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}});
        System.out.println(Arrays.toString(solution));
    }

    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        for (int i = 0; i < places.length; i++) {
            String[] p = places[i];
            boolean checkDistance = true;
            for (int r = 0; r < places[i].length && checkDistance; r++) {
                for (int c = 0; c < places[i].length && checkDistance; c++) {
                    if (p[r].charAt(c) == 'P') {
                        if (!bfs(r, c, p)) {
                            checkDistance = false;
                        }
                    }
                }
            }
            answer[i] = checkDistance ? 1 : 0;
        }
        return answer;
    }

    private boolean bfs(int r, int c, String[] p) {
        int dr[] = {-1, 1, 0, 0};
        int dc[] = {0, 0, 1, -1};

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{r, c});

        while (!queue.isEmpty()) {
            int[] position = queue.poll();

            for (int i = 0; i < dr.length; i++) {
                int nr = position[0] + dr[i];
                int nc = position[1] + dc[i];

                if (nr < 0 || nc < 0 || nr >= 5 || nc >= 5 || (nr == r && nc == c)) {
                    continue;
                }

                int d = Math.abs(nr - r) + Math.abs(nc - c);
                if (p[nr].charAt(nc) == 'P' && d <= 2) {
                    return false;
                } else if (p[nr].charAt(nc) == 'O' && d < 2) {
                    queue.offer(new int[]{nr, nc});
                }
            }
        }
        return true;
    }
}
