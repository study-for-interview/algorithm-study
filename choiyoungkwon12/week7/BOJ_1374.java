package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1374
 * 강의실
 */

public class BOJ_1374 {

    static PriorityQueue<Lecture> priorityQueue = new PriorityQueue<>((o1, o2) -> {
        if (o1.end - o2.end != 0) {
            return o1.end - o2.end;
        } else {
            return o1.start - o2.start;
        }
    });

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<Lecture> lectures = new ArrayList<>();
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            st = new StringTokenizer(s);
            int num = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            lectures.add(new Lecture(num, start, end));
        }

        Collections.sort(lectures);

        System.out.println(solution(lectures));
    }

    static int solution(List<Lecture> lectures) {
        priorityQueue.add(lectures.get(0));
        int minClassRoom = 1;

        if (lectures.size() == 1) {
            return minClassRoom;
        }

        for (int i = 1; i < lectures.size(); i++) {
            if (!priorityQueue.isEmpty()) {
                Lecture lecture = priorityQueue.peek();

                if (lecture.end <= lectures.get(i).start) {
                    priorityQueue.remove();
                    priorityQueue.add(lectures.get(i));
                } else {
                    priorityQueue.add(lectures.get(i));
                    minClassRoom++;
                }
            } else {
                priorityQueue.add(lectures.get(i));
            }
        }

        return minClassRoom;
    }
}

class Lecture implements Comparable<Lecture> {

    int number;
    int start;
    int end;

    public Lecture(int number, int start, int end) {
        this.number = number;
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Lecture l) {

        // 시작시간으로 정렬, 같으면 종료시간으로 정렬
        if ((this.start - l.start) != 0) {
            return this.start - l.start;
        } else {
            return this.end - l.end;
        }
    }
}
