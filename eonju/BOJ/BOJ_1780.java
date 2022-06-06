import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    public static int[][] board;
    public static int MINUS = 0;
    public static int ZERO = 0;
    public static int PLUS = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        StringTokenizer st;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        divide(0, 0, N);

        System.out.println(MINUS);	// -1
        System.out.println(ZERO);	// 0
        System.out.println(PLUS);	// 1

    }


    public static void divide(int row, int col, int size) {

        // 만약 같은 색상으로 이루어져있다면 해당 색상 카운트를 증가시킨다.
        if (colorCheck(row, col, size)) {
            if(board[row][col] == -1) {
                MINUS++;
            }
            else if(board[row][col] == 0) {
                ZERO++;
            }
            else {
                PLUS++;
            }

            return;
        }

        int newSize = size / 3;

        divide(row, col, newSize);								// 왼쪽 위
        divide(row, col + newSize, newSize);						// 중앙 위
        divide(row, col + 2 * newSize, newSize);					// 오른쪽 위

        divide(row + newSize, col, newSize);						// 왼쪽 중간
        divide(row + newSize, col + newSize, newSize);			// 중앙 중간
        divide(row + newSize, col + 2 * newSize, newSize);		// 오른쪽 중간

        divide(row + 2 * newSize, col, newSize);					// 왼쪽 아래
        divide(row + 2 * newSize, col + newSize, newSize);		// 중앙 아래
        divide(row + 2 * newSize, col + 2 * newSize, newSize);	// 오른쪽 아래

    }

    public static boolean colorCheck(int row, int col, int size) {
        int color = board[row][col];

        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                if (color != board[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

}
