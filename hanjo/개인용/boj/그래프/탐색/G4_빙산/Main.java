package 그래프.탐색.G4_빙산;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solution(n, m, map));
    }

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

    public static int solution(int n, int m, int[][] map){

        int[][] temp = new int[n][m];
        boolean[][] isVisited = new boolean[n][m];

        int year = 0;

        while(!isMelted(n, m, map)){
            year++;
            // 녹이기
            for(int i=1; i<n-1; i++){
                for(int j=1; j<m-1; j++){
                    if(map[i][j] == 0){
                        continue;
                    }
                    int sea = 0;
                    for(int k=0; k<4; k++){
                        if(map[i+dx[k]][j+dy[k]] == 0){
                            sea++;
                        }
                    }
                    temp[i][j] = Math.max(0, map[i][j] - sea);
                }
            }
            // 복사
            for(int i=1; i<n-1; i++){
                for(int j=1; j<m-1; j++){
                    map[i][j] = temp[i][j];
                }
            }
            // 첫번째 얼음부터 bfs
            for(int i=0; i<n; i++){
                Arrays.fill(isVisited[i], false);
            }
            Loop : for(int i=1; i<n-1; i++){
                for(int j=1; j<m-1; j++){
                    if(map[i][j] != 0){
                        bfs(new Node(i, j), map, isVisited);
                        break Loop;
                    }
                }
            }
            // 만약 방문하지 않은 얼음이 하나라도 있다면 끝
            for(int i=1; i<n-1; i++){
                for(int j=1; j<m-1; j++){
                    if(map[i][j] != 0 && !isVisited[i][j]){
                        return year;
                    }
                }
            }
        }
        return 0;
    }

    public static boolean isMelted(int n, int m, int[][] map){
        for(int[] row : map){
            for(int num : row){
                if(num != 0){
                    return false;
                }
            }
        }
        return true;
    }

    public static void bfs(Node start, int[][] map, boolean[][] isVisited){
        
        Queue<Node> queue = new LinkedList<>();
        queue.offer(start);
        isVisited[start.x][start.y] = true;

        while(!queue.isEmpty()){
            Node cur = queue.poll();
            
            for(int i=0; i<4; i++){
                Node next = new Node(cur.x + dx[i], cur.y + dy[i]);

                if(!isVisited[next.x][next.y] && map[next.x][next.y] != 0){
                    queue.offer(next);
                    isVisited[next.x][next.y] = true;
                }
            }
        }
    }

}

/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/2573
 * 성공여부 : 성공
 * 풀이시간 : 1h
 * 
 * 시간복잡도 : ??  / bfs는 O(V+E)
 * 메모리 : 214280 KB
 * 소요 시간 : 692 ms
 * ================================================================================
 * 
 * 적록색약이랑 비슷한데 좀 더 까다로운 문제.
 * 배열을 그래프탐색하는 부분이 매우 비슷하다. (dx, dy 방향 배열을 사용)
 * 
 * 그래프탐색 자체는 별다를게 없지만, 탐색 전후로 배열을 계속 바꿔줘야해서 빡셌음
 * 이번에는 bfs로 풀었다. (dfs도 상관없음)
 * 
 * 녹이고 난 후 bfs의 시작정점(얼음)은 아무데나 잡아도 상관없음. 
 * 하지만 가장 먼저 등장하는 얼음으로 정했는데 여기서 이중for문 break가 필요했다.
 * for문에 별명을 붙여서 break 지점을 선택할 수 있다는 것을 알게되었음..
 * 
 */