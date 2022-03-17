package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/5588 별자리 찾기
 */
public class BOJ_5588 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;

        List<Point> movePoints = new ArrayList<>();
        //List<Point> points = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            movePoints.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        int m = Integer.parseInt(br.readLine());

        Set<Point> points = new HashSet<>();
        Point[] diff = new Point[m];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            diff[i] = new Point(x - movePoints.get(0).x, y - movePoints.get(0).y);
            points.add(new Point(x, y));
        }

        int cnt = 0;
        Point answer = null;
        for (Point point : diff) {
            for (Point point1 : movePoints) {
                int dx = point1.x + point.x;
                int dy = point1.y + point.y;
                Point point2 = new Point(dx, dy);
                if (points.contains(point2)) {
                    cnt++;
                }
            }
            if (cnt == movePoints.size()) {
                answer = point;
                break;
            } else {
                cnt = 0;
            }

        }
        System.out.println(answer.x + " " + answer.y);

    }

    static class Point {

        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Point point = (Point) o;

            if (x != point.x) {
                return false;
            }
            return y == point.y;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Point{");
            sb.append("x=").append(x);
            sb.append(", y=").append(y);
            sb.append('}');
            return sb.toString();
        }
    }

}
