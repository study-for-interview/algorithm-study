package 그래프.탐색.G5_적록색약;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        char[][] map = new char[n][n];
        for(int i=0; i<n; i++){
            String line = br.readLine();
            for(int j=0; j<n; j++){
                map[i][j] = line.charAt(j);
            }
        }

        solution(map);
    }
                                  // 상 우 하 좌
    public static final int[] dx = {-1, 0, 1, 0};
    public static final int[] dy = {0, 1, 0, -1};
    
    public static void solution(char[][] map){

        int n = map.length;
        boolean[][] isVisited = new boolean[n][n];

        // 일반인일 경우
        int count1 = 0;
        initIsVisited(isVisited);
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(!isVisited[i][j]){
                    Node start = new Node(i, j);
                    bfs(map, isVisited, start);
                    count1++;
                }
            }
        }

        // 색약일 경우
        int count2 = 0;
        initIsVisited(isVisited);
        initMapForBlind(map);
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(!isVisited[i][j]){
                    Node start = new Node(i, j);
                    bfs(map, isVisited, start);
                    count2++;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(count1).append(" ").append(count2);
        System.out.println(sb.toString());
    }

    public static class Node{
        public int x;
        public int y;

        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void initIsVisited(boolean[][] isVisited){
        for(int i=0; i<isVisited.length; i++){
            Arrays.fill(isVisited[i], false);
        }
    }

    public static void initMapForBlind(char[][] map){
        int n = map.length;
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(map[i][j] == 'G'){
                    map[i][j] = 'R';
                }
            }
        }
    }
    
    public static void bfs(char[][] map, boolean[][] isVisited, Node start){
        
        Queue<Node> queue = new LinkedList<>();
        queue.add(start);
        isVisited[start.x][start.y] = true;

        while(!queue.isEmpty()){

            Node cur = queue.poll();
            
            for(int i=0; i<4; i++){
                Node next = new Node(cur.x + dx[i], cur.y + dy[i]);
                if(next.x < 0 || next.y < 0 || next.x >= map.length || next.y >= map.length){
                    continue;
                }
                // 아직 방문 X + 현재 노드와 같은 색일 때
                if(!isVisited[next.x][next.y] && map[next.x][next.y] == map[cur.x][cur.y]){
                    queue.add(next);
                    isVisited[next.x][next.y] = true;
                }
            }
        }
    }
}

/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/10026
 * 성공여부 : 성공
 * 풀이시간 : 1H
 * 
 * 시간복잡도 : O(2*(V+E)^(N^2))
 * 메모리 : 16620 KB
 * 소요 시간 : 140 ms
 * ================================================================================
 * 
 * 완전 탐색 + 그래프 탐색 문제
 * 주어진 배열을 보고 이게 왜 그래프 문제지?라는 생각이 들었다.
 * 그래서 배열을 그래프로도 그려봤는데, 그려지긴 하지만 루트부터 시작해서 영역이 바뀔때 어떻게 구현해야할지 감이 안왔음
 * 
 * 곰곰히 생각해보니 배열 요소를 하나하나 완전탐색하면 됐다.
 * 1. 배열 요소 하나하나를 시작점으로 잡고 
 * 2. bfs를 돌려서 같은 영역일때 상하좌우로 방문하여 체크 + 영역 count 올려줌
 * 3. 다음 요소가 시작점일 때 이미 다른 영역은 방문체크가 다 되어있기에, 영역을 독립적으로 카운트 가능
 * 
 * <시행착오>
 * bfs시 큐에 삽입할때 방문체크를 하지 않고 실제 방문할때 체크했더니 메모리 초과가 뜸
 * 당연한 원리인데 까먹었다.. 꼭 정점이 큐에 삽입될때 방문체크를 해주자
 * 
 */