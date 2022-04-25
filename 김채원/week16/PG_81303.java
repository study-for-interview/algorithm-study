import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    public String solution(int n, int k, String[] cmd) {

        StringBuilder builder = new StringBuilder();
        int rowNum = n;
        Stack<Integer> deleted = new Stack<>();

        for(String tmp : cmd){
            if(tmp.length() > 1){
                System.out.println(tmp);
                int x = Integer.parseInt(tmp.substring(2));
                if(tmp.charAt(0) == 'U') k -= x;
                else k += x;
                System.out.println(k);
            } else if (tmp.charAt(0) == 'C'){
                System.out.println(tmp);

                deleted.push(k);
                System.out.println(k);
                rowNum--;
                if (rowNum == k) {
                    k--;
                    System.out.println("-----");
                    System.out.println(k);
                }

            } else {
                System.out.println(tmp);
                rowNum++;
                if(deleted.pop() <= k) k++;
                System.out.println(k);
            }
        }

        for(int i = 0 ; i < rowNum ; i++)
            builder.append("O");
        while(!deleted.empty())
            builder.insert(deleted.pop(), "X");

        return builder.toString();
    }


    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = 8;
        int k = 2;
        String[] cmd = {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z"};


        System.out.println(T.solution(n,k,cmd));
    }
}