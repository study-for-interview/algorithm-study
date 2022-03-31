import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class BOJ_16120 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] target = bufferedReader.readLine().split("");

        Stack<String> stack = new Stack<>();
        for (int i = 0; i < target.length; i++) {
            if (target[i].equals("P")) {
                stack.add("P");
                continue;
            }

            if (stack.size() >= 2 && i < target.length - 1 && target[i + 1].equals("P")) {
                stack.pop();
                stack.pop();
            } else {
                System.out.println("NP");
                return;
            }
        }

        if (stack.size() == 1) {
            System.out.println("PPAP");
        } else {
            System.out.println("NP");
        }
    }
}
