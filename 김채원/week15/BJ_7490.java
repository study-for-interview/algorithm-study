import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    static int n;
    static boolean[] visited;
    static int[] calculation;
    static LinkedList<Integer> val;
    static ArrayList<String> answer;

    public void dfs(int L, int m) {
        val = new LinkedList<>();
        if (L == m) {
            val.add(1);
            for (int i = 2; i <= m; i++) {
                if (calculation[i - 1] == 2) { //뛰어쓰기라면
                    int now = val.remove(val.size() - 1);
                    val.add(now * 10 + i);
                } else {
                    val.add(i);
                }
            }


            int result = val.get(0) ;
            int j = 1;
            for (int i = 1; i < val.size(); i ++) {
                if(calculation[j] == 2) j++;
                if(calculation[j] == 0) result += val.get(i);
                else result -= val.get(i);
                j++;
            }

            StringBuilder s = new StringBuilder();
            s.append(1);
            if (result == 0) {
                for (int i = 2; i <= m; i++) {
                    if(calculation[i-1] == 0) s.append("+");
                    else if (calculation[i-1] == 1) s.append("-");
                    else s.append(" ");
                    s.append(i);
                }
                answer.add(String.valueOf(s));
            }
            return;
        }

        for (int i = 0; i < 3; i++) {
            if (!visited[L]) {
                visited[L] = true;
                calculation[L] = i;
                dfs(L+1, m);
                visited[L] = false;
            }
        }
    }


    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            int m = Integer.parseInt(br.readLine());
            visited = new boolean[m+1];
            calculation = new int[m];
            answer = new ArrayList<>();
            T.dfs(1,m);
            Collections.sort(answer);
            for (int j = 0; j < answer.size(); j++) {
                System.out.println(answer.get(j));
            }
            System.out.println();
        }
    }
}