import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import static java.util.Arrays.*;

public class BJ_8979{
    public int solution(int n, int k, int[][] arr) {
        int answer = 1;
        for (int i = 0; i < n; i++) {
            if( i == k-1) continue;;
            if (arr[i][0] > arr[k - 1][0]) {
                answer++;
                continue;
            }
            if (arr[i][0] == arr[k - 1][0]) {
                if (arr[i][1] > arr[k - 1][1]) {
                    answer++;
                } else if (arr[i][1] == arr[k - 1][1]) {
                    if(arr[i][2] > arr[k - 1][2]) answer++;
                }
            }
        }
        return answer;

    }


    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][3];
        for (int i = 0; i < n; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st2.nextToken())-1;
            for (int j = 0; j < 3; j++) {
                arr[x][j] = Integer.parseInt(st2.nextToken());
            }
        }

        System.out.println(T.solution(n,k,arr));

        bw.flush();
        bw.close();
        br.close();
    }

}



