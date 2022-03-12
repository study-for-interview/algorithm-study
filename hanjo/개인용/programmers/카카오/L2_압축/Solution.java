package 카카오.L2_압축;

import java.util.*;

public class Solution {

    static public int[] solution(String msg) {
        // A~Z 까지 {A : 1} 형태로 저장
        LinkedHashMap<String, Integer> dictionary = new LinkedHashMap<>();
        for (int i = 'A'; i <= 'Z'; i++) {
            dictionary.put(Character.toString((char) i), i - 64);
        }

        List<Integer> answer = new ArrayList<>();
        int start = 0;
        int end = 1;
        // start와 end 인덱스만을 움직인다
        while (start < msg.length()) {
            String w = msg.substring(start, end);
            // 만약 자른넘이 사전에 존재하면?
            if (dictionary.containsKey(w)) {
                // 근데 끝까지 왔을 경우 (end 인덱스가 마지막이면 끝임) 그냥 넣어버린다
                if(end == msg.length()){
                    answer.add(dictionary.get(w));
                    break;
                }
                // 한칸씩 뒤로 이동
                end += 1;
            } else {
                // 사전에 없는 string은 새로 저장
                dictionary.put(w, new ArrayList<>(dictionary.values()).get(dictionary.size() - 1) + 1);
                // 딱 이전의 substring은 사전에 존재했었으므로 그 답을 저장
                answer.add(dictionary.get(msg.substring(start, end - 1)));
                // start 이동
                start = end - 1;
                end = start + 1;
            }
        }

        return answer.stream().mapToInt(v -> v).toArray();
    }

    public static void main(String[] args) {
        String msg;
        msg = "KAKAO";
        System.out.println(Arrays.toString(solution(msg)));
        msg = "TOBEORNOTTOBEORTOBEORNOT";
        System.out.println(Arrays.toString(solution(msg)));
        msg = "ABABABABABABABAB";
        System.out.println(Arrays.toString(solution(msg)));
    }
}
