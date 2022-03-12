package 구현.L2_다리를지나는트럭;

import java.util.*;

public class Solution {

    public static int solution(int bridge_length, int weight, int[] truck_weights) {

        Queue<Truck> waiting = new LinkedList<>();
        for (int truckWeight : truck_weights) {
            waiting.offer(new Truck(truckWeight, 0));
        }

        LinkedList<Truck> bridge = new LinkedList<>();
        bridge.offer(waiting.poll());
        int time = 1;

        while (!bridge.isEmpty()) {
            // 시간 증가 및 다리 무게 계산
            int weightSum = 0;
            for (Truck truck : bridge) {
                truck.increaseTime();
                weightSum += truck.weight;
            }
            time++;

            // 시간이 되면 다리에서 치움
            if (bridge.peek().isTimeOut(bridge_length)) {
                weightSum -= bridge.poll().weight;
            }

            // 무게가 된다면 트럭을 올림
            if (!waiting.isEmpty() && weightSum + waiting.peek().weight <= weight) {
                bridge.offer(waiting.poll());
            }
        }

        return time;
    }

    public static class Truck {
        public int weight;
        public int time;

        public Truck(int weight, int time) {
            this.weight = weight;
            this.time = time;
        }

        public void increaseTime() {
            time+=1;
        }

        public boolean isTimeOut(int bridgeLength) {
            return time == bridgeLength;
        }
    }

    public static void main(String[] args) {
        System.out.println(solution(2, 10, new int[]{7,4,5,6}));
        System.out.println(solution(100, 100, new int[]{10}));
        System.out.println(solution(100, 100, new int[]{10,10,10,10,10,10,10,10,10,10}));

    }
}

/**
 * ================================================================================
 * 링크 : https://programmers.co.kr/learn/courses/30/lessons/42583?language=java
 * 성공여부 : 성공
 * 풀이시간 : 1h
 * 
 * 시간복잡도 : O( bridge_length * 트럭개수 )
 * 메모리(테케5) : 94.9 MB
 * 소요 시간 : 38.56 ms
 * ================================================================================
 * 
 * 큐를 활용해야하는 문제
 * 객체지향적으로 품
 * for문을 최대한 적게쓰려고 노력함
 * 
 */