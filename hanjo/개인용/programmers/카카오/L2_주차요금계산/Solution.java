package 카카오.L2_주차요금계산;

import java.util.*;

class Solution {

    public static int baseTime;
    public static int baseFee;
    public static int perMinute;
    public static int perFee;

    public static int[] solution(int[] fees, String[] records) {
        init(fees);

        HashMap<String, Car> cars = new HashMap<>();

        for(String record: records){
            String[] splited = record.split(" ");
            String time = splited[0];
            String carNum = splited[1];
            String status = splited[2];

            if(cars.containsKey(carNum)){
                cars.get(carNum).addTime(time, status);
            }
            else{
                cars.put(carNum, new Car(carNum, time, status));
            }
        }

        ArrayList<Car> result = new ArrayList<>(cars.values());
        Collections.sort(result);

        int[] answer = new int[result.size()];

        for(int i=0; i<result.size(); i++){
            answer[i] = result.get(i).calculate();
        }
        return answer;
    }

    public static void init(int[] fees){
        baseTime = fees[0];
        baseFee = fees[1];
        perMinute = fees[2];
        perFee = fees[3];
    }

    public static class Car implements Comparable<Car>{
        public int num;
        public ArrayList<Integer> inTimes = new ArrayList<>();
        public ArrayList<Integer> outTimes = new ArrayList<>();
        
        public Car(String num, String time, String status){
            this.num = Integer.valueOf(num);
            addTime(time, status);
        }

        public void addTime(String time, String status){
            if("IN".equals(status)){
                this.inTimes.add(timeOf(time));
            }
            else{
                this.outTimes.add(timeOf(time));
            }
        }

        private int timeOf(String time){
            String[] splited = time.split(":");
            return Integer.valueOf(splited[0])*60 + Integer.valueOf(splited[1]);
        }

        public int calculate(){
            if(inTimes.size() != outTimes.size()){
                addTime("23:59", "OUT");
            }
            int timeSum = 0;
            for(int i=0; i<inTimes.size(); i++){
                timeSum += outTimes.get(i) - inTimes.get(i);
            }
            timeSum = timeSum - baseTime > 0 ? timeSum - baseTime : 0 ;
            return baseFee + perFee*(int)Math.ceil((double)timeSum/perMinute);
        }

        @Override
        public int compareTo(Car o){
            return this.num - o.num;
        }
    }


    public static void main(String args[]) {

        // [14600, 34400, 5000]
        System.out.println(Arrays.toString(solution(
            new int[]{180, 5000, 10, 600},
            new String[]{"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", 
            "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", 
            "22:59 5961 IN", "23:00 5961 OUT"}
        )));

        // [0, 591]
        System.out.println(Arrays.toString(solution(
            new int[]{120, 0, 60, 591},
            new String[]{"16:00 3961 IN","16:00 0202 IN","18:00 3961 OUT",
            "18:00 0202 OUT","23:58 3961 IN"}
        )));

        // [14841]
        System.out.println(Arrays.toString(solution(
            new int[]{1, 461, 1, 10},
            new String[]{"00:00 1234 IN"}
        )));
    }
}


/**
 * ================================================================================
 * 링크 : https://programmers.co.kr/learn/courses/30/lessons/92341
 * 성공여부 : 성공
 * 풀이시간 : 1h 30m
 * 
 * 시간복잡도 : n
 * 테케7 : (5.90ms, 82.6MB)
 * ================================================================================
 * 
 * 문자열 파싱 + 적절한 자료구조 사용 + 구현 문제
 * 레벨 2인데 엄청 오래걸려서 풀었다. (타입때문에 풀면서 파이썬 쓰고싶어짐;)
 * 
 * 맨처음엔 차량별 입차기록 HashMap + 차량별 시간합계 HashMap => 이렇게 두개를 사용했음
 * 물론 이렇게 풀어도 되지만, 뭔가 객체지향적으로 풀수 있을것 같아서 엎고 다시 품
 * 
 * 따라서 Car 객체를 만들어주고, 모든 정보는 Car에 저장하기로 함.
 * 만약 hashcode equals까지 재정의 해줬으면 HashSet을 썼을텐데, 그거까진 오바라서 HashMap 사용
 * 
 */