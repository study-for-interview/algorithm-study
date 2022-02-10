import java.util.Scanner;

public class BJ_14499 {
    static int[] dice = new int[7];
    static int[][] map;
    static int[] dx = { 0, 0, -1, 1 };
    static int[] dy = { 1, -1, 0, 0 };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int x = sc.nextInt();
        int y = sc.nextInt();
        int k = sc.nextInt();

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < k; i++) {
            int dir = sc.nextInt();
            int nx = x + dx[dir - 1];
            int ny = y + dy[dir - 1];

            if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
                changeDice(dir);

                if (map[nx][ny] == 0) {
                    map[nx][ny] = dice[6];
                } else {
                    dice[6] = map[nx][ny];
                    map[nx][ny] = 0;
                }

                x = nx;
                y = ny;
                System.out.println(dice[1]);
            }
        }
    }

    public static void changeDice(int d) {
        int[] temp = dice.clone();
        // 6 밑면, 1 윗면
        // 동쪽은 1, 서쪽은 2, 북쪽은 3, 남쪽은 4
        if (d == 1) {
            dice[1] = temp[4];
            dice[3] = temp[1];
            dice[4] = temp[6];
            dice[6] = temp[3];
        } else if (d == 2) {
            dice[1] = temp[3];
            dice[3] = temp[6];
            dice[4] = temp[1];
            dice[6] = temp[4];
        } else if (d == 3) {
            dice[1] = temp[5];
            dice[2] = temp[1];
            dice[5] = temp[6];
            dice[6] = temp[2];
        } else {
            dice[1] = temp[2];
            dice[2] = temp[6];
            dice[5] = temp[1];
            dice[6] = temp[5];
        }
    }
}