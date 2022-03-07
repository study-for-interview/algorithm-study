package Programmers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * https://programmers.co.kr/learn/courses/30/lessons/17676
 * 추석 트래픽
 */
public class Prgms_17676 {

    public static void main(String[] args) throws ParseException {
        Prgms_17676 p = new Prgms_17676();
        int solution = p.solution(new String[]{"2016-09-15 01:00:04.001 2.0s", "2016-09-15 01:00:07.000 2s"});
        System.out.println(solution);
    }

    public int solution(String[] lines) throws ParseException {
        int answer = 0;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss.SSS");

        int[] counts = new int[lines.length];

        for (int i = 0; i < lines.length; i++) {

            String[] infos = lines[i].split(" ");
            Date endDate = simpleDateFormat.parse(infos[1]);
            long endTime = endDate.getTime();

            for (int j = i; j < lines.length; j++) {
                String[] next = lines[j].split(" ");

                Date nextEndDate = simpleDateFormat.parse(next[1]);
                double sec = Double.parseDouble(next[2].substring(0, next[2].length() - 1)); // 처리 시간

                double nextStart = nextEndDate.getTime() - sec * 1000 + 1;

                if (endTime + 1000 > nextStart) {
                    counts[i] += 1;
                    answer = Math.max(answer, counts[i]);
                }
            }

        }

        return answer;
    }
}
