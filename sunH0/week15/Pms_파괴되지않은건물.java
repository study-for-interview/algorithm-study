package kakao;

public class Pms_파괴되지않은건물 {

    static int[][] sum;
    static int N, M;

    public static int solution(int[][] board, int[][] skill) {
        int answer = 0;
        N = board.length;
        M = board[0].length;

        sum = new int[N + 1][M + 1];
        for (int[] s : skill) {
            int y1 = s[1], x1 = s[2];
            int y2 = s[3], x2 = s[4];
            int degree = s[5] * (s[0] == 1 ? -1 : 1);

            sum[y1][x1] += degree;
            sum[y1][x2 + 1] += (degree * -1);
            sum[y2 + 1][x1] += (degree * -1);
            sum[y2 + 1][x2 + 1] += degree;
        }

        prefixSum();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] + sum[i][j] > 0) answer++;
            }
        }
        return answer;

    }

    private static void prefixSum() {
        // 상하
        for (int x = 1; x < N; x++) {
            for (int y = 0; y < M; y++) {
                sum[x][y] += sum[x - 1][y];
            }
        }
        // 좌우
        for (int y = 1; y < M; y++) {
            for (int x = 0; x < N; x++) {
                sum[x][y] += sum[x][y - 1];
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(solution(
                new int[][]{
                        {5,5,5,5,5}
                        ,{5,5,5,5,5}
                        ,{5,5,5,5,5}
                        ,{5,5,5,5,5}
                },
                new int[][]{
                        {1,0,0,3,4,4}
                        ,{1,2,0,2,3,2}
                        ,{2,1,0,3,1,2}
                        ,{1,0,1,3,3,1}
                }
        ));
    }
    
}

// unsolve : 효율성 테스트 시간초과(누적합)  -> 빈 배열에 (x1,y1)부터 (x2,y2)까지 n만큼의 변화는 (x1,y1)에 +n, (x1,y2+1)에 -n, (x2+1,y1)에 -n, (x2+1,y2+1)에 +n -> 누적합(상->하/좌->우)
// 브루트 포스 : O(N * M * K) || 누적합 :  O(K + N * M)  배열생성 (K) + 누적합 (N * M)