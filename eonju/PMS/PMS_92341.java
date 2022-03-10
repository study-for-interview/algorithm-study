import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.TreeMap;

class Solution {

    private static int basicTime;
    private static int basicFee;
    private static int unitTime;
    private static int unitFee;

    public int[] solution(int[] fees, String[] records) {

        int lastTime = 60 * 23 + 59;
        basicTime = fees[0];
        basicFee = fees[1];
        unitTime = fees[2];
        unitFee = fees[3];

        TreeMap<String, List<Integer>> answers = new TreeMap<>();
        TreeMap<String, Queue<Integer>> inMap = new TreeMap<>();
        TreeMap<String, Queue<Integer>> outMap = new TreeMap<>();

        for (int i = 0; i < records.length; i++) {
            String[] input = records[i].split(" ");
            int time = makeTime(input[0]);
            String number = input[1];
            String command = input[2];

            if (command.equals("IN")) {
                inMap.putIfAbsent(number, new LinkedList<>());
                inMap.get(number).add(time);
            } else {
                outMap.putIfAbsent(number, new LinkedList<>());
                outMap.get(number).add(time);
            }
        }

        for (String number : inMap.keySet()) {
            Queue<Integer> inQueue = inMap.get(number);
            Queue<Integer> outQueue;
            if (!outMap.containsKey(number)) {
                outQueue = new LinkedList<>();
            } else {
                outQueue = outMap.get(number);
            }

            while (!inQueue.isEmpty()) {
                Integer inTime = inQueue.poll();
                if (outQueue.isEmpty()) {
                    answers.putIfAbsent(number, new ArrayList<>());
                    answers.get(number).add(lastTime - inTime);
                } else {
                    Integer outTime = outQueue.poll();
                    answers.putIfAbsent(number, new ArrayList<>());
                    answers.get(number).add(outTime - inTime);
                }
            }
        }

        int[] returnAnswer = new int[answers.size()];
        int idx = 0;
        for (String number : answers.keySet()) {
            List<Integer> times = answers.get(number);
            returnAnswer[idx] = makeFee(times);
            idx++;
        }

        return returnAnswer;
    }

    public static int makeFee(List<Integer> times) {
        int time = 0;
        for (int i = 0; i < times.size(); i++) {
            time += times.get(i);
        }

        int fee = 0;

        if (time - basicTime > 0) {
            fee += (int) (Math.ceil((double) (time - basicTime) / unitTime) * unitFee);
        }

        fee += basicFee;

        return fee;
    }

    public static int makeTime(String str) {
        String[] input = str.split(":");
        int hour = Integer.parseInt(input[0]);
        int min = Integer.parseInt(input[1]);

        return hour * 60 + min;
    }
}
