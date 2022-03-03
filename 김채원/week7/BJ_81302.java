import java.awt.Point;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class P81302거리두기확인 {

    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        Arrays.fill(answer, 1);
        sol(answer, places);
        return answer;
    }

    boolean sol(int[] answer, String[][] places) {
        gg:for (int idx = 0 ; idx < 5 ; idx++) {
            String [] place = places[idx];
            for (int i = 0; i < 5; i++)
                for (int j = 0; j < 5; j++)
                    if (place[i].charAt(j) == 'P') {
                        if(!check(place, i, j)) {
                            answer[idx] = 0;
                            continue gg;
                        }

                    }
        }
        return true;
    }

    boolean check(String[] place, int x, int y) {
        Queue<Point> que = new LinkedList<Point>();
        que.add(new Point(x, y));
        int[] dr = { 0, 0, -1, 1 };
        int[] dc = { 1, -1, 0, 0 };
        boolean visit[][] = new boolean[5][5];
        visit[x][y] = true;
        for (int cy = 0; cy < 2; cy++) {
            for (int size = que.size(); size > 0; size--) {
                Point now = que.poll();
                for (int i = 0; i < 4; i++) {
                    int row = now.x + dr[i];
                    int col = now.y + dc[i];
                    if (row < 0 || col < 0 || row >= 5 || col >= 5 || place[row].charAt(col) == 'X')
                        continue;
                    if (visit[row][col])
                        continue;
                    if(place[row].charAt(col) == 'P')
                        return false;
                    visit[row][col] = true;
                    que.add(new Point(row, col));
                }
            }
        }
        return true;
    }
}