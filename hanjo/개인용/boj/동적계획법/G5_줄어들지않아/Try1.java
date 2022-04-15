package 동적계획법.G5_줄어들지않아;

import java.io.*;

public class Try1 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        for(int i=0; i<t; i++){
            int n = Integer.parseInt(br.readLine());
            System.out.println(solution(n));
        }
    }

    public static int solution(int n){
        int count = 0;

        for(int i=0; i<Math.pow(10, n); i++){
            int num = i;
            int right = Integer.MAX_VALUE;
            boolean isValid = true;

            while(num != 0){
                if(num % 10 > right){
                    isValid = false;
                    break;
                }
                right = num%10;
                num /= 10;
            }

            if(isValid){
                count++;
            }
        }
        return count;
    }

    
    
}

// 완탐 -> 시간초과