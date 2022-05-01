package kakao;

import java.util.*;

public class Pms_미로탈출 {
    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        int[][] graph = new int[n + 1][n + 1];

        for(int i = 0; i < graph.length; i++){
            Arrays.fill(graph[i],3001);
        }


        for (int[] road : roads) {
            graph[road[0]][road[1]] = Math.min(graph[road[0]][road[1]], road[2]);
        }

        boolean[][] visited = new boolean[n + 1][1 << traps.length];

        Queue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(start, 0, 0));

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            int state = current.state;

            if (current.number == end) {
                return current.cost;
            }

            if (visited[current.number][current.state]) {
                continue;
            }
            visited[current.number][current.state] = true;

            boolean currentTrapped = false;
            Set<Integer> trapped = new HashSet<>();

            //현재 노드 트랩처리
            for (int i = 0; i < traps.length; i++) {
                int bit = 1 << i;

                if ((state & bit) != 0) { 
                    if (current.number == traps[i]) { 
                        state &= ~bit; // state에서 이 trap을 비활성화
                        continue;   
                    }

                    trapped.add(traps[i]);
                    continue;
                }

                if (current.number == traps[i]) {
                    state |= bit;
                    trapped.add(traps[i]);
                    currentTrapped = true;
                }
            }

            for (int i = 1; i <= n; i++) {
                if (current.number == i) {
                    continue;
                }

                boolean nextTrapped = trapped.contains(i); // 다음 이동할 노드가 trap인지 체크

                if (currentTrapped == nextTrapped) { 
                    if (graph[current.number][i] != 3001) {
                        queue.add(new Node(i, current.cost + graph[current.number][i], state));
                    }
                    continue;
                }

				// 둘 중 하나가 트랩이라면 그래프의 역방향을 적용
                if (graph[i][current.number] != 3001) {
                    queue.add(new Node(i, current.cost + graph[i][current.number], state));
                }
            }


    }
    
    return 3001;
}
}
class Node implements Comparable<Node> {
    int number;
    int cost;
    int state;

    public Node(int number, int cost, int state) {
        this.number = number;
        this.cost = cost;
        this.state = state;
    }

    @Override
    public int compareTo(Node o) {
        return this.cost - o.cost;
    }
}





