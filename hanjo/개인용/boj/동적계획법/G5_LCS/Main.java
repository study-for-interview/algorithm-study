package 동적계획법.G5_LCS;

import java.io.*;

public class Main {

    public static int solution(char[] arr1, char[] arr2) {

        int len1 = arr1.length;
        int len2 = arr2.length;

        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int x = 1; x <= len1; x++) {
            for (int y = 1; y <= len2; y++) {
                if (arr1[x - 1] == arr2[y - 1]) {
                    dp[x][y] = dp[x - 1][y - 1] + 1;
                } 
                else {
                    dp[x][y] = Math.max(dp[x - 1][y], dp[x][y - 1]);
                }
            }
        }

        return dp[len1][len2];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] arr1 = br.readLine().toCharArray();
        char[] arr2 = br.readLine().toCharArray();

        System.out.println(solution(arr1, arr2));

    }

}

/**
 * 링크 : https://www.acmicpc.net/problem/9251
 * 성공여부 : 실패 (구글링함)
 * 풀이시간 : 2h
 * 
 * 시간복잡도 : O(len1*len2)
 * 메모리 : 18224KB
 * 소요 시간 : 148ms
 * 
 * 1. 2차원 배열을 통한 풀이를 생각해내야함
 * 2. 배열을 채워보고 *규칙*을 찾아내야함
 * https://user-images.githubusercontent.com/71180414/149795935-d5d10f81-e144-4580-9801-e7f884e3c19d.png
 */
