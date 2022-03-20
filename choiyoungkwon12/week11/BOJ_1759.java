package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1759 암호 만들기
 */
public class BOJ_1759 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int l = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        String[] strings = new String[c];
        boolean[] visited = new boolean[c];
        for (int i = 0; i < c; i++) {
            strings[i] = st.nextToken();
        }
        Arrays.sort(strings);

        combination(strings, visited, 0, c, l);

    }

    static void combination(String[] arr, boolean[] visited, int start, int n, int r) {
        if (r == 0) {
            print(arr, visited, n);
            return;
        }

        for (int i = start; i < n; i++) {
            visited[i] = true;
            combination(arr, visited, i + 1, n, r - 1);
            visited[i] = false;
        }

    }

    // 배열 출력
    static void print(String[] arr, boolean[] visited, int n) {
        StringBuilder sb = new StringBuilder();

        int consonant = 0;
        int vowel = 0;

        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                if (arr[i].equals("a") || arr[i].equals("e") || arr[i].equals("i") || arr[i].equals("u")
                    || arr[i].equals("o")) {
                    consonant++;
                } else {
                    vowel++;
                }
                    sb.append(arr[i]);
            }
        }
        if (consonant >= 1 && vowel >= 2){
            System.out.println(sb);
        }
    }


}
