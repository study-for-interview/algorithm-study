import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BJ_1138{

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int [] tall = new int[n+1];
        for (int i = 0; i < n; i++) {
            tall[i] = Integer.parseInt(st.nextToken());
        }


        List<Integer> ans = new ArrayList<>();
        for(int i=n; i>=1; i--) {
            ans.add(tall[i], i);
        }

        for(int k : ans) {
            System.out.print(k+" ");
        }
    }
}
