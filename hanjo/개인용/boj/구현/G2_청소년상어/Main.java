package 구현.G2_청소년상어;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        Fish[][] map = new Fish[4][4];
        for(int i=0; i<4; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<4; j++){
                int num = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken()) - 1;
                map[i][j] = new Fish(num, dir);
            }
        }

        System.out.println(new Solution().solution(map));
    }

    public static class Fish{
        public int num;
        public int dir;
        
        public Fish(int num, int dir){
            this.num = num;
            this.dir = dir;
        }
    }

    public static class Solution{

        public int max = 0;
        public final int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
        public final int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};

        public int solution(Fish[][] map){
            int sum = map[0][0].num;
            map[0][0].num = -1;
            dfs(map, sum);
            
            return max;
        }

        public void dfs(Fish[][] origin, int sum){

            Fish[][] map = copy(origin);

            moveFish(map);

            for(int x=0; x<4; x++){
                for(int y=0; y<4; y++){
                    if(map[x][y].num != -1){
                        continue;
                    }
                    Fish shark = map[x][y];
                    for(int i=1; i<=3; i++){
                        int nextX = x + dx[shark.dir]*i;
                        int nextY = y + dy[shark.dir]*i;
                        if(isOut(nextX, nextY) || map[nextX][nextY].num == 0){
                            continue;
                        }
                        int nextNum = map[nextX][nextY].num;
                        shark.num = 0;
                        map[nextX][nextY].num = -1;
                        dfs(map, sum + nextNum);
                        shark.num = -1;
                        map[nextX][nextY].num = nextNum;
                    }
                }
            }
            
            max = Math.max(max, sum);
        }

        public void moveFish(Fish[][] map){
            Loop : for(int num=1; num<=16; num++){
                for(int x=0; x<4; x++){
                    for(int y=0; y<4; y++){
                        Fish cur = map[x][y];
                        if(cur.num != num){
                            continue;
                        }
                        for(int i=0; i<8; i++){
                            int dir = (cur.dir + i) % 8;
                            int nextX = x + dx[dir];
                            int nextY = y + dy[dir];
                            if(isOut(nextX, nextY) || map[nextX][nextY].num == -1){
                                continue;
                            }
                            cur.dir = dir;
                            swap(map, x, y, nextX, nextY);
                            continue Loop;
                        }
                    }
                }
            }
        }

        public boolean isOut(int x, int y){
            if(x<0 || x>=4 || y<0 || y>=4){
                return true;
            }
            return false;
        }

        public void swap(Fish[][] map, int x, int y, int nextX, int nextY){
            Fish temp = map[x][y];
            map[x][y] = map[nextX][nextY];
            map[nextX][nextY] = temp;
        }

        public Fish[][] copy(Fish[][] origin){
            Fish[][] copy = new Fish[4][4];
            for(int x=0; x<4; x++){
                for(int y=0; y<4; y++){
                    copy[x][y] = new Fish(origin[x][y].num, origin[x][y].dir);
                }
            }
            return copy;
        }
    }
    
}

/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/19236
 * 날짜 : 220329
 * 성공여부 : 실패(1시간 초과)
 * 풀이시간 : 2h
 * 
 * 시간복잡도 : ?
 * 메모리 : 14260 KB
 * 소요 시간 : 132 ms
 * ================================================================================
 * 
 * 빡구현 시뮬레이션 문제....
 * 
 * 맨처음엔 물고기 이동과 상어 이동 둘다 백트래킹하려 하다가 진짜 답이 안나와서 포기함
 * 결국 상어 이동만 백트래킹하고 물고기 이동은 배열 복사해서 넘겨줌
 * 
 * 근데 또 클래스 배열이라서 어떤 복사 유틸메소드를 사용해도 완전한 복사가 되지 않음. (실험해봄)
 * 따라서 그냥 쌩 포문 돌려서 복사했다.
 * 
 * 한번에 풀리긴 했는데 어떻게 푼건지 기억이 안나는 문제 ㅋㅋㅋㅋㅋㅋ
 * 
 */