import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    private static int size = 0;
    private static int quantity = 0;
    private static List<Location> chickenHouses;
    private static List<Location> houses;
    private static boolean[] visited;
    private static int min;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] input = bufferedReader.readLine().split(" ");

        size = Integer.parseInt(input[0]);
        quantity = Integer.parseInt(input[1]);
        chickenHouses = new ArrayList<>();
        houses = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            input = bufferedReader.readLine().split(" ");

            for (int j = 0; j < size; j++) {
                if (input[j].equals("1")) {
                    houses.add(new Location(i, j));
                    continue;
                }

                if (input[j].equals("2")) {
                    chickenHouses.add(new Location(i, j));
                }
            }
        }

        min = Integer.MAX_VALUE;
        visited = new boolean[chickenHouses.size()];
        int[] dist = new int[houses.size()];
        Arrays.fill(dist, Integer.MAX_VALUE);

        int answer = solve(0, dist);

        System.out.println(answer);
    }

    public static int solve(int depth, int[] dist) {
        if (depth == quantity) {
            int sum = 0;

            for (int i = 0; i < dist.length; i++) {
                sum += dist[i];
            }

            return sum;
        }

        for (int i = 0; i < chickenHouses.size(); i++) {

            Location chickenHouse = chickenHouses.get(i);

            if (!visited[i]) {
                visited[i] = true;

                int[] temp = new int[houses.size()];
                for (int j = 0; j < dist.length; j++) {
                    temp[j] = dist[j];
                }

                for (int j = 0; j < houses.size(); j++) {
                    Location house = houses.get(j);
                    int distance = getDistance(chickenHouse, house);

                    if (temp[j] > distance) {
                        temp[j] = distance;
                    }
                }

                min = Math.min(solve(depth + 1, temp), min);

                visited[i] = false;
            }
        }

        return min;
    }

    public static int getDistance(Location chickenHouse, Location house) {
        return Math.abs(chickenHouse.x - house.x) + Math.abs(chickenHouse.y - house.y);
    }

    static class Location {

        int x;
        int y;

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
