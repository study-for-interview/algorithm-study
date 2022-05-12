package 기타;

import java.util.*;
 
class Practice3 {
    class Item{
        int x1, x2, y1, y2, time, vertical;
        Item(int x1, int y1, int x2, int y2, int time, int v){
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.time = time;
            this.vertical = v;
        }
    }
    
    public int solution(int[][] board) {
        int answer = 0;
        Queue<Item> q = new LinkedList<>();
        int[][] op = {{1,0},{-1,0},{0,1},{0,-1}};
        boolean[][][] visited = new boolean[board.length][board.length][2];
        
        q.offer(new Item(0, 0, 0, 1, 0, 0));
        
        while(!q.isEmpty()){
            Item item = q.peek();
            q.poll();
            
            if(item.x1 < 0 || item.x1 >= board.length || item.y1 < 0 || item.y1 >= board.length ||
                   item.x2 < 0 || item.x2 >= board.length || item.y2 < 0 || item.y2 >= board.length){
                continue;
            }
            
            if(board[item.x1][item.y1] == 1 || board[item.x2][item.y2] == 1){
                continue;
            }
            
            if(visited[item.x1][item.y1][item.vertical] && 
              visited[item.x2][item.y2][item.vertical])
                continue;
                
            if((item.x1 == board.length - 1 && item.y1 == board.length - 1) ||
               (item.x2 == board.length - 1 && item.y2 == board.length - 1)){
                answer = item.time;
                break;
            }
            
            
            visited[item.x1][item.y1][item.vertical] = true;
            visited[item.x2][item.y2][item.vertical] = true;
            
            for(int i = 0; i < op.length; i++){
                int n_x1 = item.x1 + op[i][0];
                int n_y1 = item.y1 + op[i][1];
                int n_x2 = item.x2 + op[i][0];
                int n_y2 = item.y2 + op[i][1];
 
                q.offer(new Item(n_x1, n_y1, n_x2, n_y2, item.time + 1, item.vertical));
            }
            
            if(item.vertical == 1){
                if(item.y1 - 1 >= 0 && board[item.x1][item.y1 - 1] == 0 && board[item.x2][item.y2 - 1] == 0){
                    q.offer(new Item(item.x1, item.y1, item.x1, item.y1 - 1, item.time + 1, 0));
                    q.offer(new Item(item.x2, item.y2, item.x2, item.y2 - 1, item.time + 1, 0));
                }
                
                if(item.y1 + 1 <= (board.length - 1) && 
                   board[item.x1][item.y1 + 1] == 0 && board[item.x2][item.y2 + 1] == 0){
                    q.offer(new Item(item.x1, item.y1, item.x1, item.y1 + 1, item.time + 1, 0));
                    q.offer(new Item(item.x2, item.y2, item.x2, item.y2 + 1, item.time + 1, 0));                    
                }
            }else{
                if(item.x1 - 1 >= 0 && board[item.x1 - 1][item.y1] == 0 &&
                  board[item.x2 - 1][item.y2] == 0){            
                    q.offer(new Item(item.x1, item.y1, item.x1 - 1, item.y1, item.time + 1, 1));
                    q.offer(new Item(item.x2, item.y2, item.x2 - 1, item.y2, item.time + 1, 1));
                }
                
                if(item.x1 + 1 <= (board.length - 1) && board[item.x1 + 1][item.y1] == 0 &&
                  board[item.x2 + 1][item.y2] == 0){
                    q.offer(new Item(item.x1, item.y1, item.x1 + 1, item.y1, item.time + 1, 1));
                    q.offer(new Item(item.x2, item.y2, item.x2 + 1, item.y2, item.time + 1, 1));    
                }
            }
        }
 
        return answer;
    }
}
