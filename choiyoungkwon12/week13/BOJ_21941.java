package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/21941
 * 문자열 제거
 */
public class BOJ_21941 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int m = Integer.parseInt(br.readLine());
        StringTokenizer st;
        PriorityQueue<Info> pq = new PriorityQueue<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            String word = st.nextToken();
            int point = Integer.parseInt(st.nextToken());
            if (word.length() >= point) {
                continue;
            }
            pq.add(new Info(word, point));
        }

        int answer = 0;
        while (!pq.isEmpty()) {
            Info info = pq.poll();
            while (s.contains(info.word)) {
                s = s.replaceFirst(info.word, "_");
                answer += info.point;
            }
        }

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '_') {
                answer++;
            }
        }

        System.out.println(answer);
    }

    static class Info implements Comparable<Info> {

        String word;
        int point;

        public Info(String word, int point) {
            this.word = word;
            this.point = point;
        }

        @Override
        public int compareTo(Info o) {
            if (o.point / o.word.length() == this.point / word.length()) {
                return this.point - o.point;
            }
            return o.point / o.word.length() - this.point / word.length();
        }
    }

}
