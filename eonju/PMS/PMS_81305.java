import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Solution {

    private static HashMap<Integer, List<Integer>> graph;
    private static int root;

    public int solution(int k, int[] num, int[][] links) {
        graph = new HashMap<>();
    }

    public static int dfs(){

    }

    public static void makeGraph(int[][] links) {
        for (int i = 0; i < links.length; i++) {
            int[] link = links[i];

            int left = link[0];
            int right = link[1];

            graph.put(i, new ArrayList<>());
            if (left != -1) {
                graph.get(i).add(left);
            }
            if (right != -1) {
                graph.get(i).add(right);
            }

        }
    }

    static class Node {

        int number;
        int left;
        int right;
        int leftSum;
        int rightSum;

        public Node(int number, int left, int right) {
            this.number = number;
            this.left = left;
            this.right = right;
        }
    }
}
