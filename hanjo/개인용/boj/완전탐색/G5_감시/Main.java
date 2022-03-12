package 완전탐색.G5_감시;

import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static int m;

    public static int[][] copy(int[][] map) {
        int[][] copiedMap = new int[n][m];
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                copiedMap[x][y] = map[x][y];
            }
        }
        return copiedMap;
    }

    public static int[][] check(int cctvX, int cctvY, int map[][], String[] commands) {
        int[][] cMap = copy(map);

        for (String command : commands) {
            if (command.equals("UP")) {
                int point = cctvX - 1;
                while (point >= 0 && cMap[point][cctvY] != 6) {
                    if (cMap[point][cctvY] == 0)
                        cMap[point][cctvY] = -1;
                    point--;
                }
            } 
            else if (command.equals("DOWN")) {
                int point = cctvX + 1;
                while (point < n && cMap[point][cctvY] != 6) {
                    if (cMap[point][cctvY] == 0)
                        cMap[point][cctvY] = -1;
                    point++;
                }
            } 
            else if (command.equals("LEFT")) {
                int point = cctvY - 1;
                while (point >= 0 && cMap[cctvX][point] != 6) {
                    if (cMap[cctvX][point] == 0)
                        cMap[cctvX][point] = -1;
                    point--;
                }
            } 
            else if (command.equals("RIGHT")) {
                int point = cctvY + 1;
                while (point < m && cMap[cctvX][point] != 6) {
                    if (cMap[cctvX][point] == 0)
                        cMap[cctvX][point] = -1;
                    point++;
                }
            }
        }
        return cMap;
    }

    public static int countBlindSpot(int map[][]) {
        int count = 0;
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                if (map[x][y] == 0) {
                    count++;
                }
            }
        }
        return count;
    }

    public static int reculsive(int startX, int startY, int map[][]) {
        int[][] cMap = copy(map);
        ArrayList<Integer> blindSpotNum = new ArrayList<>();

        for (int x = startX; x < n; x++) {
            for (int y = 0; y < m; y++) {

                if(x == startX && y < startY)
                    continue;

                int nextX = x;
                int nextY = y + 1;
                if (nextY == m) {
                    nextX += 1;
                    nextY = 0;
                }

                if (cMap[x][y] == 1) {                    
                    blindSpotNum.add(reculsive(nextX, nextY, check(x, y, cMap, new String[] { "UP" })));
                    blindSpotNum.add(reculsive(nextX, nextY, check(x, y, cMap, new String[] { "DOWN" })));
                    blindSpotNum.add(reculsive(nextX, nextY, check(x, y, cMap, new String[] { "LEFT" })));
                    blindSpotNum.add(reculsive(nextX, nextY, check(x, y, cMap, new String[] { "RIGHT" })));
                    return Collections.min(blindSpotNum);
                } 
                else if (cMap[x][y] == 2) {
                    blindSpotNum.add(reculsive(nextX, nextY, check(x, y, cMap, new String[] { "UP", "DOWN" })));
                    blindSpotNum.add(reculsive(nextX, nextY, check(x, y, cMap, new String[] { "LEFT", "RIGHT" })));
                    return Collections.min(blindSpotNum);
                } 
                else if (cMap[x][y] == 3) {
                    blindSpotNum.add(reculsive(nextX, nextY, check(x, y, cMap, new String[] { "UP", "LEFT" })));
                    blindSpotNum.add(reculsive(nextX, nextY, check(x, y, cMap, new String[] { "LEFT", "DOWN" })));
                    blindSpotNum.add(reculsive(nextX, nextY, check(x, y, cMap, new String[] { "DOWN", "RIGHT" })));
                    blindSpotNum.add(reculsive(nextX, nextY, check(x, y, cMap, new String[] { "RIGHT", "UP" })));
                    return Collections.min(blindSpotNum);
                } 
                else if (cMap[x][y] == 4) {
                    blindSpotNum.add(reculsive(nextX, nextY, check(x, y, cMap, new String[] { "LEFT", "UP", "RIGHT" })));
                    blindSpotNum.add(reculsive(nextX, nextY, check(x, y, cMap, new String[] { "UP", "RIGHT", "DOWN" })));
                    blindSpotNum.add(reculsive(nextX, nextY, check(x, y, cMap, new String[] { "RIGHT", "DOWN", "LEFT" })));
                    blindSpotNum.add(reculsive(nextX, nextY, check(x, y, cMap, new String[] { "DOWN", "LEFT", "UP" })));
                    return Collections.min(blindSpotNum);
                } 
                else if (cMap[x][y] == 5) {
                    return reculsive(nextX, nextY, check(x, y, cMap, new String[] { "UP", "RIGHT", "DOWN", "LEFT" }));
                }
            }
        }
        return countBlindSpot(cMap);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];
        for (int x = 0; x < n; x++) {
            st = new StringTokenizer(br.readLine());
            for (int y = 0; y < m; y++) {
                map[x][y] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(reculsive(0, 0, map));
    }

}

/**
 * 링크 : https://www.acmicpc.net/problem/15683
 * 성공여부 : 1시간 초과해서 실패 / 답을 보지않고 구현은 성공
 * 풀이시간 : 3h
 * 
 * 시간복잡도 : 
 * 메모리 : 144668 KB
 * 소요 시간 : 936 ms
 * 
 * ------------------------------------------------------------
 * 
 * 수많은 경우의 수에 대한 완전탐색을 어떻게 구현할지가 중요한 문제 (재귀나 dfs bfs를 사용해야함)
 * 나는 단순 재귀를 생각했는데 풀고나니 dfs인 것 같음 -> 재귀는 웬만하면 DFS?
 * 
 * 2개이상의 숫자의 최소/최대값을 구하기 위해 List + Collections.min 사용함
 * 
 * < 시행착오1 >
 * 배열을 매개변수로 넘기면 객체가 새롭게 복사되는줄 알았으나 주소만 복사하는 Call by reference가 일어남
 * 따라서 배열을 따로 복사해줌 (2차원배열이라 따로 메소드를 사용하지 않음)
 * 
 * < 시행착오2 >
 * map에서 방문한 인덱스의 관리를 하지 않고 / 다음 인덱스를 바로 넘겨줌으로써 계속 오류났음
 * 오류들을 잡기 위해 예외처리를 많이 해줘야 했다. 확실히 방문 관리를 해주는게 좋을듯
 * 
 * 
 */