class Solution {

    private static String[][] map;

    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];

        for (int i = 0; i < places.length; i++) {
            map = new String[5][5];

            for (int j = 0; j < places[i].length; j++) {
                map[j] = places[i][j].split("");
            }

            if (search()) {
                answer[i] = 1;
            }
        }

        return answer;
    }

    public static boolean search() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j].equals("P") && !isSafe(i, j)) {
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean isSafe(int nowI, int nowJ) {
        int[] moveHeight = {-1, 1, 0, 0};
        int[] moveWidth = {0, 0, -1, 1};

        for (int i = 0; i < 4; i++) {
            int nextI = nowI + moveHeight[i];
            int nextJ = nowJ + moveWidth[i];

            if (!isInBound(nextI, nextJ)) {
                continue;
            }

            if (map[nextI][nextJ].equals("P")) {
                return false;
            }

            if (map[nextI][nextJ].equals("X")) {
                continue;
            }

            nextI = nowI + moveHeight[i] * 2;
            nextJ = nowJ + moveWidth[i] * 2;
            if (!isInBound(nextI, nextJ)) {
                continue;
            }

            if (map[nextI][nextJ].equals("P")) {
                return false;
            }
        }

        moveHeight = new int[]{-1, -1, 1, 1};
        moveWidth = new int[]{1, -1, 1, -1};

        for (int i = 0; i < 4; i++) {
            int nextI = nowI + moveHeight[i];
            int nextJ = nowJ + moveWidth[i];

            if (!isInBound(nextI, nextJ)) {
                continue;
            }

            if (!map[nextI][nextJ].equals("P")) {
                continue;
            }

            if (i == 0) {
                if (map[nowI - 1][nowJ].equals("O") || map[nowI][nowJ + 1].equals("O")) {
                    return false;
                }
            } else if (i == 1) {
                if (map[nowI - 1][nowJ].equals("O") || map[nowI][nowJ - 1].equals("O")) {
                    return false;
                }
            } else if (i == 2) {
                if (map[nowI + 1][nowJ].equals("O") || map[nowI][nowJ + 1].equals("O")) {
                    return false;
                }
            } else {
                if (map[nowI + 1][nowJ].equals("O") || map[nowI][nowJ - 1].equals("O")) {
                    return false;
                }
            }
        }
        return true;
    }


    public static boolean isInBound(int i, int j) {
        return i >= 0 && j >= 0 && i < 5 && j < 5;
    }
}
