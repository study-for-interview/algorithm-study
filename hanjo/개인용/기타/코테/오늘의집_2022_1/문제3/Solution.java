package 코테.오늘의집_2022_1.문제3;

import java.util.*;

public class Solution {

    public static class Node {
        public int x;
        public int y;
        
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static final int[] dx = {-1, 0, 1, 0};
    public static final int[] dy = {0, 1, 0, -1};

    public int n;
    public int m;
    public int room;
    public int bath;
    public int[][] home;
    public int count;

    public int solution(int n, int m, int room, int bath){
        // 초기화
        this.n = n;
        this.m = m;
        this.room = room;
        this.bath = bath;
        home = new int[n][m];
        // dfs로 모든 배치 조합 찾기
        count = 0;
        dfs(0, 0, 0);
        return count;
    }

    public void dfs(int start, int roomCnt, int bathCnt){
        // 조합이 완성되면 bfs로 조건 검증
        if(roomCnt == room && bathCnt == bath){
            for(int x=0; x<n; x++){
                for(int y=0; y<m; y++){
                    if(home[x][y] != 0){
                       continue;
                    }
                    if(bfs(new Node(x, y))){
                        count++;
                        return;
                    }
                }
            }
            return;
        }
        if(start == n*m){   
            return;
        }

        for(int i=start; i<n*m; i++){
            int x = i/m;
            int y = i%m;
            // 자리 없으면 cut
            if(home[x][y] != 0){
                continue;
            }

            int next = i+1;
            home[x][y] = next;
            // 방
            if(roomCnt < room && canSetRoom(x, y)){
                home[x+1][y] = next;
                home[x][y+1] = next;
                home[x+1][y+1] = next;
                dfs(next, roomCnt+1, bathCnt);
                home[x+1][y] = 0;
                home[x][y+1] = 0;
                home[x+1][y+1] = 0;
            }
            // 화장실
            if(bathCnt < bath){
                // 가로
                if(canSetRowBath(x, y)){
                    home[x][y+1] = next;
                    dfs(next, roomCnt, bathCnt+1);
                    home[x][y+1] = 0;
                }
                // 세로
                if(canSetColBath(x, y)){
                    home[x+1][y] = next;
                    dfs(next, roomCnt, bathCnt+1);
                    home[x+1][y] = 0;
                }
            }
            home[x][y] = 0;
        }
    }

    public boolean bfs(Node start){

        // 조건 1 : 모든 방과 화장실은 최소 하나의 빈공간 인접
        Set<Integer> numbers = new HashSet<>(); 
        // 조건 2 : 모든 빈공간이 연결
        boolean isConnected = true;
        // 조건 3 : 최소 하나의 빈공간은 테두리에 인접
        boolean isBorder = false;

        boolean[][] isVisited = new boolean[n][m];
        Queue<Node> queue = new LinkedList<>();
        queue.offer(start);
        isVisited[start.x][start.y] = true;

        while(!queue.isEmpty()){
            Node cur = queue.poll();
            for(int i=0; i<4; i++){
                Node next = new Node(cur.x + dx[i], cur.y + dy[i]);
                if(next.x < 0 || next.x >= n || next.y < 0 || next.y >= m){
                    isBorder = true;
                    continue;
                }
                if(home[next.x][next.y] != 0){
                    numbers.add(home[next.x][next.y]);
                    continue;
                }
                if(!isVisited[next.x][next.y]){
                    isVisited[next.x][next.y] = true;
                    queue.offer(next);
                }
            }
        }

        for(int x=0; x<n; x++){
            for(int y=0; y<m; y++){
                if(home[x][y] == 0 && !isVisited[x][y]){
                   isConnected = false;
                }
            }
        }
        return numbers.size() == room + bath && isBorder && isConnected;
    }

    public boolean canSetRoom(int x, int y){
        if(x+1 >= n || y+1 >= m){
            return false;
        }
        if(home[x+1][y] + home[x][y+1] + home[x+1][y+1] != 0){
            return false;
        }
        return true;
    }

    public boolean canSetRowBath(int x, int y){
        if(y+1 >= m){
            return false;
        }
        if(home[x][y+1] != 0){
            return false;
        }
        return true;
    }

    public boolean canSetColBath(int x, int y){
        if(x+1 >= n){
            return false;
        }
        if(home[x+1][y] != 0){
            return false;
        }
        return true;
    }

    public static void main(String[] args){
        // 20
        System.out.println(new Solution().solution(4, 5, 3, 1));
        // 0
        System.out.println(new Solution().solution(2, 3, 1, 1));
        // 0
        System.out.println(new Solution().solution(3, 4, 2, 1));
        // 6
        System.out.println(new Solution().solution(2, 4, 1, 1));
    }
}
