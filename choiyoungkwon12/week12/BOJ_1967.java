package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1967 트리의 지름
 */
public class BOJ_1967 {

    static int n;
    static ArrayList<Node>[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        arr = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken()) - 1;
            int child = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());
            arr[parent].add(new Node(child, weight));
            arr[child].add(new Node(parent, weight));
        }

        String[] temp = search(new Node(0, 0)).split(" ");
        String[] result = search(new Node(Integer.parseInt(temp[0]), 1)).split(" ");

        System.out.println(result[1]);
    }

    private static String search(Node node) {
        Queue<Node> q = new LinkedList<>();
        boolean visited[] = new boolean[n];
        int cost[] = new int[n];

        q.offer(node);
        visited[node.n] = true;

        while (!q.isEmpty()) {
            Node currentNode = q.poll();
            for (Node n : arr[currentNode.n]) {
                if (!visited[n.n]) {
                    visited[n.n] = true;
                    cost[n.n] += cost[currentNode.n] + n.w;
                    q.offer(n);
                }
            }
        }

        int maxIndex = 0;
        int max = 0;

        for (int i = 0; i < n; i++) {
            if (max < cost[i]) {
                max = cost[i];
                maxIndex = i;
            }
        }

        return maxIndex + " " + max;

    }

    static class Node {

        int n;
        int w;

        public Node(int n, int w) {
            this.n = n;
            this.w = w;
        }
    }
}
