package 카카오.L3_카드짝맞추기;

import java.util.*;

class Try1 {

    public static class Node {
        public int x;
        public int y;
        public int weight;

        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }

        public Node(int x, int y, int weight){
            this.x = x;
            this.y = y;
            this.weight = weight;
        }
    }

    public static final int[] dx = {-1, 1, 0, 0};
    public static final int[] dy = {0, 0, -1, 1};
    public static final int n = 4;

    public int[][] board;
    public Node start;

    public List<Node> cards;
    public int c;
    public boolean[] cardVisited;
    public int[] order;
    public int min = Integer.MAX_VALUE;

    public int solution(int[][] board, int r, int c) {
        this.board = board;
        start = new Node(r, c);

        // 카드 리스트 만들기
        cards = new ArrayList<>();
        for(int x=0; x<n; x++){
            for(int y=0; y<n; y++){
                if(board[x][y] != 0){
                    cards.add(new Node(x, y));
                }
            }
        }

        // 카드 순서 배열 초기화
        c = cards.size();
        cardVisited = new boolean[c];
        order = new int[c];
        
        // 순서쌍 찾기
        dfs(0);

        return min;
    }

    // public void dfs(int depth){

    //     if(depth == c){
    //         System.out.println(Arrays.toString(order));
    //         return;
    //         // // 순서쌍 안맞으면 리턴
    //         // if(!isPair()){
    //         //     return;
    //         // }
    //         // // BFS로 거리구하기
    //         // int dist = bfs(start, cards.get(order[0])) + 1;
    //         // for(int i=1; i<c-1; i++){
    //         //     Node from = cards.get(order[i]);
    //         //     Node to = cards.get(order[i+1]);
    //         //     dist += bfs(from, to) + 1;
    //         // }
    //         // min = Math.min(min, dist);
    //         // return;
    //     }
        
    //     for(int i=0; i<c; i++){
    //         if(cardVisited[i]){
    //             continue;
    //         }
    //         cardVisited[i] = true;
    //         order[depth] = i;
    //         dfs(depth+1);
    //         order[depth] = 0;
    //         cardVisited[i] = false;
    //     }
    // }

    public void dfs(int depth){
        if(depth == n){
            System.out.println(Arrays.toString(order));
            return;
        }
        
        for(int i=0; i<n; i++){
            if(cardVisited[i]){
                continue;
            }
            cardVisited[i] = true;
            order[depth] = i;
            dfs(depth+1);
            order[depth] = 0;
            cardVisited[i] = false;
        }
    }

    public boolean isPair(){
        Stack<Integer> stack = new Stack<>();
        for(int idx : order){
            Node card = cards.get(idx);
            int num = board[card.x][card.y];

            if(stack.isEmpty()){
                stack.push(num);
            }
            else{
                if(stack.peek() != num){
                    return false;
                }
                stack.pop();
            }
        }
        return true;
    }

    public int bfs(Node from, Node to){

        boolean[][] isVisited = new boolean[n][n];
        Queue<Node> queue = new LinkedList<>();
        queue.offer(from);
        isVisited[from.x][from.y] = true;

        while(!queue.isEmpty()){
            Node cur = queue.poll();

            if(cur.x == to.x && cur.y == to.y){
                return cur.weight;
            }

            for(int i=0; i<4; i++){
                // 상하좌우
                Node next = new Node(cur.x + dx[i], cur.y + dy[i], cur.weight + 1);
                if(next.x < 0 || next.x >= n || next.y < 0 || next.y >= n){
                    continue;
                }
                if(isVisited[next.x][next.y]){
                    continue;
                }
                isVisited[next.x][next.y] = true;
                queue.offer(next);

                // Ctrl + 상하좌우
                Node next2 = new Node(cur.x, cur.y, cur.weight + 1);
                while(true){
                    next2.x += dx[i];
                    next2.y += dy[i];
                    if(next2.x < 0 || next2.x >= n || next2.y < 0 || next2.y >= n){
                        break;
                    }
                    if(next2.x == 0 || next2.x == n-1 || next2.y == 0 || next2.y == n-1 || board[next2.x][next2.y] != 0){
                        if(isVisited[next2.x][next2.y]){
                            continue;
                        }
                        queue.offer(next2);
                        isVisited[next2.x][next2.y] = true;
                        break;
                    }
                }
            }
        }

        return 0;
    }

    public boolean canMove(Node next, boolean[][] isVisited){
        if(next.x < 0 || next.x >= n || next.y < 0 || next.y >= n){
            return false;
        }
        if(isVisited[next.x][next.y]){
            return false;
        }
        return true;
    }



    public static void main(String[] args) {
        // 14
        System.out.println(new Try1().solution(
            new int[][]{{1,0,0,3}, {2,0,0,0}, {0,0,0,2}, {3,0,1,0}}, 1, 0
        ));
        // // 16
        // System.out.println(new Solution().solution(
        //     new int[][]{{3,0,0,2}, {0,0,1,0}, {0,1,0,0}, {2,0,0,3}}, 0, 1
        // ));
    }
}