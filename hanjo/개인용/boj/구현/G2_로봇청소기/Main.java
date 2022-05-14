package 구현.G2_로봇청소기;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true){
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            if(w==0 && h==0){
                return;
            }
            char[][] map = new char[h][w];
            for(int x=0; x<h; x++){
                String row = br.readLine();
                for(int y=0; y<w; y++){
                    map[x][y] = row.charAt(y);
                }
            }
            System.out.println(new Solution().solution(map));
        }
    }

    public static class Node {
        public int x;
        public int y;
        public int weight;
        
        public Node(int x, int y, int weight){
            this.x = x;
            this.y = y;
            this.weight = weight;
        }
    }

    public static class Solution {

        public static final int[] dx = {-1, 0, 1 ,0};
        public static final int[] dy = {0, -1, 0, 1};

        public int w;
        public int h;
        public char[][] map;

        public List<Node> targets;
        public int n;
        public int[][] dist;
        public boolean[] isVisited;
        public int min;
        
        public int solution(char[][] map){
            h = map.length;
            w = map[0].length;
            this.map = map;
            
            // 거리를 구할 목표를 모두 구함
            targets = new ArrayList<>();
            for(int x=0; x<h; x++){
                for(int y=0; y<w; y++){
                    if(map[x][y] == 'o'){
                        targets.add(0, new Node(x, y, 0));
                    }
                    else if(map[x][y] == '*'){
                        targets.add(new Node(x, y, 0));
                    }
                }
            }

            // 목표간 모든 거리를 구함
            n = targets.size();
            dist = new int[n][n];
            for(int i=0; i<n; i++){
                if(!bfs(i)){
                    return -1;
                }
            }

            // 모든 방문순서 경우의 수를 탐색
            isVisited = new boolean[n];
            isVisited[0] = true;
            min = Integer.MAX_VALUE;
            dfs(0, 0, 0);
            
            return min;
        }

        public boolean bfs(int startIdx){
            Node start = targets.get(startIdx);
            boolean[][] isVisited = new boolean[h][w];
            Queue<Node> queue = new LinkedList<>();
            queue.offer(start);
            isVisited[start.x][start.y] = true;

            int count = 0;
            
            while(!queue.isEmpty()){
                Node cur = queue.poll();

                for(int i=0; i<n; i++){
                    Node target = targets.get(i);
                    if(target.x == cur.x && target.y == cur.y){
                        dist[startIdx][i] = cur.weight;
                        dist[i][startIdx] = cur.weight;
                        count++;
                        break;
                    }
                }

                for(int i=0; i<4; i++){
                    Node next = new Node(cur.x + dx[i], cur.y + dy[i], cur.weight+1);
                    if(next.x < 0 || next.x >= h || next.y < 0 || next.y >= w){
                        continue;
                    }
                    if(map[next.x][next.y] == 'x'){
                        continue;
                    }
                    if(!isVisited[next.x][next.y]){
                        isVisited[next.x][next.y] = true;
                        queue.offer(next);
                    }
                }
            }
            return count == n;
        }

        public void dfs(int depth, int sum, int from){
            if(depth == n-1){
                min = Math.min(min, sum);
                return;
            }

            for(int to=1; to<n; to++){
                if(isVisited[to]){
                    continue;
                }
                isVisited[to] = true;
                dfs(depth+1, sum + dist[from][to], to);
                isVisited[to] = false;
            }
        }
    }
    
}


/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/4991
 * 날짜 : 220420
 * 성공여부 : 실패 (구글링)
 * 풀이시간 : 2h
 * 
 * 시간복잡도 : 
 * 메모리 : 26712 KB
 * 소요 시간 : 432 ms
 * ================================================================================
 * 
 * BFS + 순열(DFS) + 구현 문제
 * 보통 DFS로 조합을 구한 후 그걸 가지고 BFS를 돌리는데 이 문제는 반대임
 * 
 * 문제를 보면 그리디하게 더러운 방을 하나씩 방문하는 방법이 딱 떠오르지만 반례가 존재한다고 함
 * (대체 어떤 반례가 있는지 모르겠음)
 * 
 * 1. 때문에 로봇+더러운방의 좌표들을 리스트에 모두 저장시키고
 * 2. 좌표끼리의 최단거리를 BFS로 구해내야함 (리스트의 인덱스로 최단거리 행렬로 표현)
 * 3. 최단거리 정보는 모두 있으니 첫번째 방문은 로봇으로 고정해놓고, 나머지 방문순서에 대한 순열을 구함
 * 
 * 비트마스킹으로 풀 수 있다고 하는데, 나중에 꼭 풀어봐야함
 * 
 */