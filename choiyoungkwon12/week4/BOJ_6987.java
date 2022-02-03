package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/6987 월드컵 백트래킹
 */
public class BOJ_6987 {

    private static int win[], lose[], draw[], t1[], t2[];
    private static HashMap<String, String> map;
    private static boolean avail;

    static void dfs(int idx) {
        if (avail) {
            return;
        }
        if (idx == 15) {
            avail = true;
            return;
        }
        int a = t1[idx];
        int b = t2[idx];
        //a가 이기는 경우
        if (win[a] > 0 && lose[b] > 0) {
            win[a]--;
            lose[b]--;
            dfs(idx + 1);
            win[a]++;
            lose[b]++;
        }
        //a와 b가 비기는 경우
        if (draw[a] > 0 && draw[b] > 0) {
            draw[a]--;
            draw[b]--;
            dfs(idx + 1);
            draw[a]++;
            draw[b]++;
        }
        //a가 지는 경우
        if (lose[a] > 0 && win[b] > 0) {
            lose[a]--;
            win[b]--;
            dfs(idx + 1);
            lose[a]++;
            win[b]++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        int cnt = 0;

        t1 = new int[15];        //경기하는 2팀
        t2 = new int[15];
        for (int i = 0; i < 5; i++) {
            for (int j = i + 1; j < 6; j++) {
                t1[cnt] = i;
                t2[cnt++] = j;
            }
        }

        for (int i = 0; i < 4; i++) {
            String s = br.readLine();
            StringTokenizer st = new StringTokenizer(s);
            win = new int[6];
            lose = new int[6];
            draw = new int[6];
            avail = false;
            int w = 0, l = 0, d = 0;
            for (int j = 0; j < 6; j++) {
                w += win[j] = Integer.parseInt(st.nextToken());
                d += draw[j] = Integer.parseInt(st.nextToken());
                l += lose[j] = Integer.parseInt(st.nextToken());
            }
            if (w + d + l != 30) {
                avail = false;
            } else {
                dfs(0);
            }

            answer = avail ? answer.append(1).append(" ") : answer.append(0).append(" ");
        }

        System.out.println(answer);

    }
}
