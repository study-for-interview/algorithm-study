import java.io.*;
import java.util.*;

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Main {
    static int[] combi;
    static int[][] map;
    static int n, m;
    static ArrayList<Point> chickenHouse;
    static int answer;

    public void Combination(int L, int s) {
        if (L == m) {
            for (int i = 0; i < combi.length; i++) {
                System.out.print(combi[i] + " ");
            }
            System.out.println();
            ArrayList<Point> afterChickenHouse = new ArrayList<>(chickenHouse);
            int j = 0;
            for (int i = 0; i < chickenHouse.size(); i++) {
                afterChickenHouse.add(chickenHouse.get(i));
                System.out.println("J : " + j + "dd : " + chickenHouse.get(i).x + " " +chickenHouse.get(i).y );
                if (j == m) continue;
                if (combi[j] == i) {
                    j++;
                    afterChickenHouse.remove(afterChickenHouse.size()-1);

                }
            }
            System.out.println("after : " + afterChickenHouse.size());
            int n = cityChickenDistance(afterChickenHouse);
//            if (answer > n) {
//                answer = n;
//                for (int i = 0; i < afterChickenHouse.size(); i++) {
//                    System.out.println(afterChickenHouse.get(i).x + " " + afterChickenHouse.get(i).y);
//                }
//            }
            answer = Math.min(answer, cityChickenDistance(afterChickenHouse));
        } else {
            for (int i = s; i < chickenHouse.size(); i++) {
                combi[L] = i;
                Combination(L + 1, i + 1);
            }
        }
    }

    static int cityChickenDistance(ArrayList<Point> afterChickenHouse) {
        int chickenDis = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1) { //집이라면
                    int result = Integer.MAX_VALUE;
                    for (int k = 0; k < afterChickenHouse.size(); k++) {
                        Point point = afterChickenHouse.get(k);
                        result = Math.min(result, distance(i, j, point.x, point.y));
                    }
                    chickenDis += result;
                }
            }
        }
        return chickenDis;
    }

    static int distance(int x, int y, int x2, int y2) {
        return Math.abs(x - x2) + Math.abs(y - y2);
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Main T = new Main();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        combi = new int[m];
        map = new int[n][n];
        answer = Integer.MAX_VALUE;
        chickenHouse = new ArrayList<>();


        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) chickenHouse.add(new Point(i, j));
            }
        }
        System.out.println("chickenHous" + chickenHouse.size());
        T.Combination(0, 0);
        System.out.println(answer);
        bw.flush();
        bw.close();
        br.close();
    }
}
