package Programmers;

import java.util.ArrayList;
import java.util.List;

/**
 * https://programmers.co.kr/learn/courses/30/lessons/92343
 * 양과 늑대
 */
public class Solution_92343 {

    static ArrayList<Integer>[] childs;
    static int[] Info;
    static int maxSheepCnt = 0;

    public static void main(String[] args) {
        Solution_92343 s = new Solution_92343();
        int solution = s.solution(new int[]{0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1},
            new int[][]{{0, 1}, {1, 2}, {1, 4}, {0, 8}, {8, 7}, {9, 10}, {9, 11}, {4, 3}, {6, 5}, {4, 6}, {8, 9}});

        System.out.println(solution);
    }
    public int solution(int[] info, int[][] edges) {
        Info = info;
        childs = new ArrayList[info.length];
        for (int[] edge : edges) {
            int parent = edge[0];
            int child = edge[1];
            if (childs[parent] == null) {
                childs[parent] = new ArrayList<>();
            }
            childs[parent].add(child);
        }
        List<Integer> list = new ArrayList<>();
        list.add(0);
        dfs(0, 0, 0, list);

        return maxSheepCnt;
    }

    private void dfs(int index, int sheepCount, int wolfCount, List<Integer> nextPos) {
        if (Info[index] == 0){
            sheepCount++;
        }else{
            wolfCount++;
        }

        if (wolfCount >= sheepCount) {
            return;
        }
        maxSheepCnt = Math.max(sheepCount, maxSheepCnt);

        List<Integer> list = new ArrayList<>(nextPos);

        list.remove(Integer.valueOf(index));
        if (childs[index] != null){
            list.addAll(childs[index]);
        }

        for (Integer next : list) {
            dfs(next,sheepCount,wolfCount,list);
        }
    }
}
