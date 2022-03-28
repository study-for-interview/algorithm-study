package 코테.라인_2022.문제2;

import java.util.*;

class Solution {

    public static class Sentence {

        public int score;
        public List<Character> chars;

        public Sentence(int score, List<Character> chars) {
            this.score = score;
            this.chars = chars;
        }
    }

    public int solution(String[] sentences, int n) {

        List<Sentence> sentenceList = new ArrayList<>();

        for (String sentence : sentences) {
            HashSet<Character> chars = new HashSet<>();
            // 문장별 점수 구하기
            int score = 0;
            for (char c : sentence.toCharArray()) {
                if (Character.isUpperCase(c)) {
                    chars.add('@');
                    score++;
                }
                if (c != ' ') {
                    chars.add(Character.toLowerCase(c));
                }
                score++;
            }
            // 제한 숫자 초과시
            if (chars.size() > n) {
                score = 0;
            }
            // 문장의 점수와 포함 문자리스트 저장
            sentenceList.add(new Sentence(score, new ArrayList<>(chars)));
        }

        int l = sentenceList.size();
        int max = 0;
        // 완전탐색하며 비교
        for (int i = 0; i < l; i++) {
            Sentence cur = sentenceList.get(i);
            int score = cur.score;
            for (int j = 0; j < l; j++) {
                if (i == j) {
                    continue;
                }
                Sentence next = sentenceList.get(j);
                // next가 cur에 포함될 경우 점수 ++
                if (isSubset(cur, next)) {
                    score += next.score;
                }
            }
            max = Math.max(max, score);
        }

        return max;
    }

    public boolean isSubset(Sentence cur, Sentence next) {
        for (char c : next.chars) {
            if (!cur.chars.contains(c)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // 20
        System.out.println(new Solution().solution(
            new String[]{"line in line", "LINE", "in lion"},
            5
        ));

        // 12
        System.out.println(new Solution().solution(
            new String[]{"ABcD", "bdbc", "a", "Line neWs"},
            7
        ));

    }
}
