package Programmers;

/**
 * https://programmers.co.kr/learn/courses/30/lessons/60059 자물쇠와 열쇠
 */
public class Prgms_60059 {

    private static int n, m;

    public static void main(String[] args) {
        Solution18 s = new Solution18();
        int[][] key = new int[][]{{0, 0, 0}, {1, 0, 0,}, {0, 1, 1}};
        int[][] lock = new int[][]{{1, 1, 1}, {1, 1, 0,}, {1, 0, 1}};
        boolean solution = s.solution(key, lock);
        System.out.println("result = " + solution);
    }

    public boolean solution(int[][] key, int[][] lock) {
        n = lock.length;
        m = key.length;

        int t = 0;
        int[][] tmp = getCopyKey(key);
        while (t < 4) {
            int[][] exKey = extendKeyToLock(tmp);
            boolean result = matchingKeyToLock(exKey, lock);
            if (result) {
                return true;
            }
            tmp = turnKey(tmp);
            t++;
        }
        return false;
    }

    private int[][] turnKey(int[][] tmp) {
        int[][] trunKey = new int[m][m];
        int y = m - 1;
        for (int i = 0; i < m; i++) { // y
            for (int j = 0; j < m; j++) { // x
                trunKey[j][y] = tmp[i][j];
            }
            y--;
        }
        return trunKey;
    }

    private boolean matchingKeyToLock(int[][] key, int[][] lock) {
        for (int i = 0; i < m + n - 1; i++) {
            for (int j = 0; j < m + n - 1; j++) {
                boolean flag = true;
                out:
                for (int y = 0; y < n; y++) {
                    for (int x = 0; x < n; x++) {
                        if (lock[y][x] == key[i + y][j + x]) {
                            flag = false;
                            break out;
                        }
                    }
                }
                if (flag) {
                    return true;
                }
            }
        }
        return false;
    }

    private int[][] extendKeyToLock(int[][] tmp) {
        int[][] extendKey = new int[m + 2 * (n - 1)][m + 2 * (n - 1)];
        for (int i = 0; i < m; i++) {
            System.arraycopy(tmp[i], 0, extendKey[i + n - 1], n - 1, m);
        }
        return extendKey;
    }

    private int[][] getCopyKey(int[][] key) {
        int[][] copy = new int[m][m];
        for (int i = 0; i < m; i++) {
            System.arraycopy(key[i], 0, copy[i], 0, m);
        }
        return copy;
    }

}
