package baekjoon;

import java.io.*;
import java.util.*;

public class BJ_17298 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Stack<Integer> stack = new Stack();
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arrays = new int[n];
        for (int i = 0; i < n; i++) {
            arrays[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arrays[i] > arrays[stack.peek()]) {
                arrays[stack.pop()] = arrays[i];
            }
            stack.push(i);
        }

        while(!stack.isEmpty()) {
            arrays[stack.pop()] = -1;
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            sb.append(arrays[i]).append(' ');
        }

        System.out.println(sb);
        // bw.write(sb.toString() + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

}
