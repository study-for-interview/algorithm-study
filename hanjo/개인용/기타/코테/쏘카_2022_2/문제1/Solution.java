package 코테.쏘카_2022_2.문제1;

import java.util.*;

class Solution {

    public static class Node {
        public int num;
        public int weight;

        public Node(int num, int weight){
            this.num = num;
            this.weight = weight;
        }
    }

    public int[] solution(int n, int k, int[][] roads) {
        // 인접리스트 초기화
        HashMap<Integer, List<Node>> graph = new HashMap<>();
        for(int i=0; i<n; i++){
            graph.put(i, new ArrayList<>());
        }
        for(int[] road : roads){
            graph.get(road[0]).add(new Node(road[1], road[2]));
            graph.get(road[1]).add(new Node(road[0], road[2]));
        }

        // bfs
        TreeSet<Integer> destination = new TreeSet<>();
        bfs(k, graph, destination);

        // 결과 반환
        if(destination.isEmpty()){
            destination.add(-1);
        }
        return destination.stream()
                .mapToInt(v -> v).toArray();
    }

    public void bfs(int k, Map<Integer, List<Node>> graph, TreeSet<Integer> destination){
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(0, 0));

        while(!queue.isEmpty()){
            Node cur = queue.poll();
            // 대여시간 도달시
            if(cur.weight >= k){
                if(cur.weight == k){
                    destination.add(cur.num);
                }
                continue;
            }
            // 큐에 다음 정점 삽입
            for(Node connect : graph.get(cur.num)){
                Node next = new Node(connect.num, connect.weight + cur.weight);
                queue.offer(next);
            }
        }
    }

}

// 50, 나머지 시간초과