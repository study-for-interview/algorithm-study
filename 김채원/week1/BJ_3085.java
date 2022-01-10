import java.io.*;

public class BJ_3085{
    public static int answer = 0;
    public static char[][]arr;
    public static int n;

    public static void check() {

        for (int i = 0; i < n; i++) {
            int cnt = 1;
            for (int j = 0; j < n-1; j++) {
                if (arr[i][j] == arr[i][j + 1]) cnt++;
                else cnt = 1;
                answer = Math.max(answer, cnt);
            }
        }

        for (int i = 0; i < n; i++) {
            int cnt = 1;
            for (int j = 0; j < n-1; j++) {
                if (arr[j][i] == arr[j + 1][i]) cnt++;
                else cnt = 1;
                answer = Math.max(answer, cnt);
            }
        }

    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        arr = new char[n][n];
        for (int i = 0; i < n; i++) {
            String str= br.readLine();
            for (int j = 0; j < n; j++) {
                arr[i][j] = str.charAt(j);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n-1; j++) {
                char temp = arr[i][j];
                arr[i][j] = arr[i][j+1];
                arr[i][j+1] = temp;

                check();

                temp = arr[i][j];
                arr[i][j] = arr[i][j+1];
                arr[i][j+1] = temp;

            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n-1; j++) {
                char temp = arr[j][i];
                arr[j][i] = arr[j+1][i];
                arr[j+1][i] = temp;
                check();
                temp = arr[j][i];
                arr[j][i] = arr[j+1][i];
                arr[j+1][i] = temp;
            }
        }


        System.out.println(answer);

        bw.flush();
        bw.close();
        br.close();
    }

}



