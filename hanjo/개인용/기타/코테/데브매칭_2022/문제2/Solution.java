package 코테.데브매칭_2022.문제2;

import java.util.*;

class Solution {

    public int n;
    public int m;
    public char[][] map;

    public int k = 0;
    public char[] comb;
    public char[] abc = new char[]{'a', 'b', 'c'};

    public char[][] copyMap;
    public int alphaCount;
    public int answer = 0;

    public int solution(String[] grid) {
        n = grid.length;
        m = grid[0].length();

        // char 배열로 변환
        map = new char[n][m];
        for (int x = 0; x < n; x++) {
            char[] temp = grid[x].toCharArray();
            for (int y = 0; y < m; y++) {
                map[x][y] = temp[y];
                if (temp[y] == '?') {
                    k++;
                }
            }
        }

        //
        comb = new char[k];
        dfs(0);

        return answer;
    }

    public void dfs(int depth) {
        if (depth == k) {
            copyMap = updateMap(map);
            boolean[][] isVisited = new boolean[n][m];
            int bfsCount = 0;
            for (int x = 0; x < n; x++) {
                for (int y = 0; y < m; y++) {
                    if(!isVisited[x][y]) {
                        bfs(new Node(x, y), isVisited);
                        bfsCount++;
                    }
                }
            }
            if(bfsCount == alphaCount){
                answer++;
            }
            return;
        }

        for (char alpha : abc) {
            comb[depth] = alpha;
            dfs(depth+1);
        }
    }

    public char[][] updateMap(char[][] origin) {
        char[][] copy = new char[n][m];
        int combIdx = 0;
        Set<Character> alpha = new HashSet<>();
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                if (origin[x][y] == '?') {
                    copy[x][y] = comb[combIdx];
                    combIdx++;
                } else {
                    copy[x][y] = origin[x][y];
                }
                alpha.add(copy[x][y]);
            }
        }
        alphaCount = alpha.size();
        return copy;
    }


    public static class Node {
        public int x;
        public int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public final int[] dx = {-1, 0, 1, 0};
    public final int[] dy = {0, 1, 0, -1};

    public void bfs(Node start, boolean[][] isVisited) {

        Queue<Node> queue = new LinkedList<>();
        queue.offer(start);
        char target = copyMap[start.x][start.y];
        isVisited[start.x][start.y] = true;

        while(!queue.isEmpty()){
            Node cur = queue.poll();
            for(int i=0; i<4; i++){
                Node next = new Node(cur.x + dx[i], cur.y + dy[i]);
                if(next.x < 0 || next.x >= n || next.y < 0 || next.y >= m){
                    continue;
                }
                if(isVisited[next.x][next.y]){
                    continue;
                }
                if(copyMap[next.x][next.y] != target){
                    continue;
                }
                queue.offer(next);
                isVisited[next.x][next.y] = true;
            }
        }
    }

    public static void main(String[] args) {
        // 2
        System.out.println(new Solution().solution(
            new String[]{"??b", "abc", "cc?"}
        ));
        // 0
        System.out.println(new Solution().solution(
            new String[]{"abcabcab","????????"}
        ));
        // 3
        System.out.println(new Solution().solution(
            new String[]{"aa?"}
        ));

    }

}