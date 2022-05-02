class Result {
    boolean isWin;
    int cnt;

    public Result(boolean isWin, int cnt) {
        this.isWin = isWin;
        this.cnt = cnt;
    }
}

class Solution {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int max = 10_000_000;

    public int solution(int[][] board, int[] aloc, int[] bloc) {
        Result result = dfs(board,aloc,bloc,1,0);

        return result.cnt;

    }

    static Result dfs(int[][] board, int[] aloc, int[] bloc , int turn, int move) {

        int ax = aloc[0];
        int ay = aloc[1];

        int bx = bloc[0];
        int by = bloc[1];
        System.out.println(ax + " " + ay + " / " + bx + " " + by + " / " + turn + " " + move);

        // a턴이면서 a가 졌거나 b턴이면서 b가 지면,패배사
        if((turn > 0 && board[ax][ay] == 0) || (turn < 0 && board[bx][by] == 0)){
            return new Result(false,move);
        }



        int win = max;
        int lose = 0;

        for (int i = 0; i < 4; i++) {
            //a 턴일때
            System.out.println("  " + i + " : " + ax + " " + ay + " / " + bx + " " + by + " / " + turn + " " + move);
            if (turn > 0) {

                int nx = ax + dx[i];
                int ny = ay + dy[i];

                if(nx < 0 || ny < 0|| nx>= board.length || ny >=board[0].length) continue; //범위벗어남
                if(board[nx][ny] == 0) continue; //발판이 없는경우
                board[ax][ay] = 0;
                Result a = dfs(board,new int[] {nx,ny}, bloc, -turn, move+1);
                board[ax][ay] = 1;
                if(!a.isWin){
                    win = Math.min(win,a.cnt);
                }else{
                    lose = Math.max(lose,a.cnt);
                }


            } else {

                int nbx = bx + dx[i];
                int nby = by + dy[i];

                if(nbx < 0 || nby < 0|| nbx>= board.length || nby >=board[0].length) continue; //범위벗어남
                if(board[nbx][nby] == 0) continue; //발판이 없는경우
                board[bx][by] = 0;
                Result b = dfs(board,aloc, new int[] {nbx,nby}, -turn, move+1);
                board[bx][by] = 1;
                if(!b.isWin){
                    win = Math.min(win,b.cnt);
                }else{
                    lose = Math.max(lose,b.cnt);
                }


            }
        }

        if (win == max && lose == 0) {
            return new Result(false, move);
        } else if (win != max) {
            return new Result(true, win);
        } else {
            return new Result(false, lose);
        }
    }
}