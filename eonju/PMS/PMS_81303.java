import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

class Solution {

    private static LinkedList<Node> nodes;
    private static int now;
    private static Stack<Node> deleted;

    public static String solution(int n, int k, String[] cmd) {
        nodes = new LinkedList<>();
        deleted = new Stack<>();
        now = k;

        for (int i = 0; i < n; i++) {
            nodes.add(new Node(i));
        }

        for (int i = 0; i < cmd.length; i++) {
            String command = cmd[i];
            if (command.contains("U")) {
                String[] input = command.split(" ");
                int x = Integer.parseInt(input[1]);
                up(x);
            } else if (command.contains("D")) {
                String[] input = command.split(" ");
                int x = Integer.parseInt(input[1]);
                down(x);
            } else if (command.equals("C")) {
                delete();
            } else {
                reset();
            }
        }

        String[] answer = new String[n];
        Arrays.fill(answer, "X");
        for (int i = 0; i < nodes.size(); i++) {
            Node node = nodes.get(i);
            answer[node.number] = "O";
        }

        return String.join("", answer);
    }

    public static void up(int x) {
        if (now - x < 0) {
            now = 0;
        } else {
            now -= x;
        }
    }

    public static void down(int x) {
        if (now + x >= nodes.size()) {
            now = nodes.size() - 1;
        } else {
            now += x;
        }
    }

    public static void delete() {
        if (nodes.size() - 1 == now) {
            Node removed = nodes.remove(now);
            removed.deleted(now);
            deleted.push(removed);
            now--;
        } else {
            Node removed = nodes.remove(now);
            removed.deleted(now);
            deleted.push(removed);
        }
    }

    public static void reset() {
        Node node = deleted.pop();
        nodes.add(node.deletedIdx, new Node(node.number));
        if (now >= node.deletedIdx) {
            now++;
        }
    }


    static class Node {

        int deletedIdx;
        int number;

        public Node(int number) {
            this.number = number;
            this.deletedIdx = -1;
        }

        public void deleted(int idx) {
            deletedIdx = idx;
        }
    }
}
