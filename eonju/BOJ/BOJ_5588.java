import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

class BOJ_5588 {

    private static int M;
    private static int N;
    private static List<Location> map;
    private static List<Location> photo;


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        M = Integer.parseInt(bufferedReader.readLine());
        map = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            String[] location = bufferedReader.readLine().split(" ");
            int x = Integer.parseInt(location[0]);
            int y = Integer.parseInt(location[1]);
            map.add(new Location(x, y));
        }
        map.sort(Comparator.comparingInt(x -> x.x));

        N = Integer.parseInt(bufferedReader.readLine());
        photo = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String[] location = bufferedReader.readLine().split(" ");
            int x = Integer.parseInt(location[0]);
            int y = Integer.parseInt(location[1]);
            photo.add(new Location(x, y));
        }
        photo.sort(Comparator.comparingInt(x -> x.x));

        moveLocation();
    }

    public static void moveLocation() {
        for (int i = 0; i < M; i++) {
            Location mapLocation = map.get(0);
            for (int j = 0; j < N; j++) {
                Location photoLocation = photo.get(j);

                int moveX = photoLocation.x - mapLocation.x;
                int moveY = photoLocation.y - mapLocation.y;

                if (compare(moveX, moveY)) {
                    System.out.println(moveX + " " + moveY);
                    return;
                }
            }
        }
    }

    public static boolean compare(int moveX, int moveY) {
        int correctCnt = 0;

        for (int i = 0; i < M; i++) {
            Location location = map.get(i);
            Location mapNewLocation = new Location(location.x + moveX, location.y + moveY);

            for (int j = 0; j < N; j++) {
                if (photo.get(j).equals(mapNewLocation)) {
                    correctCnt++;
                    break;
                }
            }
        }

        if (correctCnt == M) {
            return true;
        }

        return false;
    }

    static class Location {

        int x;
        int y;

        public Location(int x, int y) {
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
            Location location = (Location) o;
            return x == location.x && y == location.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
