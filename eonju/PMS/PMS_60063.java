import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {

    private static int[][] map;
    private static int[][] dist;
    private static int[] moveHeight = {-1, 1, 0, 0};
    private static int[] moveWidth = {0, 0, -1, 1};

    public static void main(String[] args) {
        System.out.println(
            solution(new int[][]{{0, 0, 0, 1, 1}, {0, 0, 0, 1, 0}, {0, 1, 0, 1, 1}, {1, 1, 0, 0, 1}, {0, 0, 0, 0, 0}}));
    }

    public static int solution(int[][] board) {
        map = board;
        dist = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        bfs();

        return dist[board.length - 1][board[0].length - 1];
    }

    public static void bfs() {
        Queue<int[]> queue = new LinkedList<>();
        dist[0][0] = 0;
        dist[0][1] = 0;
        queue.add(new int[]{0, 0, 0});
        queue.add(new int[]{0, 1, 0});

        while (!queue.isEmpty()) {
            int[] poll1 = queue.poll();
            int poll1Height = poll1[0];
            int poll1Width = poll1[1];

            int[] poll2 = queue.poll();
            int poll2Height = poll2[0];
            int poll2Width = poll2[1];

            int weight = poll1[2];

            for (int i = 0; i < moveHeight.length; i++) {
                int nextHeight1 = poll1Height + moveHeight[i];
                int nextHeight2 = poll2Height + moveHeight[i];
                int nextWidth1 = poll1Width + moveWidth[i];
                int nextWidth2 = poll2Width + moveWidth[i];

                int cnt = 0;
                if (!isInBound(nextHeight1, nextWidth1)) {
                    cnt++;
                }
                if (!isInBound(nextHeight2, nextWidth2)) {
                    cnt++;
                }

                if (cnt == 2) {
                    continue;
                } else {
                    if (!isInBound(nextHeight1, nextWidth1) || map[nextHeight1][nextWidth1] == 1) {
                        weight++;
                        if (nextHeight1 == nextHeight2) { // 가로일 경우

                        } else {

                        }

                    } else if (!isInBound(nextHeight2, nextWidth2) || map[nextHeight2][nextWidth2] == 1) {
                        weight++;
                        if(nextHeight1 == nextHeight2){

                        }
                    }
                }

                dist[nextHeight1][nextWidth1] = weight + 1;
                dist[nextHeight2][nextWidth2] = weight + 1;
                queue.add(new int[]{nextHeight1, nextWidth1, weight + 1});
                queue.add(new int[]{nextHeight2, nextWidth2, weight + 1});
            }
        }

    }

    public static boolean isInBound(int height, int width) {
        return height >= 0 && width >= 0 && height < dist.length && width < dist[0].length;
    }
}
