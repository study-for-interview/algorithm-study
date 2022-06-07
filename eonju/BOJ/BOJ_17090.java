import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

class Main {

    private static int height;
    private static int width;
    private static int answer;
    private static HashMap<Position, List<Position>> dp;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] input = bufferedReader.readLine().split(" ");

        height = Integer.parseInt(input[0]);
        width = Integer.parseInt(input[1]);

        String[][] map = new String[height][width];
        dp = new HashMap<>();
        ArrayList<Position> positions = new ArrayList<>();

        for (int i = 0; i < height; i++) {
            map[i] = bufferedReader.readLine().split("");
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j].equals("U")) {
                    if (isInBound(i - 1, j)) {
                        Position next = new Position(i - 1, j);
                        dp.putIfAbsent(next, new ArrayList<>());
                        dp.get(next).add(new Position(i, j));
                    } else {
                        positions.add(new Position(i, j));
                    }
                } else if (map[i][j].equals("R")) {
                    if (isInBound(i, j + 1)) {
                        Position next = new Position(i, j + 1);
                        dp.putIfAbsent(next, new ArrayList<>());
                        dp.get(next).add(new Position(i, j));
                    } else {
                        positions.add(new Position(i, j));
                    }

                } else if (map[i][j].equals("L")) {
                    if (isInBound(i, j - 1)) {
                        Position next = new Position(i, j - 1);
                        dp.putIfAbsent(next, new ArrayList<>());
                        dp.get(next).add(new Position(i, j));
                    } else {
                        positions.add(new Position(i, j));
                    }

                } else if (map[i][j].equals("D")) {
                    if (isInBound(i + 1, j)) {
                        Position next = new Position(i + 1, j);
                        dp.putIfAbsent(next, new ArrayList<>());
                        dp.get(next).add(new Position(i, j));
                    } else {
                        positions.add(new Position(i, j));
                    }

                }
            }
        }

        answer = 0;

        for (Position position : positions) {
            answer++;
            countBlock(position);
        }

        System.out.println(answer);
    }

    public static void countBlock(Position position) {
        if (!dp.containsKey(position)) {
            return;
        }

        List<Position> positions = dp.get(position);
        answer += positions.size();

        for (Position next : positions) {
            countBlock(next);
        }
    }

    public static boolean isInBound(int i, int j) {
        return i >= 0 && j >= 0 && i < height && j < width;
    }

    static class Position {

        int positionI;
        int positionJ;

        public Position(int positionI, int positionJ) {
            this.positionI = positionI;
            this.positionJ = positionJ;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Position position = (Position) o;
            return positionI == position.positionI && positionJ == position.positionJ;
        }

        @Override
        public int hashCode() {
            return Objects.hash(positionI, positionJ);
        }
    }
}
