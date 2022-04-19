import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    static boolean[][][] visited;
    static boolean[] vi;
    static ArrayList<ArrayList<Integer>> graph;
    static int answer;

    static void dfs(int now, int[] info, int sheep, int wolf) {

        if (wolf >= sheep) {
            return;
        } else {
            answer = Math.max(answer, sheep);
        }

        for (int ob : graph.get(now)) {
            int nextSheep = sheep;
            int nextWolf = wolf;

            boolean ch = true;
            if (!vi[ob]) {
                ch = false;
                if (info[ob] == 0) {
                    nextSheep++;
                }else nextWolf++;
            }
            if(visited[ob][nextSheep][nextWolf]) continue;

            visited[ob][nextSheep][nextWolf] = true;
            vi[ob] = true;
            dfs(ob,info,nextSheep,nextWolf);
            visited[ob][nextSheep][nextWolf] = false;
            if (!ch) {
                vi[ob] = false;
            }
        }
    }

    public int solution(int[] info, int[][] edges) {
        visited = new boolean[info.length][info.length+1][info.length+1];
        vi = new boolean[info.length];
        answer = Integer.MIN_VALUE;
        graph = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < info.length; i++) {
            graph.add(new ArrayList<Integer>());
        }

        //간선 추가
        for (int i = 0; i < edges.length; i++) {
            graph.get(edges[i][0]).add(edges[i][1]);
            graph.get(edges[i][1]).add(edges[i][0]);
        }

        vi[0] = true;
        visited[0][1][0] = true;
        dfs(0, info, 1, 0);
        System.out.println(answer);
        return answer;
    }


    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int n = 437674;
//        int k = 3;

        int[] info = {0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1};
        int[][] edges = {{0, 1}, {1, 2}, {1, 4}, {0, 8}, {8, 7}, {9, 10}, {9, 11}, {4, 3}, {6, 5}, {4, 6}, {8, 9}};

        T.solution(info, edges);
    }
}