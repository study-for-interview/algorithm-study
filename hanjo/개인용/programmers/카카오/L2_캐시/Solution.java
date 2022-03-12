package 카카오.L2_캐시;

import java.util.*;

class Solution {

    private static final int HIT = 1;
    private static final int MISS = 5;

    public static int solution(int cacheSize, String[] cities) {

        int runtime = 0;
        LinkedList<String> cache = new LinkedList<>();

        if (cacheSize == 0) {
            return cities.length * MISS;
        }

        for (String city : cities) {

            city = city.toUpperCase();

            if (cache.contains(city)) {
                runtime += HIT;
                cache.remove(city);
                cache.addLast(city);
            } else {
                runtime += MISS;
                if (cache.size() == cacheSize) {
                    cache.removeFirst();
                }
                cache.addLast(city);
            }
        }
        return runtime;
    }

    public static void main(String[] args) {
        System.out.println(solution(3, new String[] { "Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo",
                "Seoul", "NewYork", "LA" }));
        System.out.println(solution(3,
                new String[] { "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul" }));
        System.out.println(solution(2, new String[] { "Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco",
                "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome" }));
        System.out.println(solution(5, new String[] { "Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco",
                "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome" }));
        System.out.println(solution(2, new String[] { "Jeju", "Pangyo", "NewYork", "newyork" }));
        System.out.println(solution(0, new String[] { "Jeju", "Pangyo", "Seoul", "NewYork", "LA" }));
    }
}

/**
 * 문제 : https://programmers.co.kr/learn/courses/30/lessons/17680
 * 소요시간 : 45m
 * LRU -> 가장 오랫동안 참조되지 않은 페이지를 교체
 * ArrayList와 LinkedList 차이
 */

