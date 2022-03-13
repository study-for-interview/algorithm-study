package 구현.G4_주사위굴리기;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 세로
        int m = Integer.parseInt(st.nextToken()); // 가로
        int x = Integer.parseInt(st.nextToken()); // 첫 주사위 x 좌표
        int y = Integer.parseInt(st.nextToken()); // 첫 주사위 y 좌표
        int k = Integer.parseInt(st.nextToken()); // 명령 개수

        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] commands = new int[k];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            commands[i] = Integer.parseInt(st.nextToken());
        }

        solution(n, m, x, y, k, map, commands);
    }


    public static final int[] dx = {0, 0, 0, -1, 1};
    public static final int[] dy = {0, 1, -1, 0, 0};

    public static void solution(int n, int m, int x, int y, int k, int[][] map, int[] commands) {

        Dice dice = new Dice();
        int[] valueOfDice = new int[7];

        for (int command : commands) {
            int nextX = x + dx[command];
            int nextY = y + dy[command];

            if(nextX < n && nextX >= 0 && nextY < m && nextY >= 0){
                // 좌표 이동
                x = nextX;
                y = nextY;
                dice.roll(command);

                if(map[x][y] == 0){
                    map[x][y] = valueOfDice[dice.bottom];
                }
                else {
                    valueOfDice[dice.bottom] = map[x][y];
                    map[x][y] = 0;
                }
                System.out.println(valueOfDice[dice.top]);
            }
        }
    }

    public static class Dice {
        int top = 1;
        int north = 2;
        int west = 3;
        int east = 4;
        int south = 5;
        int bottom = 6;

        public void roll(int command){
            int cTop = this.top;
            int cNorth = this.north;
            int cWest = this.west;
            int cEast = this.east;
            int cSouth = this.south;
            int cBottom = this.bottom;
            
            // east
            if(command ==1){
                this.top = cWest;
                this.bottom = cEast;
                this.east = cTop;
                this.west = cBottom;
            }
            // west
            else if(command == 2){
                this.top = cEast;
                this.bottom = cWest;
                this.east = cBottom;
                this.west = cTop;
            }
            // north
            else if(command == 3){
                this.top = cSouth;
                this.bottom = cNorth;
                this.north = cTop;
                this.south = cBottom;
            }
            // south
            else if(command == 4){
                this.top = cNorth;
                this.bottom = cSouth;
                this.north = cBottom;
                this.south = cTop;
            }
        }
    }
}

/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/14499
 * 성공여부 : 실패
 * 풀이시간 : 3H
 * 
 * 시간복잡도 : O(k)
 * 메모리 : 14836 KB
 * 소요 시간 :176 ms
 * ================================================================================
 * 
 * 문제를 읽고 빠르게 이해해야하는 문제임 (계속 읽어도 이해가 안됐음)
 * 주사위를 굴리고 그 상태를 어떻게 저장하고 업데이트할지가 중요하고... 그것만 하면 구현은 쉽다
 * 
 * < 시행착오 (Try1) >
 * 주사위를 굴리면 계속 방향이 바뀐다는 것을 간과함
 * (바닥면이 6번이고, 동쪽으로 굴렸을때 항상 3이 나온다고 착각함)
 * 그냥 삽질함... 
 * 
 */