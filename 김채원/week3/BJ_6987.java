import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_6987 {

    static int[] g1 = {0,0,0,0,0,1,1,1,1,2,2,2,3,3,4};
    static int[] g2 = {1,2,3,4,5,2,3,4,5,3,4,5,4,5,5};
    static int[] win = new int[6];
    static int[] draw = new int[6];
    static int[] lose = new int[6];
    static boolean ok;
    static StringBuffer sb = new StringBuffer();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int t = 0; t < 4; t++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int sw = 0;
            int sd = 0;
            int sl = 0;
            ok = false;

            for (int i = 0; i < 6; i++) {
                sw += win[i] = Integer.parseInt(st.nextToken());
                sd += draw[i] = Integer.parseInt(st.nextToken());
                sl += lose[i] = Integer.parseInt(st.nextToken());
            }
            // 총 승/무/패의 합은 30이 되어야함.
            if(sw+ sd + sl != 30) {
                ok = false;
            }
            else {
                solve(0);
            }
            sb.append((ok ? 1 : 0) +" ");
        }
        System.out.println(sb.toString());
    }

    static void solve(int game) {
        if(ok) return ;

        // 마지막 게임까지 왔다면, 가능한경우
        if(game == 15) {
            ok = true;
            return ;
        }

        int t1 = g1[game];    // team 1
        int t2 = g2[game];    // team 2

        // team 1의 승리가 가능하다면
        if(win[t1] > 0 && lose [t2] >0) {
            win[t1]--;
            lose[t2]--;
            solve(game+1);
            win[t1]++;
            lose[t2]++;
        }
        // team 2의 승리가 가능하다면
        if(lose[t1] > 0 && win [t2] >0) {
            lose[t1]--;
            win[t2]--;
            solve(game+1);
            lose[t1]++;
            win[t2]++;
        }
        // team 1,2 가 무승부가 가능하다면
        if(draw[t1] > 0 && draw[t2] >0) {
            draw[t1]--;
            draw[t2]--;
            solve(game+1);
            draw[t1]++;
            draw[t2]++;
        }
    }
}
