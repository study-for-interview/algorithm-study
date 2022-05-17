package 카카오.L3_사라지는발판;

class Try1 {

    public static final int[] dx = {1, -1, 0, 0};
    public static final int[] dy = {0, 0, 1, -1};
    public int[][] board;
    public int n;
    public int m;
    public int max = 0;
    public int min = Integer.MAX_VALUE;
    
    public int solution(int[][] board, int[] aloc, int[] bloc) {

        this.board = board;
        n = board.length;
        m = board[0].length;

        dfs(true, aloc[0], aloc[1], bloc[0], bloc[1], 0, 0, 0);

        // System.out.println(max + "," + min);
        System.out.println();
        return max + min;
    }

    public void dfs(boolean turn, int ax, int ay, int bx, int by, int aCount, int bCount, int depth){

        // if(!turn && ax == bx && ay == by){
        //     max = Math.max(max, bCount);
        //     min = Math.min(min, aCount);
        //     return;
        // }

        boolean isMovedA = false;
        boolean isMovedB = false;

        for(int i=0; i<4; i++){
            // A 차례
            if(turn && board[ax][ay] == 1){
                int nx = ax + dx[i];
                int ny = ay + dy[i];
                if(isOut(nx, ny) || board[nx][ny] == 0){
                    continue;
                }
                isMovedA = true;
                board[ax][ay] = 0;
                dfs(false, nx, ny, bx, by, aCount+1, bCount, depth+1);
                board[ax][ay] = 1;
            }
            // B 차례
            else if(!turn && board[bx][by] == 1){
                int nx = bx + dx[i];
                int ny = by + dy[i];
                if(isOut(nx, ny) || board[nx][ny] == 0){
                    continue;
                }
                isMovedB = true;
                board[bx][by] = 0;
                dfs(true, ax, ay, nx, ny, aCount, bCount+1, depth+1);
                board[bx][by] = 1;
            }
        }

        // A가 지는경우
        if(turn && !isMovedA){
            // System.out.println("A 패배");
            // System.out.println(aCount + "," + bCount);

            max = Math.max(max, aCount);
            min = Math.min(min, bCount);
            return;
        }
        // B가 지는경우
        if(!turn && !isMovedB){
            // System.out.println("B 패배");
            // System.out.println(aCount + "," + bCount);

            max = Math.max(max, bCount);
            min = Math.min(min, aCount);
            return;
        }
    }

    public boolean isOut(int x, int y){
        return x < 0 || x >= n || y < 0 || y >= m;
    }



    public static void main(String[] args) {
        // 5
        System.out.println(new Try1().solution(
            new int[][]{{1,1,1}, {1,1,1}, {1,1,1}},
            new int[]{1,0},
            new int[]{1,2}
        ));
        //4
        System.out.println(new Try1().solution(
            new int[][]{{1,1,1}, {1,0,1}, {1,1,1}},
            new int[]{1,0},
            new int[]{1,2}
        ));
        // 4
        System.out.println(new Try1().solution(
            new int[][]{{1,1,1,1,1}},
            new int[]{0,0},
            new int[]{0,4}
        ));
        // 0
        System.out.println(new Try1().solution(
            new int[][]{{1}},
            new int[]{0,0},
            new int[]{0,0}
        ));
    }
}

/**
 * ================================================================================
 * 링크 : https://programmers.co.kr/learn/courses/30/lessons/92345
 * 날짜 : 220429
 * 성공여부 : 실패 (해결 X)
 * 풀이시간 : 2h
 * ================================================================================
 * 
 * dfs + 미니맥스 알고리즘
 * 
 * 너무 어려워서 포기함. 
 * 
 */