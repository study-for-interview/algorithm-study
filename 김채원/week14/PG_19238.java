import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

class Node implements Comparable<Node> {
    int x;
    int y;
    int node;
    Node right;
    Node left;

    public Node(int x, int y, int node, Node right, Node left) {
        this.x = x;
        this.y = y;
        this.node = node;
        this.right = right;
        this.left = left;
    }

    @Override
    public int compareTo(Node o) {
        if (this.y == o.y) return this.x - o.x; //오름차순 <-> o.y - this.y
        else return o.y - this.y; // 내림차순
    }
}

public class Main {
    static int[][] result;
    static int idx;

    public int[][] solution(int[][] nodeinfo) {
        Node[] node = new Node[nodeinfo.length];
        ArrayList<Node> arr = new ArrayList<>();
        for (int i = 0; i < nodeinfo.length; i++) {
            arr.add(new Node(nodeinfo[i][0], nodeinfo[i][1], i + 1, null, null))
            //node[i] = new Node(nodeinfo[i][0], nodeinfo[i][1], i + 1, null, null);
        }
        Collections.sort(arr);
        for (int i = 0; i < arr.size(); i++) {
            node[i] = arr.get(i);
        }

        Node root = node[0];
        for(int i = 1; i < node.length; i++) {
            insertNode(root, node[i]);
        }

        result = new int[2][nodeinfo.length];
        idx = 0;
        preorder(root); //전위 순회
        idx = 0;
        postorder(root); //후위 순회
        return result;
    }

    public void preorder(Node root) {
        if(root != null) {
            result[0][idx++] = root.node;
            preorder(root.left);
            preorder(root.right);
        }
    }

    public void postorder(Node root) {
        if(root != null) {
            postorder(root.left);
            postorder(root.right);
            result[1][idx++] = root.node;
        }
    }

    static private void insertNode(Node parent, Node child) {
        if (parent.x > child.x) { //왼쪽으로가야함
            if (parent.left == null) parent.left = child;
            else insertNode(parent.left, child);
        } else {
            if(parent.right == null) parent.right = child;
            else insertNode(parent.right, child);
        }
    }

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] nodeinfo = {{5, 3}, {11, 5}, {13, 3}, {3, 5}, {6, 1}, {1, 3}, {8, 6}, {7, 2}, {2, 2}};
        T.solution(nodeinfo);
    }
}