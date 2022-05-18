package 코테.SSG_2022.문제1;

import java.util.*;

public class Solution {

    public static class Truck implements Comparable<Truck>{
        public int num;
        public int fuel;

        public Truck(int num, int fuel) {
            this.num = num;
            this.fuel = fuel;
        }

        @Override
        public int compareTo(Truck o) {
            return o.fuel - this.fuel;
        }
    }

    public int solution(int[] v, int a, int b){
        int n = v.length;

        List<Truck> trucks = new ArrayList<>();
        for(int i=0; i<n; i++){
            trucks.add(new Truck(i, v[i]));
        }

        int time = 0;
        Loop : while(true){
            Collections.sort(trucks);
            // 선두
            Truck first = trucks.get(0);
            first.fuel -= a;
            if(first.fuel < 0){
                break;
            }
            // 나머지
            for(int i=1; i<n; i++){
                Truck next = trucks.get(i);
                next.fuel -= b;
                if(next.fuel < 0){
                    break Loop;
                }
            }
            time++;
        }
        return time;
    }

    public static void main(String[] args) {
        // 3
        System.out.println(new Solution().solution(new int[]{4,5,5}, 2, 1));
        // 2
        System.out.println(new Solution().solution(new int[]{4,4,3}, 2, 1));
    }
}
