import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Lecture {
    int start;
    int end;

    Lecture(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

public class BJ_1374 {

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
        // 시작 시간을 기준으로 오름차순 정렬하되,
        // 시작 시간이 같다면, 종료 시간을 기준으로 오름차순 정렬한다.
        Arrays.sort(lectures, (l1, l2) -> l1.start == l2.start ? l1.end - l2.end : l1.start - l2.start);

        // 우선순위 큐에는 종료 시간만 들어가며, 오름차순으로 자동 정렬된다.
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(lectures[0].end);

        for (int i = 1; i < N; i++) {
            // 우선순위 큐에서 가장 작은 종료 시간과
            // 현재 lectures[i]의 시작 시간을 비교한다.
            if (pq.peek() <= lectures[i].start) {
                pq.poll();
            }
            pq.offer(lectures[i].end);
        }

        // 현재 우선순위 큐에 남아있는 요소의 개수가 필요한 최소한의 강의실 개수이다.
        bw.write(pq.size() + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

}