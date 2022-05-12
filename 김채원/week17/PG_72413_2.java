import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;

import java.util.*;

/*
A에서 B 최단거리 구하고
시작지점에서 A에서 B 지나온 노드들 중에서 가장 짧게 걸리는거 더해주고
만약 A B 지나온 루트랑 겹치면 빼주기
 */
class Edge implements Comparable<Edge> {
    public int vex;
    public int cost;

    Edge(int vex, int cost) {
        this.vex = vex;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge ob) {
        return this.cost - ob.cost;
    }
}


class Solution {
    static int[][] arr;
    static int N;
    static final int INF = 9999999;

    static void FolydTest() {
        for (int k = 1; k < N; ++k) { // 경유지
            for (int i = 1; i < N; ++i) { // 출발지
                if (i == k) continue; // 경유지와 출발지가 같다면 패스
                for (int j = 1; j < N; ++j) { // 도착지
                    if (j == k || i == j) continue; // 경유지와 도착지가 같거나 출발지와 도착지가 같다면 패
                    if (arr[i][k] + arr[k][j] < arr[i][j]) {
                        arr[i][j] = arr[i][k] + arr[k][j];
                    }
                }
            }
        }
    }

    static int shortestDistance(int s, int a, int b) {
        int answer = Integer.MAX_VALUE ;
        for (int i = 1; i < N; i++) {
            int tmp = arr[s][i] + arr[i][a] + arr[i][b];
            answer = Math.min(answer, tmp);
        }

        return answer;
    }

    public int solution(int n, int s, int a, int b, int[][] fares) {
        N = n+1;
        arr = new int[n + 1][n + 1];
        for(int i = 0; i < N; i++) Arrays.fill(arr[i], INF);

        for (int i = 1; i < n + 1; i++) {
            arr[i][i] = 0;
        }

        for (int i = 0; i < fares.length; i++) {
            int x = fares[i][0];
            int y = fares[i][1];
            int z = fares[i][2];
            arr[x][y] = z;
            arr[y][x] = z;
        }

        FolydTest();
        return shortestDistance(s, a, b);
    }



    public static void main(String[] args) {
        Solution T = new Solution();

        int n = 6;
        int s = 4;
        int a = 6;
        int b = 2;

        int[][] fares = {{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}
                , {2, 3, 22}, {1, 6, 25}};
        T.solution(n, s, a, b, fares);

    }
}
