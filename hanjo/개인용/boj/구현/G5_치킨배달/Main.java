package 구현.G5_치킨배달;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());   // 최대 치킨집 수
        
        int[][] map = new int[n][n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(new Solution().solution(n, m, map));
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

        public int n;
        public int m;
        public int size;
        public List<Node> candidates;
        public boolean[] isVisited;
        public List<List<Integer>> combs;

        public int solution(int n, int m, int[][] map){
            this.n = n;
            this.m = m;
            // 후보 좌표 찾기
            candidates = new ArrayList<>();
            for(int x=0; x<n; x++){
                for(int y=0; y<n; y++){
                    if(map[x][y] == 2){
                        candidates.add(new Node(x, y));
                    }
                }
            }

            // 모든 조합 찾기
            size = candidates.size();
            isVisited = new boolean[size];
            combs = new ArrayList<>();
            combination(0, 0);
            
            // 조합을 순회
            int min = Integer.MAX_VALUE;
            for(var comb : combs){
                // 도시의 치킨거리
                int cityDist = 0;
                // 집마다 순회
                for(int x=0; x<n; x++){
                    for(int y=0; y<n; y++){
                        if(map[x][y] == 1){
                            // 해당 집의 치킨거리
                            cityDist += getHomeDist(x, y, comb);
                        }
                    }
                }
                min = Math.min(min, cityDist);
            }
            return min;
        }
    
        public void combination(int depth, int r){
            if(r == m){
                ArrayList<Integer> route = new ArrayList<>();
                for(int i=0; i<size; i++){
                    if(isVisited[i]){
                        route.add(i);
                    }
                }
                combs.add(route);
                return;
            }
            if(depth == size){
                return;
            }

            isVisited[depth] = true;
            combination(depth+1, r+1);

            isVisited[depth] = false;
            combination(depth+1, r);
        }

        public int getHomeDist(int x, int y, List<Integer> comb){
            int min = Integer.MAX_VALUE;
            for(int idx : comb){
                Node chiken = candidates.get(idx);
                int dist = Math.abs(chiken.x - x) + Math.abs(chiken.y - y);
                min = Math.min(min, dist);
            }
            return min;
        }
    }
    
}


/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/15686
 * 날짜 : 220328
 * 성공여부 : 성공
 * 풀이시간 : 1h
 * 
 * 시간복잡도 : 
 * 메모리 : 21448 KB
 * 소요 시간 : 308 ms
 * ================================================================================
 * 
 * 구현 + 완전탐색 문제
 * 그냥 문제 조건 잘 읽고 구현하면 된다. (진짜 잘읽어야됨)
 * 
 * 조합 재귀함수 끝에서 최소 치킨거리를 계산해줘도 됐는데 그냥 모두 구분해서 했음. 코드가 길어진듯
 * 1. 치킨집 좌표를 다 뽑아서 후보리스트를 만듬
 * 2. 후보리스트의 인덱스를 가지고 조합을 만듬
 * 3. 조합 + 집들을 완전탐색하면서 도시의 치킨거리를 구함
 * 
 */