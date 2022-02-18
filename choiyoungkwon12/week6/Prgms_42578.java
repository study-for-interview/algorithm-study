package Programmers;

import java.util.HashMap;
import java.util.Map;

/**
 * https://programmers.co.kr/learn/courses/30/lessons/42578
 * 코딩테스트 연습
 * 해시
 * 위장
 */
public class Solution8 {

    public static void main(String[] args) {
        Solution8 solution = new Solution8();
        String[][] clothes = {{"yellowhat", "headgear"}, {"bluesunglasses", "eyewear"}, {"green_turban", "headgear"}};
        int result = solution.solution(clothes);
        System.out.println(result);
    }

    /**
     * 경우의 수를 구하는 공식은 4종류의 옷과 그 옷이 {n, m, o, p}의 개수로 있을 때 아래와 같다.
     *
     * (n + 1) * (m + 1) * (o + 1) * (p + 1) - 1
     * 각 종류들은 하나를 입거나, 안입거나의 선택지가 있기 때문에 옷의 개수에 안입는 선택지를 하나 추가한다.
     * 그리고 옷을 고르는 선택은 동시에 일어나는 일이기 때문에, 각 옷들을 곱해 경우의 수를 계산한다.
     * 이 때, 하나도 안입는 선택지는 없으므로 그 경우의 수 하나를 뺀다.
     * */
    public int solution(String[][] clothes) {
        Map<String, Integer> clothesMap = new HashMap<>();

        for (int i = 0; i < clothes.length; i++) {
            // 옷 종류에 따른 갯수 저장
            clothesMap.put(clothes[i][1], clothesMap.getOrDefault(clothes[i][1], 0) + 1);
        }
        int result = 1;
        for (String s : clothesMap.keySet()) {

            int clothesEA = clothesMap.get(s);
            result = result * (clothesEA + 1);
        }
        return result -1;
    }
}
