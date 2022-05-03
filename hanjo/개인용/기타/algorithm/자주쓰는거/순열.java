package algorithm.자주쓰는거;

import java.util.*;

public class 순열 {

    static int n;
    static boolean[] isVisited;
    static int[] order;

    public static void main(String[] agrs){
        n = 3;
        isVisited = new boolean[n];
        order = new int[n];
        dfs(0);
    }

    public static void dfs(int depth){
        if(depth == n){
            System.out.println(Arrays.toString(order));
            return;
        }
        
        for(int i=0; i<n; i++){
            if(isVisited[i]){
                continue;
            }
            isVisited[i] = true;
            order[depth] = i;
            dfs(depth+1);
            order[depth] = 0;
            isVisited[i] = false;
        }
    }
}
