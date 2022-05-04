package Programmers;

/**
 * https://programmers.co.kr/learn/courses/30/lessons/92345 사라지는 발판
 */
public class Solution92345 {

    int[] dx = {0, 0, 1, -1};
    int[] dy = {-1, 1, 0, 0};

    int n, m;

    boolean[][] visit = new boolean[5][5];
    int[][] block = new int[5][5];

    public static void main(String[] args) {
        Solution92345 s = new Solution92345();
        int solution = s.solution(new int[][]{{1, 1, 1, 1, 1}}, new int[]{0, 0}, new int[]{0, 4});
        System.out.println(solution);
    }

    // 현재 상태에서 둘 다 최적의 플레이를 할 때 남은 이동 횟수
    // 반환 값이 짝수 : 플레이어가 패배함을 의미, 홀수 : 플레이어가 승리함을 의미
    // curx, cury : 현재 플레이어의 좌표, opx, opy : 상대 플레이어의 좌표
    public int solve(int curx, int cury, int opx, int opy) {
        // 플레이어가 밟고 있는 발판이 사라졌다면
        if (visit[curx][cury]) {
            return 0;
        }
        int ret = 0;

        // 플레이어를 네 방향으로 이동시켜 다음 단계로 진행할 예정
        for (int dir = 0; dir < 4; dir++) {
            int nx = curx + dx[dir];
            int ny = cury + dy[dir];
            if (OOB(nx, ny) || visit[nx][ny] || block[nx][ny] == 0) {
                continue;
            }
            visit[curx][cury] = true; // 플레이어가 직전에 있던 곳은 방문 표시를 함.

            //플레이어를 dir 방향으로 이동 시켰을 때 턴의 수
            // 다음 함수를 호출할 때 opx, opy, nx, ny 순으로 호출
            int val = solve(opx, opy, nx, ny) + 1;

            // 방문 표시 해제
            visit[curx][cury] = false;

            // 1. 현재 저장된 턴은 패배인데 새로 계산된 턴은 승리인 경우
            if (ret % 2 == 0 && val % 2 == 1) {
                ret = val; // 바로 갱신

                // 현재 저장된 턴과 새로 계산된 턴이 모두 패배인 경우
            } else if (ret % 2 == 0 && val % 2 == 0) {
                ret = Math.max(ret, val); // 최대한 늦게 지는 것을 선택

                // 현재 저장된 턴과 새로 저장된 턴이 모두 승리인 경우
            } else if (ret % 2 == 1 && val % 2 == 1) {
                ret = Math.min(ret, val); // 최대한 빠르게 이기는 것을 선택
            }
        }

        return ret;
    }

    public boolean OOB(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= m;
    }

    public int solution(int[][] board, int[] aloc, int[] bloc) {
        n = board.length;
        m = board[0].length;
        for (int i = 0; i < n; i++) {
            if (m >= 0) {
                System.arraycopy(board[i], 0, block[i], 0, m);
            }
        }
        return solve(aloc[0], aloc[1], bloc[0], bloc[1]);
    }
}
