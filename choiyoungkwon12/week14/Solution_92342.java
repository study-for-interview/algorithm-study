package Programmers;

/**
 * https://programmers.co.kr/learn/courses/30/lessons/92342 양궁대회
 */
public class Solution_92342 {

    boolean[] visited = new boolean[11];
    int total = 0;
    int max = 0;
    int[] need = new int[11];
    int vict[] = new int[11];

    public static void main(String[] args) {
        Solution_92342 s = new Solution_92342();
        s.solution(5, new int[]{2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0});
    }

    public int[] solution(int n, int[] info) {
        int[] answer = {};
        for (int i = 0; i < 10; i++) {
            need[i] = info[i] + 1;
            if (info[i] != 0) {
                total += 10 - i;
            }
        }
        rec(0, 11, n);
        if (max == 0) {
            return new int[]{-1};
        }
        return vict;
    }

    private void rec(int start, int r, int n) {
        if (r == 0) {
            int sum = 0;
            int[] arr = new int[11];
            for (int i = 0; i < 11; i++) {
                if (visited[i]) {
                    arr[i] = need[i];
                    if (arr[i] == 1) {
                        sum += 10 - i;
                    } else {
                        sum += (10 - i) * 2;
                    }
                }
            }
            arr[10] = n;
            if (max == sum - total) {
                for (int i = 10; i >= 0; i--) {
                    if (vict[i] > arr[i]) {
                        break;
                    } else if (vict[i] == arr[i]) {
                        continue;
                    } else {
                        vict = arr;
                    }
                }
            } else if (max < sum - total) {
                max = Math.max(sum - total, max);
                vict = arr;
            }
            return;
        }

        if (n == 0) {
            int sum = 0;
            int[] arr = new int[11];
            for (int i = 0; i < 11; i++) {
                if (visited[i]) {
                    arr[i] = need[i];
                    if (arr[i] == 1) {
                        sum += 10 - i;
                    } else {
                        sum += (10 - i) * 2;
                    }
                }
            }
            if (max == sum - total) {
                for (int i = 10; i >= 0; i--) {
                    if (vict[i] > arr[i]) {
                        break;
                    } else if (vict[i] == arr[i]) {
                        continue;
                    } else {
                        vict = arr;
                    }
                }
            } else if (max < sum - total) {
                max = Math.max(sum - total, max);
                vict = arr;
            }
            return;
        }

        for (int i = start; i < 11; i++) {
            if (n >= need[i]) {
                visited[i] = true;
                n -= need[i];
                rec(start + 1, r - 1, n);
                visited[i] = false;
                n += need[i];
            }
        }

    }
}
