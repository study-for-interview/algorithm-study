package 코테.데브매칭_2022.문제3;

import java.util.*;

class Solution {

    public int k;
    public int b;
    public int m;
    public int[][] edges;
    public boolean[] isUsed;
    public boolean[] isVisited;
    public Map<Integer, List<Integer>> graph;

    public int solution(int n, int[][] edges, int k, int a, int b) {
        this.k = k;
        this.b = b;
        this.edges = edges;
        m = edges.length;

        isUsed = new boolean[m];
        isVisited = new boolean[n];

        graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }
        for(int[] edge : edges){
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        dfs(0, a);

        int answer = 0;
        for(boolean check : isUsed){
            if(check){
                answer++;
            }
        }
        return answer;
    }

    public boolean dfs(int depth, int cur){
        if(cur == b){
            return depth <= k;
        }
        boolean flag = false;
        for(int next : graph.get(cur)){
            if(!isVisited[next]){
                isVisited[next] = true;
                if(dfs(depth+1, next)){
                    checkUsed(cur, next);
                    flag = true;
                }
                isVisited[next] = false;
            }
        }
        return flag;
    }

    public void checkUsed(int x, int y){
        for(int i=0; i<m; i++){
            if((edges[i][0] == x && edges[i][1] == y) || (edges[i][1] == x && edges[i][0] == y)){
                isUsed[i] = true;
            }
        }
    }

    public static void main(String[] args) {
        // 7
        System.out.println(new Solution().solution(
            8,
                new int[][]{
                    {0,1},{1,2},{2,3},{4,0},{5,1},{6,1},{7,2},{7,3},{4,5},{5,6},{6,7}
                },
            4, 0, 3
        ));
    }
}