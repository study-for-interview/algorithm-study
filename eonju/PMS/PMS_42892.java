import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

class Solution {

    private static List<Node> nodes;
    private static List<Integer> preAnswer;
    private static List<Integer> postAnswer;

    public static void main(String[] args) {
        int[][] answer = solution(
            new int[][]{{5, 3}, {11, 5}, {13, 3}, {3, 5}, {6, 1}, {1, 3}, {8, 6}, {7, 2}, {2, 2}});

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < answer[i].length; j++) {
                System.out.print(answer[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int[][] solution(int[][] nodeInfo) {
        nodes = new LinkedList<>();

        for (int i = 0; i < nodeInfo.length; i++) {
            nodes.add(new Node(i + 1, nodeInfo[i][0], nodeInfo[i][1]));
        }

        nodes.sort(Comparator.naturalOrder());
        makeTree();

        preAnswer = new LinkedList<>();
        postAnswer = new LinkedList<>();
        preOrder(nodes.get(0));
        postOrder(nodes.get(0));

        int[][] answer = new int[2][];
        answer[0] = preAnswer.stream().mapToInt(i -> i).toArray();
        answer[1] = postAnswer.stream().mapToInt(i -> i).toArray();

        return answer;
    }

    public static void makeTree() {
        Node root = nodes.get(0);

        for (int i = 1; i < nodes.size(); i++) {
            Node node = nodes.get(i);
            addNode(root, node);
        }
    }

    public static void addNode(Node root, Node node) {
        if (node.x < root.x) {
            if (root.left == null) {
                root.setLeft(node);
                return;
            } else {
                addNode(root.left, node);
            }
        } else {
            if (root.right == null) {
                root.setRight(node);
                return;
            } else {
                addNode(root.right, node);
            }
        }
    }

    public static void preOrder(Node root) { // PLR
        preAnswer.add(root.number);
        if (root.left != null) {
            preOrder(root.left);
        }
        if (root.right != null) {
            preOrder(root.right);
        }
    }

    public static void postOrder(Node root) { //LRP
        if (root.left != null) {
            postOrder(root.left);
        }
        if (root.right != null) {
            postOrder(root.right);
        }
        postAnswer.add(root.number);
    }


    static class Node implements Comparable<Node> {

        int number;
        int x;
        int y;
        Node left;
        Node right;

        public Node(int number, int x, int y) {
            this.number = number;
            this.x = x;
            this.y = y;
            left = null;
            right = null;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        @Override
        public int compareTo(Node n) {
            if (this.y == n.y) {
                return this.x - n.x;
            } else {
                return n.y - this.y;
            }
        }
    }
}
