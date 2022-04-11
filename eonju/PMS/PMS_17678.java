import java.util.*;

public class Pro17678 {

    public static String solution(int n, int t, int m, String[] timetable) {

        String answer = "";

        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {

                return o1 - o2;
            }

        });

        for (String table : timetable) {

            int time = Integer.parseInt(table.substring(0, 2)) * 60 + Integer.parseInt(table.substring(3));

            queue.add(time);
        }

        int busTime = 9 * 60;

        int last = 0;

        while (n-- > 0) {//

            int accept = m;

            int time = 0;

            while (!queue.isEmpty()) {

                if (queue.peek() <= busTime && accept > 0) {

                    accept--;
                    time = queue.poll();
                } else {
                    break;
                }
            }

            if (n > 0) {

                if (queue.isEmpty()) {

                    last = busTime + ((n + 1) * t);
                    break;
                }

                busTime += t;

            } else {

                if (accept > 0) {
                    last = busTime;
                } else {
                    last = time - 1;
                }

                break;

            }
        }

        answer = String.format("%02d", last / 60) + ":" + String.format("%02d", last % 60);

        return answer;
    }
}
