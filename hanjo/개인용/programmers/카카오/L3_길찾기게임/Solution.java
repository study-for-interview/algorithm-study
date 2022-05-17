package 카카오.L3_길찾기게임;

import java.util.*;

class Solution {

    public static class Node implements Comparable<Node>{
        public int x;
        public int y;
        public int num;
        public Node left;
        public Node right;

        public Node(int x, int y, int num){
            this.x = x;
            this.y = y;
            this.num = num;
        }

        @Override
        public int compareTo(Node o){
            if(o.y == this.y){
                return this.x - o.x;
            }
            return o.y - this.y;
        }
    }

    public int[][] solution(int[][] nodeinfo) {
        // 노드 생성후 정렬
        int n = nodeinfo.length;
        List<Node> nodes = new ArrayList<>();
        for(int i=0; i<n; i++){
            nodes.add(new Node(nodeinfo[i][0], nodeinfo[i][1], i+1));
        }
        Collections.sort(nodes);

        // 노드 연결 -> 트리 생성
        Node root = nodes.get(0);
        for(int i=1; i<n; i++){
            addNode(nodes.get(i), root);
        }

        // 전위순회
        List<Integer> preorder = new ArrayList<>();
        preorder(root, preorder);
        // 후위순회
        List<Integer> postorder = new ArrayList<>();
        postorder(root, postorder);

        int[][] answer = new int[2][n];
        for(int i=0; i<n; i++){
            answer[0][i] = preorder.get(i);
            answer[1][i] = postorder.get(i);
        }
        return answer;
    }

    public void addNode(Node down, Node up){
        // 타겟이 왼쪽에 위치
        if(down.x < up.x){
            if(up.left == null){
                up.left = down;
            }
            else{
                addNode(down, up.left);
            }
        }
        // 오른쪽에 위치
        else{
            if(up.right == null){
                up.right = down;
            }
            else{
                addNode(down, up.right);
            }
        }
    }

    public void preorder(Node cur, List<Integer> order){
        order.add(cur.num);
        if(cur.left != null){
            preorder(cur.left, order);
        }
        if(cur.right != null){
            preorder(cur.right, order);
        }
    }

    public void postorder(Node cur, List<Integer> order){
        if(cur.left != null){
            postorder(cur.left, order);
        }
        if(cur.right != null){
            postorder(cur.right, order);
        }
        order.add(cur.num);
    }

    public static void main(String[] args) {
        // [[7,4,6,9,1,8,5,2,3],[9,6,5,8,1,4,3,2,7]]
        System.out.println(Arrays.deepToString(new Solution().solution(
            new int[][]{
                {5,3},
                {11,5},
                {13,3},
                {3,5},
                {6,1},
                {1,3},
                {8,6},
                {7,2},
                {2,2}
            }
        )));
    }
}

/**
 * ================================================================================
 * 링크 : https://programmers.co.kr/learn/courses/30/lessons/42892
 * 날짜 : 220414
 * 성공여부 : 실패 (구글링함)
 * 풀이시간 : 1h
 * 
 * 시간복잡도 : 
 * 테케12 : (29.91ms, 98.3MB)
 * ================================================================================
 * 
 * 트리구현 + 트리 (하향)순회 문제
 * 
 * 1. 주어진 좌표값으로 트리를 만들어내야한다고 생각
 * 2. 좌표값 기준으로 정렬 후 이진트리를 리스트로 구현하는거까지 성공
 * 3. 정렬된 리스트를 가지고 트리를 구현하려 했으나... 생각안나서 답을봄
 *      - 나는 인접리스트를 만들어서 순회할라했는데 트리 + 하향순회라 그럴필요 X
 *      - 그냥 부모노드의 좌/우만 다 연결해주면 끝나는 거였음..
 * 3. 모든 노드끼리의 관계를 연결시켜줌 
 *      - 추가할 노드로 재귀를 돌려서 root 노드부터 아래로 비교함
 *      - x좌표를 비교해서 오른쪽인지 왼쪽인지 판단하고 부모노드될때까지 재귀
 * 4. 전위, 후외 순회해주면끝
 * 
 */