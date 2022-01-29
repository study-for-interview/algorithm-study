package Programmers;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://programmers.co.kr/learn/courses/30/lessons/42587?language=java 스택/큐 프린터
 */
public class Solution16 {

    public static void main(String[] args) {
        Solution16 s = new Solution16();
        System.out.println(s.solution(new int[]{1, 1, 9, 1, 1, 1}, 0));
    }

    public int solution(int[] priorities, int location) {
        int answer = 0;
        Queue<Node> queue = new LinkedList<>();

        for (int i = 0; i < priorities.length; i++) {
            queue.add(new Node(priorities[i], i));
        }

        int count = 0;

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            boolean flag = false;
            for (Node q : queue) {
                if (node.getPriority() < q.getPriority()) {
                    flag = true;
                }
            }

            if (flag) {
                queue.add(node);
            } else {
                count++;
                if (node.getLocation() == location) {
                    answer = count;
                    break;
                }
            }
        }

        return answer;
    }
}

class Node {

    private final int priority;
    private final int location;

    public Node(int priority, int location) {
        this.priority = priority;
        this.location = location;
    }

    public int getPriority() {
        return priority;
    }

    public int getLocation() {
        return location;
    }
}
