package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/15686 치킨 배달
 */
public class BOJ_15686 {

    static int n, m;
    static Stack<House> selectChicken;
    static int[][] map;
    static List<House> chickens;
    static List<House> houses;
    static int minDist = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        chickens = new ArrayList<>();
        houses = new ArrayList<>();
        selectChicken = new Stack<>();
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int info = Integer.parseInt(st.nextToken());
                map[i][j] = info;
                if (info == 1) {
                    houses.add(new House(i, j));
                } else if (info == 2) {
                    chickens.add(new House(i, j));
                }
            }
        }

        select(0,0);

        System.out.println(minDist);
    }

    static void select(int start, int count) {
        if (count == m) {
            calcDist();
            return;
        }

        for (int i = start; i < chickens.size(); i++) {
            selectChicken.push(chickens.get(i));
            select(i + 1, count + 1);
            selectChicken.pop();
        }
    }

    private static void calcDist() {
        int sum = 0;
        for (House house : houses) {
            int min = Integer.MAX_VALUE;
            for (House chicken : selectChicken) {
                int dist = Math.abs(house.x - chicken.x) + Math.abs(house.y - chicken.y);
                min = Math.min(min, dist);
            }
            sum += min;
            if (sum > minDist) {
                return;
            }
        }

        minDist = sum;
    }

    static class House {

        int x;
        int y;

        public House(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("House{");
            sb.append("x=").append(x);
            sb.append(", y=").append(y);
            sb.append('}');
            return sb.toString();
        }
    }
}
