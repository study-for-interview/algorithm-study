import java.util.ArrayList;
import java.util.Collections;

class Solution {

    static ArrayList<int[]> answer = new ArrayList<>();
    static int[] ryan;
    static int[] apeach;
    static int N;
    static int max = Integer.MIN_VALUE;

    public static void DFS(int L, int s) {
        if (L == N) {
            int apeachScore = 0;
            int ryanScore = 0;

            for (int i = 0; i <= 10; i++) {
                if (apeach[i] == 0 && ryan[i] == 0) {
                    continue;
                }
                if (apeach[i] < ryan[i]) {
                    ryanScore += (10 - i);
                } else {
                    apeachScore += (10 - i);
                }
            }

            if (ryanScore > apeachScore) {
                int diff = ryanScore - apeachScore;

                if (diff > max) {
                    max = diff;
                    answer.clear();
                    answer.add(ryan.clone());
                } else if (diff == max) {
                    answer.add(ryan.clone());
                }
            }
        } else {
            for (int i = s; i < 11; i++) {
                ryan[i]++;
                DFS(L + 1, i);
                ryan[i]--;
            }
        }
    }

    public static int[] solution(int n, int[] info) {
        N = n;
        apeach = info.clone();
        ryan = new int[11];

        DFS(0, 0);

        if (answer.isEmpty()) {
            return new int[]{-1};
        }

        Collections.sort(answer, (o1, o2) -> {
            for (int i = 10; i >= 0; i--) {
                if (o1[i] != o2[i]) {
                    return o2[i] - o1[i];
                }
            }
            return 0;
        });

        return answer.get(0);
    }
}
