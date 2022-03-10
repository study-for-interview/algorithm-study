import java.util.*;

class Car implements Comparable<Car> {
    int carNum;
    int fee;

    public Car(int carNum, int fee) {
        this.carNum = carNum;
        this.fee = fee;
    }

    @Override
    public int compareTo(Car o) {
        return this.carNum - o.carNum;
    }
}

class ParkStatus {
    int minute;
    String state;

    public ParkStatus(int minute, String state) {
        this.minute = minute;
        this.state = state;
    }
}

public class PG_92341 {

    public int[] solution(int[] fees, String[] records) {
        int basicTime = fees[0];
        int basicRate = fees[1];
        int extraTime = fees[2];
        int extraCharge = fees[3];

        HashMap<String, ParkStatus> map = new HashMap<>();
        ArrayList<Car> car = new ArrayList<>();
        for (String x : records) {
            int minute = 0;
            String[] s = x.split(" ");
            String[] time = s[0].split(":");
            minute = Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);

            if (!map.containsKey(s[1])) {
                map.put(s[1], new ParkStatus(-minute, s[2])); // 차량번호, 요금, 내역
            } else {
                if (s[2].equals("IN")) map.put(s[1], new ParkStatus(map.get(s[1]).minute - minute, s[2]));
                if (s[2].equals("OUT")) map.put(s[1], new ParkStatus(map.get(s[1]).minute + minute, s[2]));
            }
        }

        for (String key : map.keySet()) {
            ParkStatus parkStatus = map.get(key);
            if (parkStatus.state.equals("IN")) parkStatus.minute += 1439;
            int totalRate = 0;
            if (parkStatus.minute <= basicTime) {
                car.add(new Car(Integer.parseInt(key), basicRate));
            } else {
                int extraRate = ((parkStatus.minute - basicTime) % extraTime == 0 ? (parkStatus.minute - basicTime) / extraTime : (parkStatus.minute - basicTime) / extraTime + 1);
                totalRate =  (basicRate + extraRate * extraCharge);
                car.add(new Car(Integer.parseInt(key), totalRate));
            }
        }

        Collections.sort(car);

        int[] answer = new int[car.size()];
        for (int i = 0; i < car.size(); i++) {
            answer[i] = car.get(i).fee;
        }

        return answer;
    }


    public static void main(String[] args) {
        Solution T = new Solution();
        Scanner kb = new Scanner(System.in);

        String[] records = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};
        int[] fees = {180, 5000, 10, 600};
        System.out.println(T.solution(fees,records));
    }
}
