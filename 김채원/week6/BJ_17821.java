import java.io.*;

public class BJ_17821 {

    static int N;
    static int max;
    static int[][] game;
    static int[] order = new int[10];
    static boolean[] check = new boolean[10];

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        game = new int[N + 1][10];

        for (int i = 1; i <= N; i++) {
            String[] sarr = br.readLine().split(" ");
            for (int j = 1; j <= 9; j++)
                game[i][j] = Integer.parseInt(sarr[j - 1]);
        }

        order[4] = 1;

        setOrder(1);

        bw.write(max + "\n");
        bw.flush();

    }

    public static void setOrder(int idx) {

        if (idx > 9) {
            solve();
            return;
        }

        if (idx == 4) {
            setOrder(idx + 1);
            return;
        }

        for (int i = 2; i <= 9; i++) {

            if (!check[i]) {
                check[i] = true;
                order[idx] = i;
                setOrder(idx + 1);
                check[i] = false;
            }
        }
    }

    public static void solve() {

        int score = 0;
        int turn = 1;
        int inning = 1;
        int out = 0;
        int[] ru = new int[4];

        while (inning <= N) {

            if (out == 3) {
                inning++;
                out = 0;
                ru[0] = ru[1] = ru[2] = ru[3] = 0;
                continue;
            }

            int player = order[turn];
            int result = game[inning][player];

            ru[0] = 1;

            switch (result) {
                case 1:
                    score += ru[3];
                    ru[3] = ru[2];
                    ru[2] = ru[1];
                    ru[1] = ru[0];
                    ru[0] = 0;
                    break;
                case 2:
                    score += ru[2] + ru[3];
                    ru[3] = ru[1];
                    ru[2] = ru[0];
                    ru[0] = ru[1] = 0;
                    break;
                case 3:
                    score += ru[1] + ru[2] + ru[3];
                    ru[3] = ru[0];
                    ru[0] = ru[1] = ru[2] = 0;
                    break;
                case 4:
                    score += ru[0] + ru[1] + ru[2] + ru[3];
                    ru[0] = ru[1] = ru[2] = ru[3] = 0;
                    break;
                default:
                    out++;
                    ru[0] = 0;
                    break;
            }

            max = Math.max(max, score);

            if (turn == 9)
                turn = 1;
            else
                turn++;

        }

    }

}