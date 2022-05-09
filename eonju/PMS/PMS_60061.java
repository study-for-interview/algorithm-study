import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;

class Solution {

    static boolean[][] gidung;
    static boolean[][] boo;
    static int N;
    static Deque<int[]> ansList = new ArrayDeque<>();

    public int[][] solution(int n, int[][] build_frame) {
        N = n + 1;
        gidung = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            gidung[i] = new boolean[N];
        }
        boo = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            boo[i] = new boolean[N];
        }

        for (int[] bf : build_frame) {

            if (bf[2] == 1 && bf[3] == 1) { // 보 설치
                if (booCheck(bf[0], bf[1])) {
                    boo[bf[0]][bf[1]] = true;
                    ansList.add(new int[]{bf[0], bf[1], 1});
                }
            }

            if (bf[2] == 0 && bf[3] == 1) { // 기둥 설치
                if (gidungCheck(bf[0], bf[1])) {
                    gidung[bf[0]][bf[1]] = true;
                    ansList.add(new int[]{bf[0], bf[1], 0});
                }
            }

            if (bf[2] == 0 && bf[3] == 0) { // 기둥 삭제
                gidung[bf[0]][bf[1]] = false;
                remove(bf[0], bf[1], bf[2]);
                if (!allCheck()) {
                    gidung[bf[0]][bf[1]] = true;
                    ansList.add(new int[]{bf[0], bf[1], bf[2]});
                }


            }
            if (bf[2] == 1 && bf[3] == 0) { // 보 삭제
                boo[bf[0]][bf[1]] = false;
                remove(bf[0], bf[1], bf[2]);
                if (!allCheck()) {
                    boo[bf[0]][bf[1]] = true;
                    ansList.add(new int[]{bf[0], bf[1], bf[2]});
                }

            }

        }

        return ansListToAnswer();
    }

    static int[][] ansListToAnswer() {

        Iterator<int[]> iterator = ansList.iterator();
        int[][] ans = new int[ansList.size()][3];
        int i = 0;
        while (iterator.hasNext()) {
            ans[i++] = iterator.next();
        }
        Arrays.sort(ans, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            } else if (a[1] != b[1]) {
                return a[1] - b[1];
            } else {
                return a[2] - b[2];
            }
        });
        return ans;
    }

    static boolean booCheck(int x, int y) {
        if (y == 0) {
            return false; //바닥
        } else if (gidung[x][y - 1] || gidung[x + 1][y - 1]) { // 양쪽 기둥
            return true;
        } else if (x - 1 >= 0 && x + 1 < N && boo[x - 1][y] && boo[x + 1][y]) { // 양쪽 보
            return true;
        }
        return false;
    }

    static boolean gidungCheck(int x, int y) {
        if (y == 0) {
            return true; // 바닥
        } else if ((x - 1 >= 0 && boo[x - 1][y]) || boo[x][y]) {
            return true; // 한쪽 끝에 보
        } else if (y - 1 >= 0 && gidung[x][y - 1]) {
            return true; //다른 기둥 위에
        }

        return false;
    }

    static void remove(int x, int y, int w) {
        int ansListSize = ansList.size();
        for (int i = 0; i < ansListSize; i++) {
            int[] tmp = ansList.pollFirst();
            if (!(tmp[0] == x && tmp[1] == y && tmp[2] == w)) {
                ansList.addLast(tmp);
            }
        }
    }

    static boolean allCheck() {
        Iterator<int[]> iterator = ansList.iterator();
        while (iterator.hasNext()) {
            int[] tmp = iterator.next();
            if (tmp[2] == 1) {
                if (!booCheck(tmp[0], tmp[1])) {
                    return false;
                }

            } else {
                if (!gidungCheck(tmp[0], tmp[1])) {
                    return false;
                }
            }
        }
        return true;
    }

}
