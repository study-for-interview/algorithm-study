package Programmers;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * https://programmers.co.kr/learn/courses/30/lessons/1830 브라이언의 고민
 */
public class Prgms_1830 {

    static final String INVALID = "INVALID";

    public static void main(String[] args) {
        Prgms_1830 p = new Prgms_1830();
        //String tc = p.solution("kABaCDk");
        String tc = p.solution("HaEaLaLaObWORLDb");
        // String tc = p.solution("AxAxAxAoBoBoB");
        System.out.println("tc = " + tc);
    }

    public String solution(String sentence) {
        StringBuilder answerList = new StringBuilder();
        LinkedHashMap<Character, ArrayList<Integer>> lowerCount = new LinkedHashMap<>();
        try {
            int size = sentence.length();
            for (int i = 0; i < size; i++) {
                char c = sentence.charAt(i);
                if (Character.isLowerCase(c)) {
                    if (!lowerCount.containsKey(c)) {
                        lowerCount.put(c, new ArrayList<>());
                    }
                    lowerCount.get(c).add(i);
                }
            }
            int stringIdx = 0;
            int startChar, endChar, startWord, endWord, rule;
            int lastStartChar = -1, lastEndChar = -1;
            int lastStartWord = -1, lastEndWord = -1;

            ArrayList<Integer> temp;
            for (char c : lowerCount.keySet()) {
                temp = lowerCount.get(c);
                int count = temp.size();
                startChar = temp.get(0);
                endChar = temp.get(count - 1);
                if (count == 1 || count >= 3) {
                    for (int i = 1; i < count; i++) {
                        if (temp.get(i) - temp.get(i - 1) != 2) {
                            return INVALID;
                        }
                    }
                    rule = 1;
                } else {
                    int distance = endChar - startChar;
                    if (distance == 2 && (endChar < lastEndChar && startChar > lastStartChar)) {
                        rule = 1;
                    } else if (distance >= 2) {
                        rule = 2;
                    } else {
                        return INVALID;
                    }
                }
                if (rule == 1) {
                    startWord = startChar - 1;
                    endWord = endChar + 1;
                    if (lastStartWord < startWord && lastEndWord > endWord) {
                        if (startChar - lastStartChar == 2 && lastEndChar - endChar == 2) {
                            continue;
                        } else {
                            return INVALID;
                        }
                    }
                } else {
                    startWord = startChar;
                    endWord = endChar;
                    if (lastStartWord < startWord && lastEndWord > endWord) {
                        return INVALID;
                    }
                }
                if (lastEndWord >= startWord) {
                    return INVALID;
                }
                if (stringIdx < startWord) {
                    answerList.append(makeWord(sentence, stringIdx, startWord - 1));
                    answerList.append(" ");
                }
                answerList.append(makeWord(sentence, startWord, endWord));
                answerList.append(" ");
                lastStartWord = startWord;
                lastEndWord = endWord;
                lastStartChar = startChar;
                lastEndChar = endChar;
                stringIdx = endWord + 1;
            }
            if (stringIdx < size) {
                answerList.append(makeWord(sentence, stringIdx, size - 1));
            }
        } catch (Exception e) {
            return INVALID;
        }
        return answerList.toString().trim();
    }

    public String makeWord(String sentence, int start, int end) {
        String word = sentence.substring(start, end + 1);
        return word.replaceAll("[a-z]", "");
    }
}
