package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/9934 완전 이진 트리
 */
public class BOJ_9934 {

    static List<Integer> nodes;
    static StringBuilder[] sb;
    static int[] node;
    static int k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        double nodeAmount = Math.pow(2, k) - 1;

        StringTokenizer st = new StringTokenizer(br.readLine());

        node = new int[(int) nodeAmount];
        for (int i = 0; i < nodeAmount; i++) {
            node[i] = Integer.parseInt(st.nextToken());
        }

        sb = new StringBuilder[k];

        for (int i = 0; i < sb.length; i++) {
            sb[i] = new StringBuilder();
        }

        solve(0, node.length - 1, 0);

        for (int i = 0; i < sb.length; i++) {
            System.out.println(sb[i]);
        }
    }

    private static void solve(int s, int e, int depth) {
        if (depth == k) {
            return;
        }

        int mid = (s + e) / 2;
        sb[depth].append(node[mid]).append(" ");

        solve(s, mid - 1, depth + 1);
        solve(mid + 1, e, depth + 1);
    }
}
