class Solution {

    private static int[][] diffMap;

    public static void main(String[] args) {
        int answer1 = solution(new int[][]{{5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}},
            new int[][]{
                {1, 0, 0, 3, 4, 4}, {1, 2, 0, 2, 3, 2}, {2, 1, 0, 3, 1, 2}, {1, 0, 1, 3, 3, 1}});

        System.out.println(answer1);
    }

    public static int solution(int[][] board, int[][] skill) {
        diffMap = new int[board.length][board[0].length];
        makeDiffMap(skill);
        return destroyOrHeal(board);
    }


    public static int destroyOrHeal(int[][] map) {
        int answer = 0;

        for (int i = 0; i < diffMap.length; i++) {
            for (int j = 0; j < diffMap[i].length; j++) {
                map[i][j] = map[i][j] + diffMap[i][j];
                if (map[i][j] > 0) {
                    answer++;
                }
            }
        }

        return answer;
    }

    public static void makeDiffMap(int[][] skill) {
        for (int i = 0; i < skill.length; i++) {
            int type = skill[i][0];
            int startI = skill[i][1];
            int startJ = skill[i][2];
            int endI = skill[i][3];
            int endJ = skill[i][4];
            int degree = skill[i][5];

            if (type == 1) {
                degree = degree * -1;
            }

            for (int j = startI; j <= endI; j++) {
                for (int k = startJ; k <= endJ; k++) {
                    diffMap[j][k] += degree;
                }
            }
        }
    }
}
