package 코테.sk_2022.문제1;

import java.util.PriorityQueue;

public class Answer {

    public static int solution(int money, int[] costs) {
        PriorityQueue<Coin> pq = new PriorityQueue<>();
        pq.add(new Coin(1, costs[0] * 500, costs[0]));
        pq.add(new Coin(5, costs[1] * 100, costs[1]));
        pq.add(new Coin(10, costs[2] * 50, costs[2]));
        pq.add(new Coin(50, costs[3] * 10, costs[3]));
        pq.add(new Coin(100, costs[4] * 5, costs[4]));
        pq.add(new Coin(500, costs[5], costs[5]));

        int sum = 0;

        while (!pq.isEmpty()) {
            Coin cost = pq.poll();
            int i = money / cost.unit;
            money = money % cost.unit;
            sum += i * cost.unitPrice;
        }

        return sum;
    }

    static class Coin implements Comparable<Coin> {
        int unit;
        int price;
        int unitPrice;

        public Coin(int unit, int price, int unitPrice) {
            this.unit = unit;
            this.price = price;
            this.unitPrice = unitPrice;
        }

        @Override
        public int compareTo(Coin c) {
            int i = this.price - c.price;
            if (i == 0) {
                return c.unit - this.unit;
            } else {
                return i;
            }
        }
    }

    public static void main(String[] args) {
        // 2308
        System.out.println(solution(4578, new int[]{1, 4, 99, 35, 50, 1000}));
        // 2798
        System.out.println(solution(1999, new int[]{2, 11, 20, 100, 200, 600}));
        // 1000
        System.out.println(solution(500, new int[]{2, 10, 990000, 3000005, 500000, 10000000}));
    }
}
