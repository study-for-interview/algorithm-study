package 그래프.탐색.G5_인구이동;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solution(l, r, map));
    }

    public static class Node {
        public int x;
        public int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static final int[] dx = { -1, 0, 1, 0 };
    public static final int[] dy = { 0, 1, 0, -1 };
    public static int N;
    public static int L;
    public static int R;

    public static int solution(int l, int r, int[][] map) {
        N = map.length;
        L = l;
        R = r;
        int year = 0;

        while (true) {
            boolean[][] isVisited = new boolean[N][N];
            boolean hasUnion = false;

            for (int x = 0; x < N; x++) {
                for (int y = 0; y < N; y++) {
                    if (!isVisited[x][y]) {
                        if (bfs(new Node(x, y), map, isVisited)) {
                            hasUnion = true;
                        }
                    }
                }
            }

            if (!hasUnion) {
                break;
            }
            year++;
        }

        return year;
    }

    public static boolean bfs(Node start, int[][] map, boolean[][] isVisited) {

        int unionSize = 0;
        int populationSum = 0;
        ArrayList<Node> visited = new ArrayList<>();

        Queue<Node> queue = new LinkedList<>();
        queue.offer(start);
        isVisited[start.x][start.y] = true;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            unionSize++;
            populationSum += map[cur.x][cur.y];
            visited.add(cur);

            for (int i = 0; i < 4; i++) {
                Node next = new Node(cur.x + dx[i], cur.y + dy[i]);

                if (next.x < 0 || next.x >= N || next.y < 0 || next.y >= N) {
                    continue;
                }

                int diff = Math.abs(map[cur.x][cur.y] - map[next.x][next.y]);
                if (!isVisited[next.x][next.y] && L <= diff && diff <= R) {
                    isVisited[next.x][next.y] = true;
                    queue.offer(next);
                }
            }
        }

        if (unionSize == 1) {
            return false;
        }

        int unionAvg = populationSum / unionSize;
        for (Node node : visited) {
            map[node.x][node.y] = unionAvg;
        }
        return true;
    }

}

/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/16234
 * 성공여부 : 실패 (1시간초과)
 * 풀이시간 : 1h 30m
 * 
 * 시간복잡도 : ??
 * 메모리 : 297184 KB
 * 소요 시간 : 608 ms
 * ================================================================================
 * 
 * 시뮬레이션이 매우 빡셌던 문제..
 * 일단 문제 이해부터 잘못해서 삽질한번 했다. (Try1)
 * 
 * 배열을 그래프 탐색하는 것은 적록색약과 비슷
 * 
 * 1. 각 원소를 완전탐색하면서 방문하지 않은 원소를 시작점으로 잡음 
 * 2. 해당 시작점에서 bfs 탐색 -> 연합으로 묶이는 녀석들의 인구수를 업데이트
 * 
 */