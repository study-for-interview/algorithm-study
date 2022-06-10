import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {

    private static List<int[]> weakCases;
    private static List<int[]> distCases;
    private static int answer;

    public static void main(String[] args) {
//        System.out.println(solution(12, new int[]{1, 5, 6, 10}, new int[]{1, 2, 3, 4}));
        System.out.println(solution(12, new int[]{1, 3, 4, 9, 10}, new int[]{3, 5, 7}));

    }

    public static int solution(int n, int[] weak, int[] dist) {
        weakCases = new ArrayList<>();
        distCases = new ArrayList<>();
        answer = Integer.MAX_VALUE;

        makeDistCases(0, Arrays.copyOf(dist, dist.length), dist);
        makeWeakCases(0, Arrays.copyOf(weak, weak.length), weak);

        for (int[] weakCase : weakCases) {
            for (int[] distCase : distCases) {
                checkWall(n, weakCase, distCase);
            }
        }

        if (answer == Integer.MAX_VALUE) {
            answer = -1;
        }

        return answer;
    }

    public static void checkWall(int n, int[] weakCase, int[] distCase) {
        boolean[] isFixed = new boolean[n];
        int distIdx = 0;

        for (int weakArea : weakCase) {
            if (isFixed[weakArea]) {
                continue;
            }

            if (distIdx >= distCase.length) {
                return;
            }

            int dist = distCase[distIdx];

            if (weakArea + dist > n) {
                Arrays.fill(isFixed, weakArea, n, true);
                Arrays.fill(isFixed, 0, weakArea + dist - n, true);
            } else {
                Arrays.fill(isFixed, weakArea, weakArea + dist, true);
            }

            distIdx++;
        }

        answer = Math.min(answer, distIdx);
    }

    public static void makeWeakCases(int depth, int[] tmp, int[] weak) {
        if (depth == weak.length) {
            weakCases.add(Arrays.copyOf(tmp, tmp.length));
            return;
        }

        for (int i = depth; i < weak.length; i++) {
            swap(tmp, depth, i);
            makeWeakCases(depth + 1, tmp, weak);
            swap(tmp, depth, i);
        }
    }

    public static void makeDistCases(int depth, int[] tmp, int[] dist) {
        if (depth == dist.length) {
            distCases.add(Arrays.copyOf(tmp, tmp.length));
            return;
        }

        for (int i = depth; i < dist.length; i++) {
            swap(tmp, depth, i);
            makeDistCases(depth + 1, tmp, dist);
            swap(tmp, depth, i);
        }
    }

    public static void swap(int[] arr, int depth, int i) {
        int tmp = arr[depth];
        arr[depth] = arr[i];
        arr[i] = tmp;
    }
}
