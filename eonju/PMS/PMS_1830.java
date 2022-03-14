import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {

    public static void main(String[] args) {
        String answer1 = solution("SpIpGpOpNpGJqOqA");
        System.out.println(answer1);
    }


    public static String solution(String sentence) {
        List<String> words = new ArrayList<>();

        int startIdx = 0;
        int endIdx;

        while (true) {
            if (startIdx >= sentence.length()) {
                break;
            }

            if (Character.isLowerCase(sentence.charAt(startIdx))) { //맨 첫글자가 소문자인 경우
                endIdx = sentence.indexOf(sentence.charAt(startIdx), startIdx + 1);

                if (endIdx == -1) {
                    return "invalid";
                } else {
                    words.add(sentence.substring(startIdx + 1, endIdx));
                    startIdx = endIdx + 1;
                }
            } else { // 맨 첫글자가 대문자인 경우
                if (startIdx + 1 == sentence.length()) {
                    words.add(String.valueOf(sentence.charAt(startIdx)));
                    startIdx = startIdx + 1;
                    continue;
                }
                char target = sentence.charAt(startIdx + 1);
                endIdx = sentence.lastIndexOf(target, startIdx + 2) + 1;

                words.add(sentence.substring(startIdx, endIdx + 1));
                startIdx = endIdx + 1;
            }
        }

        ArrayList<String> answer = new ArrayList<>();

        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            Queue<Character> queue = new LinkedList<>();
            StringBuilder sb = new StringBuilder();

            char rule = '-';
            int lowerCaseCnt = 0;
            int upperCaseCnt = 0;

            for (int j = 0; j < word.length(); j++) {
                if (Character.isLowerCase(word.charAt(j))) {
                    if (queue.isEmpty()) {
                        return "invalid";
                    }

                    if (rule == '-') {
                        rule = word.charAt(j);
                    }

                    if (rule == word.charAt(j) && Character.isUpperCase(queue.peek())) {
                        queue.add(word.charAt(j));
                    }

                    lowerCaseCnt++;
                } else {
                    queue.add(word.charAt(j));
                    sb.append(word.charAt(j));
                    upperCaseCnt++;
                }
            }

            if (lowerCaseCnt >= upperCaseCnt) {
                return "invalid";
            }

            answer.add(sb.toString());
        }

        if (answer.isEmpty()) {
            return "invalid";
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < answer.size(); i++) {
                sb.append(answer.get(i));
                sb.append(" ");
            }

            return sb.toString().trim();
        }
    }
}
