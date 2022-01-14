import java.io.*;
import java.util.*;

public class BJ_20154{
    static int[] alpha = {3, 2, 1,	2, 3, 3, 3, 3, 1, 1, 3, 1, 3, 3, 1, 2, 2, 2, 1, 2, 1, 1, 2, 2, 2, 1};
    static int sum = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Queue<Integer> Q=new LinkedList<>();

        String str = br.readLine();

        for(int i = 0; i < str.length(); i++) {
            Q.add(alpha[str.charAt(i) - 'A']);
        }


        while (!Q.isEmpty()) {
            if (Q.size() == 1) {
                sum += Q.poll();
                if(sum > 10) sum %= 10;
                break;
            }
            int a = Q.poll();
            int b = Q.poll();
            sum += a+b;
            if(sum > 10) sum %= 10;
        }

        if (sum % 2 == 0) {
            System.out.println("You're the winner?");
        }
        else System.out.println("I'm a winner!");

        bw.flush();
        bw.close();
        br.close();
    }

}



