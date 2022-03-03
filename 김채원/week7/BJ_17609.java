import java.io.*;
import java.util.*;

public class BJ_17609 {
    public static int n;
    public static int[] answer;

    public static int isPalindrome(String str) {
        int count = 0;
        int lt = 0 ;
        int rt = str.length()-1;

        while (lt <= rt) {
            if(count>1) return 2;
            if (str.charAt(lt) != str.charAt(rt)) {
                if (str.charAt(lt + 1) == str.charAt(rt)) {
                    lt++;
                    count++;
                } else if (str.charAt(lt) == str.charAt(rt - 1)) {
                    rt--;
                    count++;
                } else {
                    return 2;
                }
            }
            lt++;
            rt--;
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        answer = new int[n];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            answer[i] = isPalindrome(str);
        }

        for (int i = 0; i < n; i++) {
            System.out.println(answer[i]);
        }
        bw.flush();
        bw.close();
        br.close();
    }

}