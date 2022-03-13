import java.io.*;
import java.util.*;

class Point {
    int a;
    int b;

    public Point(int a, int b) {
        this.a = a;
        this.b = b;
    }
}

class BJ_5588 {
    static int n, m;
    static Point[] constellation, copyConstellation;
    static Point[] star;
    static int[] answer;

    public void solution() {
        Point point = constellation[0];
        for (int i = 0; i < m; i++) {
            Point point1 = star[i];
            int diffX = point1.a - point.a;
            int diffY = point1.b - point.b;
            for (int j = 0; j < n; j++) {
                Point moveConstellation = constellation[j];
                copyConstellation[j] = new Point(moveConstellation.a + diffX, moveConstellation.b + diffY);
            }

            int cnt = 0;
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    if (copyConstellation[j].a == star[k].a && copyConstellation[j].b == star[k].b) {
                        cnt++;
                        break;
                    }
                }
            }

            if (cnt == n) {
                answer[0] = diffX;
                answer[1] = diffY;
                break;
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BJ_5588 T = new BJ_5588();
        n = Integer.parseInt(br.readLine());
        constellation = new Point[n];
        copyConstellation = new Point[n];
        answer = new int[2];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            constellation[i] = new Point(x, y);
        }

        m = Integer.parseInt(br.readLine());
        star = new Point[m];
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            star[i] = new Point(x, y);
        }

        T.solution();
        System.out.println(answer[0] + " " + answer[1]);
    }
}