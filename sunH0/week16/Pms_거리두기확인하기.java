package kakao;

import java.util.*;

public class Pms_거리두기확인하기 {

    public int[][] dir = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];      

        for(int i = 0; i < places.length; i++){
            char[][] map = new char[5][5];
            for(int j = 0; j < 5; j++){
                map[j] = places[i][j].toCharArray(); 
            }
            answer[i] = process(map);
        }        
        
        return answer;
    }
    
    public int process(char[][] map){

        for(int i = 0; i < 5; i ++){
            for(int j = 0; j < 5; j++){
                if(map[i][j] == 'P' && bfs(map, i, j) == 0) return 0;
            }
        }
        return 1;
    }    
    
    
    public int bfs(char[][] map, int xx, int yy) {
        boolean[][] visited = new boolean[5][5];
        
        Queue <Integer> q = new LinkedList<Integer>();
        visited[xx][yy] = true;
        q.offer(xx);
        q.offer(yy);
        q.offer(0);
        
        while(!q.isEmpty()){
            int x = q.poll();
            int y = q.poll();
            int cnt = q.poll();
            
            for(int[] d : dir){
                int nx = x + d[0];
                int ny = y + d[1];
                int nCnt = cnt + 1;
                if(isValid(visited, map, nx, ny, nCnt)){
                    if(map[nx][ny] == 'P') return 0;
                    visited[nx][ny] = true;
                    q.offer(nx);
                    q.offer(ny);
                    q.offer(nCnt);
                }
            }
        }
        
        return 1;
    }
    
    public boolean isValid(boolean[][] visited, char[][] map, int x, int y, int cnt){
        if(x < 0 || y < 0 || x >= 5 || y >= 5) return false;
        if(visited[x][y] || map[x][y] == 'X' || cnt == 3) return false;
        return true;
    }
}
