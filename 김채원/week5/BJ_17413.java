package baekjoon;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ_17413 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        Stack<Character> stack=new Stack<>();

        StringBuilder sb = new StringBuilder();
        boolean check = false;
        for (char x : s.toCharArray()) {

            if (x == '<') {
                while (!stack.isEmpty()) {
                    sb.append(stack.pop());
                }
                check = true;
            }

            if (x == '>') {
                check = false;
            }

            if (check) {
                sb.append(x);
            }

            if (!check) {
                if(x =='>') sb.append(x);
                else if (x == ' ') {
                    while (!stack.isEmpty()) {
                        sb.append(stack.pop());
                    }
                    sb.append(x);
                }else stack.push(x);
            }

        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        bw.write(sb.toString() + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

}