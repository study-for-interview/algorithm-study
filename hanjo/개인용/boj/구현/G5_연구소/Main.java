package 구현.G5_연구소;

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
        for(int x=0; x<n; x++){
            st = new StringTokenizer(br.readLine());
            for(int y=0; y<m; y++){
                map[x][y] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(new Solution().solution(map));
    }

    public static class Node {
        public int x;
        public int y;
        
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static class Solution{

        public final int[] dx = {-1, 0, 1, 0};
        public final int[] dy = {0, 1, 0, -1};
        
        public int[][] map;
        public int n;
        public int m;
        public int size;
        public List<Node> blankList;
        public List<Node> virusList;
        public boolean[] isSelected;
        public int max = 0;
    
        public int solution(int[][] map){
            this.map = map;
            n = map.length;
            m = map[0].length;

            // 빈공간 + 바이러스 좌표 리스트 초기화
            blankList = new ArrayList<>();
            virusList = new ArrayList<>();
            for(int x=0; x<n; x++){
                for(int y=0; y<m; y++){
                    if(map[x][y] == 0){
                        blankList.add(new Node(x, y));
                    }
                    if(map[x][y] == 2){
                        virusList.add(new Node(x,y));
                    }
                }
            }
            // 후보 리스트의 원소 선택 여부 배열
            size = blankList.size();
            isSelected = new boolean[size];
            // dfs
            dfs(0, 0);

            return max;
        }

        public void dfs(int depth, int start){
            if(depth == 3){
                // 선택된 벽 좌표 업데이트
                int[][] copy = copy(map);
                for(int i=0; i<size; i++){
                    if(isSelected[i]){
                        Node blank = blankList.get(i);
                        copy[blank.x][blank.y] = 1;
                    }
                }
                // 업데이트된 맵으로 바이러스 bfs
                for(Node virus : virusList){
                    bfs(virus, copy);
                }
                // 모두 퍼진 후의 map에서 안전영역 세고 -> 최대면 업데이트
                max = Math.max(max, countSafe(copy));
                return;
            }
            if(start == size){
                return;
            }
            
            // 백트래킹으로 조합 구하기
            for(int i=start; i<size; i++){
                isSelected[i] = true;
                dfs(depth+1, i+1);
                isSelected[i] = false;
            }
        }

        public void bfs(Node start, int[][] copy){
            Queue<Node> queue = new LinkedList<>();
            queue.offer(start);

            while(!queue.isEmpty()){
                Node cur = queue.poll();
                for(int i=0; i<4; i++){
                    Node next = new Node(cur.x + dx[i], cur.y + dy[i]);
                    if(next.x < 0 || next.x >= n || next.y < 0 || next.y >= m){
                        continue;
                    }
                    if(copy[next.x][next.y] == 0){
                        copy[next.x][next.y] = 2;
                        queue.offer(next);
                    }
                }
            }
        }

        public int countSafe(int[][] copy){
            int count = 0;
            for(int x=0; x<n; x++){
                for(int y=0; y<m; y++){
                    if(copy[x][y] == 0){
                        count++;
                    }
                }
            }
            return count;
        }

        public int[][] copy(int[][] origin){
            int[][] copy = new int[n][m];
            for(int x=0; x<n; x++){
                for(int y=0; y<m; y++){
                    copy[x][y] = origin[x][y];
                }
            }
            return copy;
        }
    }
    
}


/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/14502
 * 날짜 : 220331
 * 성공여부 : 성공
 * 풀이시간 : 2h
 * 
 * 시간복잡도 : 
 * 메모리 : 292756 KB
 * 소요 시간 : 608 ms
 * ================================================================================
 * 
 * 구현 + dfs(조합 백트래킹) + bfs 종합선물세트 문제;
 * 
 * 좌표별 조합을 모두 완전탐색하는 것은 청소년상어나 치킨배달 문제와 비슷했음
 * 
 * 다른사람들에 비해 메모리나 소요시간 효율이 잘 나오지 않았는데, 
 * 좌표를 인스턴스로 저장한거나 bfs를 사용해서 그런듯..
 * 
 * 구현문제에서 풀이시간을 줄여야할 필요 있음
 * 
 */