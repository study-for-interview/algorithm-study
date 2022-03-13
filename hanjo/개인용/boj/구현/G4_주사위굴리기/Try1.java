package 구현.G4_주사위굴리기;

import java.io.*;
import java.util.*;

public class Try1 {

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

    public static void solution(int n, int m, int x, int y, int k, int[][] map, int[] commands) {
        HashMap<Integer, DiceInfo> dice = initDice();

        int bottomOfDice = 6;

        for (int command : commands) {
            if(command == 1 && y+1 < n){
                y++;
                bottomOfDice = dice.get(bottomOfDice).east;
                if(dice.get(bottomOfDice).value == 0)
                    dice.get(bottomOfDice).value = map[x][y];
                System.out.println(dice.get(dice.get(bottomOfDice).opposite).value);
            }
            else if(command == 2 && y-1 >= 0){
                y--;
                bottomOfDice = dice.get(bottomOfDice).west;
                if(dice.get(bottomOfDice).value == 0)
                    dice.get(bottomOfDice).value = map[x][y];
                System.out.println(dice.get(dice.get(bottomOfDice).opposite).value);
            }
            else if(command == 3 && x+1 < m){
                x++;
                bottomOfDice = dice.get(bottomOfDice).north;
                if(dice.get(bottomOfDice).value == 0)
                    dice.get(bottomOfDice).value = map[x][y];
                System.out.println(dice.get(dice.get(bottomOfDice).opposite).value);
            }
            else if(command == 4 && x-1 >= 0){
                x--;
                bottomOfDice = dice.get(bottomOfDice).south;
                if(dice.get(bottomOfDice).value == 0)
                    dice.get(bottomOfDice).value = map[x][y];
                System.out.println(dice.get(dice.get(bottomOfDice).opposite).value);
            }
        }
    }

    public static class DiceInfo {
        int east, west, south, north, opposite, value;

        DiceInfo(int east, int west, int south, int north, int opposite, int value) {
            this.east = east;
            this.west = west;
            this.south = south;
            this.north = north;
            this.opposite = opposite;
            this.value = value;
        }
    }

    public static HashMap<Integer, DiceInfo> initDice() {
        HashMap<Integer, DiceInfo> dice = new HashMap<>();
        dice.put(1, new DiceInfo(4, 3, 2, 5, 6, 0));
        dice.put(2, new DiceInfo(4, 3, 6, 1, 5, 0));
        dice.put(3, new DiceInfo(6, 1, 5, 2, 4, 0));
        dice.put(4, new DiceInfo(1, 6, 5, 2, 3, 0));
        dice.put(5, new DiceInfo(4, 3, 1, 6, 2, 0));
        dice.put(6, new DiceInfo(4, 3, 5, 2, 1, 0));
        return dice;
    }

    public static void copyAndPrint(HashMap<Integer, DiceInfo> dice, int bottomOfDice, int value){
        if(dice.get(bottomOfDice).value == 0)
            dice.get(bottomOfDice).value = value;
        System.out.println(dice.get(bottomOfDice).opposite);
    }


}