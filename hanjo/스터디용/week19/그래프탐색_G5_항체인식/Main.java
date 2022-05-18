package week19.그래프탐색_G5_항체인식;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] before = new int[n][m];
        for(int x=0; x<n; x++){
            st = new StringTokenizer(br.readLine());
            for(int y=0; y<m; y++){
                before[x][y] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] after = new int[n][m];
        for(int x=0; x<n; x++){
            st = new StringTokenizer(br.readLine());
            for(int y=0; y<m; y++){
                after[x][y] = Integer.parseInt(st.nextToken());
            }
        }
        
        System.out.println(new Solution().solution(n, m, before, after));
    }

    public static class Node {
        public int x;
        public int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static class Solution {

        public static final int[] dx = {-1, 1, 0, 0};
        public static final int[] dy = {0, 0, -1, 1};

        public int n;
        public int m;
        public int[][] before;
        public int[][] after;
        public boolean[][] isVisited;
        public boolean isFound;
        
        public String solution(int n, int m, int[][] before, int[][] after){
            this.n = n;
            this.m = m;
            this.before = before;
            this.after = after;

            isVisited = new boolean[n][m];
            isFound = false;
            for(int x=0; x<n; x++){
                for(int y=0; y<m; y++){
                    if(isVisited[x][y]){
                        continue;
                    }
                    if(!bfs(x, y)){
                        return "NO";
                    }
                }
            }
            return "YES";
        }

        public boolean bfs(int x, int y){
            int beforeNum = before[x][y];
            int afterNum = after[x][y];

            List<Node> targets = new ArrayList<>();
            Queue<Node> queue = new LinkedList<>();
            queue.offer(new Node(x, y));
            isVisited[x][y] = true;

            // before와 after의 덩어리가 일치하는지 확인
            while(!queue.isEmpty()){
                Node cur = queue.poll();
                targets.add(cur);

                for(int i=0; i<4; i++){
                    Node next = new Node(cur.x + dx[i], cur.y + dy[i]);

                    if(next.x < 0 || next.x >= n || next.y < 0 || next.y >= m){
                        continue;
                    }
                    if(before[next.x][next.y] != beforeNum){
                        continue;
                    }
                    if(isVisited[next.x][next.y]){
                        continue;
                    }

                    // after에서 일치하지 않으면 false
                    if(after[next.x][next.y] != afterNum){
                        return false;
                    }

                    isVisited[next.x][next.y] = true;
                    queue.offer(next);
                }
            }

            // 백신 투여가 확실한 경우
            if(beforeNum != afterNum){
                // 백신 첫 등장
                if(!isFound){
                    isFound = true;
                }
                // 이미 백신이 등장했다면
                else{
                    return false;
                }
            }
            return true;
        }
    }
    
}


/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/22352
 * 날짜 : 220519
 * 성공여부 : 성공
 * 풀이시간 : 2h
 * 
 * 시간복잡도 : O(V+E)
 * 메모리 : 15048 KB
 * 소요 시간 : 144 ms
 * ================================================================================
 * 
 * 단순 bfs 문제
 * 두개의 map 2차원 배열을 한번의 bfs로 검사하는것이 특징
 * 
 * 백신은 단 한곳에만 투여한다는 조건이 제대로 써있지 않아서 매우 헤맴 ;
 * (백신을 여러곳에 놓을 수 있다고 생각해서 set으로 중복검사하는 삽질..)
 * 
 */