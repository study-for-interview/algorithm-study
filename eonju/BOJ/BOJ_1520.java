import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class Main {

    private static int[] moveHeight = {-1, 1, 0, 0};
    private static int[] moveWidth = {0, 0, -1, 1};
    private static int[][] map;
    private static int height;
    private static int width;
    private static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = bufferedReader.readLine().split(" ");
        height = Integer.parseInt(input[0]);
        width = Integer.parseInt(input[1]);
        map = new int[height][width];

        for (int i = 0; i < height; i++) {
            input = bufferedReader.readLine().split(" ");
            for (int j = 0; j < width; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }

        answer = 0;
        Location startLocation = new Location(0, 0);

        ArrayList<Location> visited = new ArrayList<>();
        visited.add(startLocation);
        dfs(startLocation, visited);
        System.out.println(answer);
    }

    public static void dfs(Location startLocation, List<Location> visited) {
        if (startLocation.y == height - 1 && startLocation.x == width - 1) {
            answer++;
        }

        for (int i = 0; i < 4; i++) {
            int nextY = startLocation.y + moveHeight[i];
            int nextX = startLocation.x + moveWidth[i];
            Location nextLocation = new Location(nextY, nextX);

            if (nextX < 0 || nextY < 0 || nextY >= height || nextX >= width) {
                continue;
            }

            if (visited.contains(nextLocation)) {
                continue;
            }

            if (map[nextY][nextX] < map[startLocation.y][startLocation.x]) {
                ArrayList<Location> nextVisited = new ArrayList<>();
                nextVisited.addAll(visited);
                nextVisited.add(nextLocation);
                dfs(nextLocation, visited);
            }
        }
    }

    static class Location {

        int y;
        int x;

        public Location(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Location location = (Location) o;
            return y == location.y && x == location.x;
        }

        @Override
        public int hashCode() {
            return Objects.hash(y, x);
        }
    }
}
