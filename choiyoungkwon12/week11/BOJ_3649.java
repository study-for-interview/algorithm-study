package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://www.acmicpc.net/problem/3649 로봇 프로젝트
 */
public class BOJ_3649 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = null;

        while ((s = br.readLine()) != null) {
            int x = Integer.parseInt(s);
            x *= 10000000;
            int n = Integer.parseInt(br.readLine());

            List<Integer> infos = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                infos.add(Integer.parseInt(br.readLine()));
            }
            int ldx = 0;
            int rdx = n - 1;
            Collections.sort(infos);

            StringBuilder sb = new StringBuilder();

            boolean flag = false;

            while (ldx < rdx) {
                int sum = infos.get(ldx) + infos.get(rdx);
                if (sum == x) {
                    sb.append("yes").append(" ").append(infos.get(ldx)).append(" ").append(infos.get(rdx)).append("\n");
                    flag = true;
                    break;
                }
                if (sum < x) {
                    ldx++;
                }
                if (sum > x) {
                    rdx--;
                }
            }

            if (!flag) {
                System.out.println("danger");
            }else{
                System.out.print(sb);
            }

            s = null;
        }

    }
}
