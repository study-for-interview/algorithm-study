package 구현.S3_수이어쓰기1;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        System.out.println(solution(n));
    }

    public static int solution(int n){
        int sum = 0;
        for(int i=1; i<=n; i*=10){
            sum += n - i + 1;
        }
        return sum;
    }
    
}
