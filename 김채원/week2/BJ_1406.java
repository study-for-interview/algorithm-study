
import java.io.*;
import java.util.Scanner;
import java.util.Stack;


public class BJ_1406 {
    public static int n;
    public static Stack<Character> RightStack;
    public static Stack<Character> LeftStack;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String s = br.readLine();
        n = Integer.parseInt(br.readLine());

        RightStack = new Stack<Character>();
        LeftStack = new Stack<Character>();

        for (char x : s.toCharArray()) {
            LeftStack.push(x);
        }

        for (int i = 0; i < n; i++) {
            String command = br.readLine();
            char c = command.charAt(0);
            if (c == 'P') {
                LeftStack.push(command.charAt(2));
            }
            if (c == 'D') {
                if(!RightStack.isEmpty()){
                    LeftStack.push(RightStack.pop());
                }
            }
            if (c == 'L') {
                if(!LeftStack.isEmpty()){
                    RightStack.push(LeftStack.pop());
                }
            }
            if (c == 'B') {
                if(!LeftStack.isEmpty()){
                    LeftStack.pop();
                }
            }
        }

        while (!LeftStack.isEmpty()) {
            RightStack.push(LeftStack.pop());
        }

        while (!RightStack.isEmpty()) {
            bw.write(RightStack.pop());
        }

        bw.flush();
        bw.close();
    }
}
