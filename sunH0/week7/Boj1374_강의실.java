import java.io.*;
import java.util.*;

public class Boj1374_강의실 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Lecture[] lectures = new Lecture[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            lectures[i] = new Lecture(start, end);
        }  

        Arrays.sort(lectures, (l1, l2) -> l1.start == l2.start ? l1.end - l2.end : l1.start - l2.start);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(lectures[0].end);

        for (int i = 1; i < N; i++) {
            if (pq.peek() <= lectures[i].start) {
                pq.poll();
            }
            pq.offer(lectures[i].end);
        }

        System.out.println(pq.size());
    }

    
    public static class Lecture {
        int start;
        int end;

        Lecture(int start, int end) {
            this.start = start;
            this.end = end;
    }
}

}

