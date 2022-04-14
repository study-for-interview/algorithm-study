
import java.util.*;
import java.io.*;

public class Pms_길찾기게임 {
    
    static class Node implements Comparable<Node>{
        int x;
        int y;
        Node left;
        Node right;
        int value;

        public Node(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }

        public void setLeft(Node left){
            this.left = left;
        }

        public void setRight(Node right){
            this.right = right;
        }

        @Override
        public int compareTo(Node n) {
            return n.y - this.y;
        }

    }

    static int[][] ans;
    static int idx;

    public int[][] solution(int[][] nodeinfo) {

        Node[] nodes = new Node[nodeinfo.length];
        ans = new int[2][nodeinfo.length];

        for(int i = 0; i < nodeinfo.length; i++) {
            nodes[i] = new Node(nodeinfo[i][0], nodeinfo[i][1], i + 1);
        }

        Arrays.sort(nodes);
        Node root = nodes[0];

        for(int i=0;i<nodes.length;i++){
            setTree(root, nodes[i]);
        }
        
        idx = 0 ;
        preOrder(root);
        idx =0;
        postOrder(root);

        return ans;
    }

    public void setTree(Node parent, Node child) {
        if(parent.x > child.x) {
            if (parent.left == null ) {
                parent.setLeft(child);
            } else {
                setTree(parent.left, child);
            }
        } else {
            if(parent.right==null){
                parent.setRight(child);
            } else setTree(parent.right, child);
        }
    }

    public void preOrder(Node node) {
        if(node != null) {
			ans[0][idx++] = node.value;
			preOrder(node.left);
			preOrder(node.right);
		}
	}

    private void postOrder(Node node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            ans[1][idx++] = node.value;
        }
    }
        
}

// 0. node class 1. 노드 정렬 (y기준) 2. 트리 만들기 3. 전위 -> 후위 순회
