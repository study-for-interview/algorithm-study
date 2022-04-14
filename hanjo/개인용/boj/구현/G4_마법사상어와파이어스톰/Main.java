package 구현.G4_마법사상어와파이어스톰;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        
        n = (int)Math.pow(2, n);
        int[][] map = new int[n][n];
        for(int x=0; x<n; x++){
            st = new StringTokenizer(br.readLine());
            for(int y=0; y<n; y++){
                map[x][y] = Integer.parseInt(st.nextToken());
            }
        }

        int[] steps = new int[q];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<q; i++){
            steps[i] = Integer.parseInt(st.nextToken());
        }
        
        new Solution().solution(map, steps);
    }

    public static class Node {
        public int x;
        public int y;

        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static class Solution {

        public final int[] dx = {-1, 0, 1, 0};
        public final int[] dy = {0, 1, 0, -1};

        public int[][] map;
        public int n;
        public boolean[][] isVisited;
        
        public void solution(int[][] map, int[] steps){
            this.map = map;
            n = map.length;

            for(int l : steps){
                // 격자들을 90도 회전
                int len = (int)Math.pow(2, l);
                for(int x=0; x<n; x+=len){
                    for(int y=0; y<n; y+=len){
                        rotate(new Node(x, y), len);
                    }
                }
                // 녹여야 하는 대상 찾기
                boolean[][] target = new boolean[n][n];
                for(int x=0; x<n; x++){
                    for(int y=0; y<n; y++){
                        target[x][y] = isTarget(new Node(x, y));
                    }
                }
                // 한번에 녹이기
                for(int x=0; x<n; x++){
                    for(int y=0; y<n; y++){
                        if(target[x][y]){
                            map[x][y] -= 1;
                        }
                    }
                }
            }
            // 모든 합 구하기 + 덩어리 찾기
            isVisited = new boolean[n][n];
            int sum = 0;
            int max = 0;
            for(int x=0; x<n; x++){
                for(int y=0; y<n; y++){
                    sum += map[x][y];
                    if(!isVisited[x][y] && map[x][y] != 0){
                        max = Math.max(max, bfs(new Node(x, y)));
                    }
                }
            }

            System.out.println(sum);
            System.out.println(max);
        }


        public void rotate(Node start, int len){
            int[][] temp = new int[len][len];
            for(int x=0; x<len; x++){
                for(int y=0; y<len; y++){
                    temp[y][(len-1)-x] = map[x+start.x][y+start.y];
                }
            }
            for(int x=0; x<len; x++){
                for(int y=0; y<len; y++){
                    map[x+start.x][y+start.y] = temp[x][y];
                }
            }
        }

        public boolean isTarget(Node cur){
            int iceCount = 0;
            for(int i=0; i<4; i++){
                Node next = new Node(cur.x + dx[i], cur.y + dy[i]);
                if(!canGo(next)){
                    continue;
                }
                iceCount++;
            }
            if(iceCount < 3 && map[cur.x][cur.y] != 0){
                return true; 
            }
            return false;
        }

        public int bfs(Node start){
            int count = 0;

            Queue<Node> queue = new LinkedList<>();
            isVisited[start.x][start.y] = true;
            count++;
            queue.offer(start);

            while(!queue.isEmpty()){
                Node cur = queue.poll();
                for(int i=0; i<4; i++){
                    Node next = new Node(cur.x + dx[i], cur.y + dy[i]);
                    if(!canGo(next)){
                        continue;
                    }
                    if(!isVisited[next.x][next.y]){
                        isVisited[next.x][next.y] = true;
                        count++;
                        queue.offer(next);
                    }
                }
            }
            return count;
        }

        public boolean canGo(Node next){
            if(next.x < 0 || next.x >= n || next.y < 0 || next.y >=n){
                return false;
            }
            if(map[next.x][next.y] == 0){
                return false;
            }
            return true;
        }
    }
    
}


/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/20058
 * 날짜 : 220404
 * 성공여부 : 실패 (시간초과뜸)
 * 풀이시간 : 3h
 * 
 * 시간복잡도 : 
 * 메모리 : 112528 KB
 * 소요 시간 : 584 ms
 * ================================================================================
 * 
 * 배열 돌리기 구현 + bfs 문제
 * 역대급으로 함정이 많은 문제였음 (그리고 시간초과 조건도 개빡셈)
 * 
 * 구현 흐름 자체는 단순하다.
 * 
 * 1. 주어진 크기대로 격자들을 90도 회전
 *      -> 90도 회전시키기는 규칙이 따로 있었음 행을 열 끝쪽으로 순서대로 붙여버리면 됨
 *      -> 근데 여기서 temp 배열을 n*n으로 선언했다가 시간초과 났었음
 * 
 * 2. 얼음 녹이기
 *      -> 여기서 함정이 존재함 하나의 얼음을 녹인 결과가 다음 얼음에 영향을 주면 안됨
 *      -> 따라서 녹일 얼음 좌표를 모두 모아놓고 한번에 녹여야 함
 * 
 * ---> 반복
 * 
 * 3. 결과 map을 가지고 얼음덩어리를 bfs 탐색
 * 
 * 결론은 배열 복사를 남발했다가 시간초과가 계속 떠서.... 진짜 삽질 너무 많이함..
 * 
 */