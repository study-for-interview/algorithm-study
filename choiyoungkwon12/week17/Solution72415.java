package Programmers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://programmers.co.kr/learn/courses/30/lessons/72415 카드 짝 맞추기
 */
public class Solution72415 {

    static List<String> orders;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) {
        Solution72415 s = new Solution72415();
        int solution = s.solution(new int[][]{{1, 0, 0, 3}, {2, 0, 0, 0}, {0, 0, 0, 2}, {3, 0, 1, 0}}, 1, 0);
        System.out.println(solution);
    }

    public int solution(int[][] board, int r, int c) {
        int cardNum = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] != 0) {
                    cardNum++;
                }
            }
        }

        cardNum /= 2;

        int[] card = new int[cardNum];
        for (int i = 0; i < cardNum; i++) {
            card[i] = i + 1;
        }

        orders = new ArrayList<>();
        permutation("", 0, card);

        int min = Integer.MAX_VALUE;
        for (String comb : orders) {
            String[] order = comb.split("");

            int totalMove = 0;
            int[] pos = new int[2];
            pos[0] = r;
            pos[1] = c;

            int[][] copyBoard = new int[4][4];
            for (int i = 0; i < 4; i++) {
                System.arraycopy(board[i], 0, copyBoard[i], 0, 4);
            }
            for (String target : order) {
                int cardNumber = Integer.parseInt(target);

                totalMove += cardSearch(pos, cardNumber, copyBoard);
                copyBoard[pos[0]][pos[1]] = 0;

                totalMove += 1;

                totalMove += cardSearch(pos, cardNumber, copyBoard);
                copyBoard[pos[0]][pos[1]] = 0;

                totalMove += 1;
            }

            min = Math.min(min, totalMove);
        }

        return min;
    }

    private int cardSearch(int[] pos, int targetCard, int[][] copyBoard) {
        Queue<Pair> q = new LinkedList<>();
        boolean[][] check = new boolean[4][4];
        int x = pos[0];
        int y = pos[1];

        check[x][y] = true;
        q.add(new Pair(x, y, 0));
        while (!q.isEmpty()) {
            Pair next = q.poll();
            int px = next.x;
            int py = next.y;
            int move = next.move;

            if (copyBoard[px][py] == targetCard) {
                // System.out.println("[" + targetCard + "] find! " + next.x + "," + next.y + ":" + move);
                pos[0] = next.x;
                pos[1] = next.y;
                return move;
            }

            for (int i = 0; i < 4; i++) {
                int nx = px + dx[i];
                int ny = py + dy[i];

                if (nx < 0 || ny < 0 || nx > 3 || ny > 3) {
                    continue;
                }
                if (check[nx][ny]) {
                    continue;
                }
                check[nx][ny] = true;
                q.add(new Pair(nx, ny, move + 1));
            }

            for (int i = 0; i < 4; i++) {
                Pair res = checkRoute(px, py, i, copyBoard);
                int nx = res.x;
                int ny = res.y;

                if (nx == x && ny == y) {
                    continue;
                }
                if (check[nx][ny]) {
                    continue;
                }

                check[nx][ny] = true;
                q.add(new Pair(nx, ny, move + 1));
            }
        }

        return 0;
    }

    private Pair checkRoute(int x, int y, int direction, int[][] copyBoard) {
        x += dx[direction];
        y += dy[direction];

        while (x >= 0 && x < 4 && y >= 0 && y < 4) {
            if (copyBoard[x][y] != 0) {
                return new Pair(x, y, 0);
            }

            x += dx[direction];
            y += dy[direction];
        }

        return new Pair(x - dx[direction], y - dy[direction], 0);
    }

    private void permutation(String comb, int depth, int[] card) {
        if (card.length == depth) {
            orders.add(comb);
            return;
        }
        for (int num : card) {
            if (!comb.contains("" + num)) {
                permutation(comb + num, depth + 1, card);
            }
        }
    }

    static class Pair {

        int x;
        int y;
        int move;

        public Pair(int x, int y, int move) {
            this.x = x;
            this.y = y;
            this.move = move;
        }
    }
}
