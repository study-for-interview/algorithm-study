package Programmers;

import java.text.ParseException;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;

/**
 * https://programmers.co.kr/learn/courses/30/lessons/92341 주차요금계산
 */
public class Prgms_92341 {

    static int baseTime, baseCost, overTime, overCost;
    static LocalTime DEADLINE = LocalTime.of(23, 59);

    public static void main(String[] args) throws ParseException {
        Prgms_92341 p = new Prgms_92341();
        int[] solution = p.solution(new int[]{180, 5000, 10, 600},
            new String[]{"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN",
                "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"});
        Arrays.stream(solution).forEach(value -> System.out.println("value = " + value));
    }

    public int[] solution(int[] fees, String[] records) throws ParseException {
        baseTime = fees[0];
        baseCost = fees[1];
        overTime = fees[2];
        overCost = fees[3];

        StringTokenizer st;
        LocalTime[] times = new LocalTime[10001];
        Map<Integer, Long> map = new HashMap<>();

        // 입력 값으로 시간 차이 저장
        for (String record : records) {
            st = new StringTokenizer(record);
            String sTime = st.nextToken();
            int hour = Integer.parseInt(sTime.substring(0, 2));
            int min = Integer.parseInt(sTime.substring(3, 5));
            LocalTime time = LocalTime.of(hour, min);
            Integer carNumber = Integer.valueOf(st.nextToken());
            String io = st.nextToken();

            if (io.equals("IN")) {
                times[carNumber] = time;
            } else {
                LocalTime localTime = times[carNumber];
                long timeGap = ChronoUnit.MINUTES.between(localTime, time);
                times[carNumber] = null;
                map.put(carNumber, map.getOrDefault(carNumber, 0L) + timeGap);
            }
        }

        // 출차 기록 없는 차들은 23:59에 나간 것으로 계산
        for (int i = 0; i < times.length; i++) {
            if (Objects.nonNull(times[i])) {
                long timeGap = ChronoUnit.MINUTES.between(times[i], DEADLINE);
                map.put(i, map.getOrDefault(i, 0L) + timeGap);
            }
        }

        // 차량 번호 기준 오름차순으로 리턴하기 위함.
        int[] answer = new int[map.size()];
        int idx = 0;

        List<Integer> numbers = new ArrayList<>(map.keySet());
        Collections.sort(numbers);

        // 주차 비용 계산
        for (Integer number : numbers) {
            Long parkingTime = map.get(number);
            int parkingCost = baseCost;
            if (parkingTime > baseTime) {
                double result = (double) (parkingTime - baseTime) / overTime;
                parkingCost = (int) (baseCost + (Math.ceil(result) * overCost));
            }
            answer[idx] = parkingCost;
            idx++;
        }

        return answer;
    }
}
