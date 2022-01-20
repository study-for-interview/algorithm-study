package BOJ;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * https://www.acmicpc.net/problem/5639
 * 이진 검색 트리
 */

public class BOJ_5639 {
    static class Node{
        int num;
        Node left;
        Node right;

        public Node(int num) {
            this.num = num;
        }

        void insert(int n){
            if (n < this.num) {
                if (this.left == null)
                    this.left = new Node(n);
                else this.left.insert(n);
            } else {
                if (this.right == null)
                    this.right = new Node(n);
                else this.right.insert(n);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        Node root = new Node(num);

        while (true){
            String s = br.readLine();
            if (s == null || s.equals("")){
                break;
            }
            root.insert(Integer.parseInt(s));
        }

        postOrder(root);
    }

    private static void postOrder(Node node) {
        if (node == null){
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.num);
    }
}
