package 카카오.L1_신규아이디추천;

import java.util.*;

class Solution {

    public static class Number{
        public String numInt;
        public String numStr;

        public Number(String numInt, String numStr){
            this.numInt = numInt;
            this.numStr = numStr;
        }
    }

    public static final List<Number> numbers = List.of(
        new Number("0", "zero"),
        new Number("1", "one"),
        new Number("2", "two"),
        new Number("3", "three"),
        new Number("4", "four"),
        new Number("5", "five"),
        new Number("6", "six"),
        new Number("7", "seven"),
        new Number("8", "eight"),
        new Number("9", "nine")
    );

    public int solution(String s) {
        for(Number number : numbers){
            s = s.replace(number.numStr, number.numInt);
        }
        return Integer.parseInt(s);
    }

    public static void main(String[] args){
        // 1478
        System.out.println(new Solution().solution("one4seveneight"));
        // 234567
        System.out.println(new Solution().solution("23four5six7"));
        // 234567
        System.out.println(new Solution().solution("2three45sixseven"));
        // 123
        System.out.println(new Solution().solution("123"));
    }
}


/**
 * ================================================================================
 * 링크 : https://programmers.co.kr/learn/courses/30/lessons/81301?language=java
 * 날짜 : 220324
 * 성공여부 : 성공
 * 풀이시간 : 10m
 * 
 * 시간복잡도 : 
 * 테케 8 : (0.11ms, 73.2MB)
 * ================================================================================
 * 
 * String.replace(타겟 Str, 교체 Str)
 * String.replaceAll(타겟 정규식, 교체 Str)
 * 
 */