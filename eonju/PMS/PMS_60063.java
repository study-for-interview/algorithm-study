import java.util.*;

public class Solution {

    public int map[][];
    public int n;
    public int dx[] = {-1, 1, 0, 0};
    public int dy[] = {0, 0, -1, 1};
    public boolean row[][];
    public boolean col[][];
    public int answer;

    public int solution(int[][] board) {
        n = board.length;
        answer = 0;
        row = new boolean[n][n];
        col = new boolean[n][n];
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            map[i] = board[i].clone();
        }

        row[0][0] = true;
        row[0][1] = true;

        start();

        return answer;
    }

    public void start() {
        Queue<Robot> q = new LinkedList<Robot>();
        q.add(new Robot(new Point(0, 0), new Point(0, 1), 0));
        q.add(new Robot(null, null, -1));
        int count = 0;

        while (!q.isEmpty()) {
            Robot now = q.poll();

            if (now.dir == -1) {
                count++;
                if (!q.isEmpty()) {
                    q.add(new Robot(null, null, -1));
                }
                continue;
            }

            if ((now.p1.x == n - 1 && now.p1.y == n - 1) || (now.p2.x == n - 1 && now.p2.y == n - 1)) {
                answer = count;
                break;
            }

            if (now.dir == 0) {
                for (int i = 0; i < 4; i++) {
                    int np1X = now.p1.x + dx[i];
                    int np1Y = now.p1.y + dy[i];
                    int np2X = now.p2.x + dx[i];
                    int np2Y = now.p2.y + dy[i];

                    if (check(np1X, np1Y) && check(np2X, np2Y)) {
                        if (!row[np1X][np1Y] || !row[np2X][np2Y]) {
                            Robot next = new Robot(new Point(np1X, np1Y), new Point(np2X, np2Y), 0);
                            row[np1X][np1Y] = true;
                            row[np2X][np2Y] = true;
                            q.add(next);
                        }
                    }
                }

                for (int i = -1; i <= 1; i += 2) {
                    int np1X = now.p1.x + i;
                    int np1Y = now.p1.y;
                    int np2X = now.p2.x + i;
                    int np2Y = now.p2.y;

                    if (check(np1X, np1Y) && check(np2X, np2Y)) {
                        if (rotate(np1X, np1Y, now.p1.x, now.p1.y) && (!col[np1X][np1Y] || !col[now.p1.x][now.p1.y])) {
                            col[np1X][np1Y] = true;
                            col[now.p1.x][now.p1.y] = true;
                            q.add(new Robot(new Point(np1X, np1Y), new Point(now.p1.x, now.p1.y), 1));
                        }
                        if (rotate(np2X, np2Y, now.p2.x, now.p2.y) && (!col[np2X][np2Y] || !col[now.p2.x][now.p2.y])) {
                            col[np2X][np2Y] = true;
                            col[now.p2.x][now.p2.y] = true;
                            q.add(new Robot(new Point(np2X, np2Y), new Point(now.p2.x, now.p2.y), 1));
                        }
                    }
                }
            } else {
                for (int i = 0; i < 4; i++) {
                    int np1X = now.p1.x + dx[i];
                    int np1Y = now.p1.y + dy[i];
                    int np2X = now.p2.x + dx[i];
                    int np2Y = now.p2.y + dy[i];

                    if (check(np1X, np1Y) && check(np2X, np2Y)) {
                        if (!col[np1X][np1Y] || !col[np2X][np2Y]) {
                            Robot next = new Robot(new Point(np1X, np1Y), new Point(np2X, np2Y), 1);
                            col[np1X][np1Y] = true;
                            col[np2X][np2Y] = true;
                            q.add(next);
                        }
                    }
                }

                for (int i = -1; i <= 1; i += 2) {
                    int np1X = now.p1.x;
                    int np1Y = now.p1.y + i;
                    int np2X = now.p2.x;
                    int np2Y = now.p2.y + i;

                    if (check(np1X, np1Y) && check(np2X, np2Y)) {
                        if (rotate(np1X, np1Y, now.p1.x, now.p1.y) && (!row[np1X][np1Y] || !row[now.p1.x][now.p1.y])) {
                            row[np1X][np1Y] = true;
                            row[now.p1.x][now.p1.y] = true;
                            q.add(new Robot(new Point(np1X, np1Y), new Point(now.p1.x, now.p1.y), 0));
                        }
                        if (rotate(np2X, np2Y, now.p2.x, now.p2.y) && (!row[np2X][np2Y] || !row[now.p2.x][now.p2.y])) {
                            row[np2X][np2Y] = true;
                            row[now.p2.x][now.p2.y] = true;
                            q.add(new Robot(new Point(np2X, np2Y), new Point(now.p2.x, now.p2.y), 0));
                        }
                    }
                }
            }
        }
    }

    public boolean rotate(int x1, int y1, int x2, int y2) {
        if (!check(x1, y1) || !check(x2, y2)) {
            return false;
        }

        return true;
    }

    public boolean check(int x, int y) {
        return x >= 0 && y >= 0 && x < n && y < n && map[x][y] == 0;
    }

    class Robot {

        Point p1, p2;
        int dir;

        Robot(Point p1, Point p2, int dir) {
            this.p1 = p1;
            this.p2 = p2;
            this.dir = dir;
        }
    }

    class Point {

        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
