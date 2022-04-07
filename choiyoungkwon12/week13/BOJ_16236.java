package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/16236
 * 아기 상어
 */
public class BOJ_16236 {

    static int n;
    static int[][] board;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];

        Queue<Node> q = new LinkedList<>();
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 9) {
                    q.add(new Node(i, j, 0));
                    board[i][j] = 0;
                }
            }
        }

        int eat = 0;
        int time = 0;
        int age = 2;

        while (true) {
            List<Node> fish = new LinkedList<>();
            int[][] dist = new int[n][n];

            while (!q.isEmpty()) {
                Node current = q.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = current.x + dx[i];
                    int ny = current.y + dy[i];

                    if (nx >= 0 && ny >= 0 && nx < n && ny < n && dist[nx][ny] == 0 && board[nx][ny] <= age) {
                        dist[nx][ny] = dist[current.x][current.y] + 1;
                        q.add(new Node(nx, ny, dist[nx][ny]));
                        if (1 <= board[nx][ny] && board[nx][ny] <= 6 && board[nx][ny] < age) {
                            fish.add(new Node(nx, ny, dist[nx][ny]));
                        }
                    }
                }
            }

            if (fish.size() == 0) {
                System.out.println(time);
                return;
            }

            Node currentFish = fish.get(0);
            for (int i = 1; i < fish.size(); i++) {
                if (currentFish.dist > fish.get(i).dist) {
                    currentFish = fish.get(i);
                } else if (currentFish.dist == fish.get(i).dist) {
                    if (currentFish.x > fish.get(i).x) {
                        currentFish = fish.get(i);
                    } else if (currentFish.x == fish.get(i).x) {
                        if (currentFish.y > fish.get(i).y) {
                            currentFish = fish.get(i);
                        }
                    }
                }
            }

            time += currentFish.dist;
            eat++;
            board[currentFish.x][currentFish.y] = 0;
            if (eat == age) {
                age++;
                eat = 0;
            }

            q.add(new Node(currentFish.x, currentFish.y, 0));
        }
    }

    static class Node {

        int x;
        int y;
        int dist;

        public Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

}
