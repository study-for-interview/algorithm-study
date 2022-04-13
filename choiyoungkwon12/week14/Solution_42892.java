package Programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://programmers.co.kr/learn/courses/30/lessons/42892
 * 길 찾기 게임
 */
public class Solution_42892 {

    static int idx;
    static int[][] result;
    public static void main(String[] args) {
        Solution_42892 s = new Solution_42892();
        int[][] solution = s.solution(
            new int[][]{{5, 3}, {11, 5}, {13, 3}, {3, 5}, {6, 1}, {1, 3}, {8, 6}, {7, 2}, {2, 2}});
        System.out.println(Arrays.deepToString(solution));
    }

    public int[][] solution(int[][] nodeinfo) {
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < nodeinfo.length; i++) {
            nodes.add(new Node(nodeinfo[i][0], nodeinfo[i][1], i + 1, null, null));
        }

        nodes.sort((o1, o2) -> {
            if (o1.y == o2.y) {
                return o1.x - o2.x;
            } else {
                return o2.y - o1.y;
            }
        });

        Node root = nodes.get(0);
        for (int i = 1; i < nodes.size(); i++) {
            insertNode(root, nodes.get(i));
        }

        result = new int[2][nodeinfo.length];
        idx = 0;
        preorder(root);
        idx = 0;
        postorder(root);

        return result;
    }

    private void postorder(Node root) {
        if (root != null){
            postorder(root.left);
            postorder(root.right);
            result[1][idx++] = root.value;
        }
    }

    private void preorder(Node root) {
        if (root != null){
            result[0][idx++] = root.value;
            preorder(root.left);
            preorder(root.right);
        }
    }

    private void insertNode(Node parent, Node child) {
        if (parent.x > child.x) {
            if (parent.left == null) {
                parent.left = child;
            } else {
                insertNode(parent.left, child);
            }
        } else {
            if (parent.right == null) {
                parent.right = child;
            } else {
                insertNode(parent.right, child);
            }
        }
    }

    public static class Node {

        int x;
        int y;
        int value;
        Node left;
        Node right;

        public Node(int x, int y, int value, Node left, Node right) {
            this.x = x;
            this.y = y;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
}
