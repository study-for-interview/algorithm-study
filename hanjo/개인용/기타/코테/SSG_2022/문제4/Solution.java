package 코테.SSG_2022.문제4;

import java.util.*;

class Solution {

    public static class Node {
        public int x;
        public int y;

        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static final int N = 6;
    public static final int dx[] = {-1, 1, 0, 0};
    public static final int dy[] = {0, 0, -1, 1};

    public int[][] map;
    public boolean[][] isVisited;

    public String[] solution(int[][] macaron) {

        map = new int[N][N];

        for(int[] m : macaron) {
            int col = m[0]-1;
            int color = m[1];

            // 쌓기
            for(int x=0; x<=N; x++){
                if(x == N || map[x][col] != 0){
                    map[x-1][col] = color;
                    break;
                }
            }

            while(true){
                // bfs로 터뜨리기
                isVisited = new boolean[N][N];
                boolean isPop = false;
                for(int x=0; x<N; x++){
                    for(int y=0; y<N; y++){
                        if(isVisited[x][y] || map[x][y] == 0){
                            continue;
                        }
                        if(bfs(x, y)){
                            isPop = true;
                        }
                    }
                }
                // 배열 업데이트
                if(isPop){
                    shiftMap();
                }
                else{
                    break;
                }
            }
        }

        String[] answer = new String[N];
        for(int x=0; x<N; x++){
            StringBuilder sb = new StringBuilder();
            for(int y=0; y<N; y++){
                sb.append(map[x][y]);
            }
            answer[x] = sb.toString();
        }
        return answer;
    }

    public boolean bfs(int x, int y){

        int color = map[x][y];
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(x, y));
        isVisited[x][y] = true;

        List<Node> target = new ArrayList<>();

        while(!queue.isEmpty()){
            Node cur = queue.poll();
            target.add(cur);


            for(int i=0; i<4; i++){
                Node next = new Node(cur.x + dx[i], cur.y + dy[i]);

                if(isOut(next.x, next.y)){
                    continue;
                }
                if(map[next.x][next.y] != color || map[next.x][next.y] == 0){
                    continue;
                }
                if(isVisited[next.x][next.y]){
                    continue;
                }

                isVisited[next.x][next.y] = true;
                queue.offer(next);
            }
        }

        if(target.size() < 3){
            return false;
        }
        else{
            for(Node node : target){
                map[node.x][node.y] = 0;
            }
            return true;
        }
    }

    public boolean isOut(int x, int y){
        return x < 0 || x >= N || y < 0 || y >= N;
    }

    public void shiftMap(){
        List<Queue<Integer>> queues = new ArrayList<>();
        for(int i=0; i<N; i++){
            queues.add(new LinkedList<>());
        }
        for(int y=0; y<N; y++){
            for(int x=N-1; x>=0; x--){
                if(map[x][y] != 0){
                    queues.get(y).offer(map[x][y]);
                }
            }
        }
        map = new int[N][N];
        for(int y=0; y<N; y++){
            for(int x=N-1; x>=0; x--){
                if(queues.get(y).isEmpty()){
                    break;
                }
                map[x][y] = queues.get(y).poll();
            }
        }
    }
}