package 카카오.L1_숫자문자열과영단어;

import java.util.*;

class Solution {

    public static class Number{
        public String num;
        public String str;

        public Number(String num, String str){
            this.num = num;
            this.str = str;
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
            s = s.replace(number.str, number.num);
        }
        return Integer.parseInt(s);
    }
}