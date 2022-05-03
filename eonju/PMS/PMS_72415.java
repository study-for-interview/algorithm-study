import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

class Solution {

    private static Location now;
    private static HashMap<Integer, List<Location>> cards;

    public static void main(String[] args) {
        System.out.println(solution(new int[][]{{1, 0, 0, 3}, {2, 0, 0, 0}, {0, 0, 0, 2}, {3, 0, 1, 0}}, 1, 0));
        System.out.println(solution(new int[][]{{3, 0, 0, 2}, {0, 0, 1, 0}, {0, 1, 0, 0}, {2, 0, 0, 3}}, 0, 1));
    }

    public static int solution(int[][] board, int r, int c) {
        now = new Location(r, c);
        cards = new HashMap<>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != 0) {
                    cards.putIfAbsent(board[i][j], new LinkedList<>());
                    cards.get(board[i][j]).add(new Location(i, j));
                }
            }
        }

        int answer = 0;

        while (!cards.isEmpty()) {
            Location target = findMinDist();

            int number = board[target.height][target.width];
            List<Location> locations = cards.get(number);

            int dist = 0;
            Location tmp = null;

            for (Location location : locations) {
                if (location.height == target.height && location.width == target.width) {
                    if (location.height == now.height || location.width == now.width) {
                        dist += 1;
                    } else {
                        dist += Math.abs(now.height - location.height) + Math.abs(now.width - location.width);
                    }
                } else {
                    if (location.height == target.height || location.width == target.width) {
                        dist += 1;
                        tmp = new Location(location.height, location.width);
                    } else {
                        dist += Math.abs(target.height - location.height) + Math.abs(target.width - location.width);
                        tmp = new Location(location.height, location.width);
                    }
                }
            }
            cards.remove(number);
            answer += (dist + 2);
            now = tmp;
        }

        return answer;
    }

    public static Location findMinDist() {
        int minValue = Integer.MAX_VALUE;
        Location minLocation = null;

        for (Integer number : cards.keySet()) {
            for (Location location : cards.get(number)) {
                if (location.height == now.height || location.width == now.width) {
                    minValue = 1;
                    minLocation = location;
                } else {
                    int dist = Math.abs(now.height - location.height) + Math.abs(now.width - location.width);
                    if (minValue > dist) {
                        minLocation = location;
                        minValue = dist;
                    }
                }
            }
        }

        return minLocation;
    }


    static class Location {

        int height;
        int width;

        public Location(int height, int width) {
            this.height = height;
            this.width = width;
        }
    }
}
