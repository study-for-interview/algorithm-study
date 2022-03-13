//https://leetcode.com/problems/reshape-the-matrix/
import java.util.*;

public class Solution {

    public static int[][] matrixReshape(int[][] mat, int r, int c) {
        int n = mat.length;
        int m = mat[0].length;

        if (n * m != r * c) {
            return mat;
        }

        int[][] answer = new int[r][c];
        for (int i = 0; i < m * n; i++) {
            answer[i / c][i % c] = mat[i / m][i % m];
        }
        return answer;
    }
}