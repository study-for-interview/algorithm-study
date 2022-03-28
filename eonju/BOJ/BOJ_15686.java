import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int size, quantity;
    static ArrayList<Location> houses;
    static ArrayList<Location> chickenHouses;
    static int answer;
    static boolean[] open;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        size = Integer.parseInt(st.nextToken());
        quantity = Integer.parseInt(st.nextToken());
        houses = new ArrayList<>();
        chickenHouses = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < size; j++) {
                String input = st.nextToken();

                if (input.equals("1")) {
                    houses.add(new Location(i, j));
                    continue;
                }
                if (input.equals("2")) {
                    chickenHouses.add(new Location(i, j));
                }
            }
        }

        answer = Integer.MAX_VALUE;
        open = new boolean[chickenHouses.size()];

        solve(0, 0);
        System.out.println(answer);
    }

    public static void solve(int start, int cnt) {
        if (cnt == quantity) {
            int res = 0;

            for (int i = 0; i < houses.size(); i++) {
                int temp = Integer.MAX_VALUE;

                for (int j = 0; j < chickenHouses.size(); j++) {
                    if (open[j]) {
                        int distance = getDistance(houses.get(i), chickenHouses.get(j));
                        temp = Math.min(temp, distance);
                    }
                }
                res += temp;
            }
            answer = Math.min(answer, res);
            return;
        }

        for (int i = start; i < chickenHouses.size(); i++) {
            open[i] = true;
            solve(i + 1, cnt + 1);
            open[i] = false;
        }
    }

    public static int getDistance(Location chickenHouse, Location house) {
        return Math.abs(chickenHouse.x - house.x) + Math.abs(chickenHouse.y - house.y);
    }

    static class Location {

        int x;
        int y;

        Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
