import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {

    private static int size;
    private static int answer;
    private static boolean[] visited;

    public static void main(String[] args) {
        System.out.println(solution(12, new int[]{1, 5, 6, 10}, new int[]{1, 2, 3, 4}));
        System.out.println(solution(12, new int[]{1, 3, 4, 9, 10}, new int[]{3, 5, 7}));
        System.out.println(solution(100, new int[]{1, 50}, new int[]{1, 1}));
        System.out.println(solution(100, new int[]{1, 50, 100}, new int[]{1, 1}));
    }

    public static int solution(int n, int[] weak, int[] dist) {
        size = n;

        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());

        for (int i = 0; i < dist.length; i++) {
            queue.add(dist[i]);
        }

        visited = new boolean[n];
        answer = Integer.MAX_VALUE;

        solve(0, weak, queue);

        if (answer == Integer.MAX_VALUE) {
            return -1;
        }

        return answer;
    }

    public static void solve(int cnt, int[] weak, PriorityQueue<Integer> queue) {
        if (queue.isEmpty()) {
            if (isVisitedAll(weak)) {
                answer = Math.min(answer, cnt);
            }
            return;
        }

        Integer dist = queue.poll();

        for (int i = 0; i < weak.length; i++) {
            int fixArea = weak[i];

            if (fixArea < size && visited[fixArea]) {
                continue;
            }

            if (fixArea + dist < size) {
                for (int j = fixArea; j < fixArea + dist; j++) {
                    visited[j] = true;
                }
            } else {
                int tmp = fixArea + dist - size;

                for (int j = fixArea; j < size; j++) {
                    visited[j] = true;
                }

                for (int j = 0; j < tmp; j++) {
                    visited[j] = true;
                }
            }

            solve(cnt + 1, weak, queue);

            if (fixArea + dist < size) {
                for (int j = fixArea; j < fixArea + dist; j++) {
                    visited[j] = false;
                }
            } else {
                int tmp = fixArea + dist - size;

                for (int j = fixArea; j < size; j++) {
                    visited[j] = false;
                }

                for (int j = 0; j < tmp; j++) {
                    visited[j] = false;
                }
            }
        }
    }

    public static boolean isVisitedAll(int[] weak) {
        for (int i = 0; i < weak.length; i++) {
            int needFixArea = weak[i];
            if (needFixArea < size && !visited[needFixArea]) {
                return false;
            }
        }

        return true;
    }
}
