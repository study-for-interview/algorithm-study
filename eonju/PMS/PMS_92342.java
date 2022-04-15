import java.util.Arrays;

class Solution {

    private static int[] ryan;
    private static int[] answer;
    private static int[] apeach;
    private static int N;
    private static int maxDiff;

    public static void main(String[] args) {
        int[] answer1 = solution(5, new int[]{2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0});
        int[] answer2 = solution(1, new int[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        int[] answer3 = solution(9, new int[]{0, 0, 1, 2, 0, 1, 1, 1, 1, 1, 1});
        int[] answer4 = solution(10, new int[]{0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3});

        System.out.println(Arrays.toString(Arrays.stream(answer1).toArray()));
        System.out.println(Arrays.toString(Arrays.stream(answer2).toArray()));
        System.out.println(Arrays.toString(Arrays.stream(answer3).toArray()));
        System.out.println(Arrays.toString(Arrays.stream(answer4).toArray()));
    }


    public static int[] solution(int n, int[] info) {
        N = n;
        ryan = new int[11];
        answer = new int[11];
        apeach = info.clone();
        maxDiff = 0;

        dfs(0, 0);

        if (maxDiff == 0) {
            answer = new int[]{-1};
        }

        return answer;
    }

    public static void dfs(int depth, int start) {
        if (depth == N) {
            calcScore(apeach, ryan);
            return;
        }

        for (int i = start; i < 11; i++) {
            if (N - (apeach[i] + 1) >= 0) {
                ryan[i] = apeach[i] + 1;
                dfs(depth + ryan[i], start + 1);
                ryan[i] = 0;
            }
        }
    }

    public static void calcScore(int[] apeach, int[] ryan) {
        int apeachScore = 0;
        int ryanScore = 0;

        for (int i = 0; i < 11; i++) {
            if (apeach[i] != 0 && ryan[i] != 0) {
                if (apeach[i] < ryan[i]) {
                    ryanScore += (10 - i);
                } else {
                    apeachScore += (10 - i);
                }
            }
        }

        if (apeachScore < ryanScore && ryanScore >= maxDiff) {
            maxDiff = ryanScore;
            answer = ryan.clone();
        }
    }
}
