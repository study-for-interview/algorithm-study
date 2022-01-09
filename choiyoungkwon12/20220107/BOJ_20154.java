package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

/**
 * https://www.acmicpc.net/problem/20154
 */

public class BOJ_20154 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        int[] num = new int[]{3, 2, 1, 2, 3, 3, 3, 3, 1, 1, 3, 1, 3, 3, 1, 2, 2, 2, 1, 2, 1, 1, 2, 2, 2, 1};

        Deque<Integer> queue = new LinkedList<>();

        for (int i = 0; i < str.length(); i++) {
            int number = num[str.charAt(i) - 'A'];
            queue.add(number);
        }

        while (queue.size() >= 2) {
            // 큐에 있는거 2개 빼서 더한 후 다시 큐에 넣기 반복
            Integer pop = queue.pop();
            Integer pop1 = queue.pop();
            int sum = pop + pop1;
            if (sum >= 10) {
                sum %= 10;
            }
            queue.push(sum);
        }

        Integer result = queue.pop();

        if (result % 2 == 0){
            System.out.println("You're the winner?");
        }else{
            System.out.println("I'm a winner!");
        }
    }
}
