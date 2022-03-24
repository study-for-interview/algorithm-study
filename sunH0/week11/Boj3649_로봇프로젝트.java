package BOJ;

import java.util.Arrays;
import java.util.Scanner;

public class Boj3649_로봇프로젝트 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextInt()) {
            long x = sc.nextInt() * 10000000;
            int n = sc.nextInt();
            int[] arr = new int[n];

            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }

            Arrays.sort(arr); 

            int left = 0, right = arr.length - 1;

            while (left < right) {
                long sum = arr[left] + arr[right];

                if (sum == x) { 
                    break;
                }

                if (sum < x) {
                    left++;
                } else {       
                    right--;
                }
            }

            if(left >= right){
                System.out.println("danger");
            }else{
                System.out.println(String.format("yes %d %d",arr[left],arr[right]));;
            }
        }
    }
}
