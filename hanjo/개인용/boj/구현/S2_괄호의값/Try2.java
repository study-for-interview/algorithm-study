package 구현.S2_괄호의값;

import java.io.*;
import java.util.regex.*;

public class Try2 {

    public static boolean isValid(String str) {
        int count1 = 0, count2 = 0;

        for (String s : str.split("")) {
            if (s.equals("(")) {
                count1++;
            } else if (s.equals(")")) {
                count1--;
            }
            if (s.equals("[")) {
                count2++;
            } else if (s.equals("]")) {
                count2--;
            }
        }
        if (count1 == 0 && count2 == 0)
            return true;
        else
            return false;
    }

    public static boolean isDigit(String str) {
        if (str.contains("(") || str.contains(")") || str.contains("[") || str.contains("]"))
            return false;
        else
            return true;
    }

    public static String convert(String str, int start, int end, int result){
        StringBuilder sb = new StringBuilder(str);
        sb.replace(start, end, "+" + Integer.toString(result));
        return sb.toString();
    }

    public static String mul(String str, Matcher matcher, int val) {
        int result = val * Character.getNumericValue(matcher.group().charAt(2));
        return convert(str, matcher.start(), matcher.end()-1, result);
    }

    public static int solution(String str) {
        str = str.replaceAll("\\(\\)", "(+1)");
        str = str.replaceAll("\\[\\]", "[+1]");

        if (!isValid(str)) {
            return 0;
        }

        while (!isDigit(str)) {
            int start=0, end=0, idx = 0, sum = 0;

            // 곱하기
            int diff = 0;
            Matcher matcher = Pattern.compile("(\\[\\+[0-9]+\\])|(\\(\\+[0-9]+\\))").matcher(str);
            while(matcher.find()){
                String found = matcher.group();
                Matcher m =  Pattern.compile("([1-9]+)").matcher(found);
                m.find();
                String result = m.group();
                convert(str, matcher.start() - diff, matcher.end() - diff, Integer.parseInt(result));
                diff += found.length() - ( result.length() + 1 );
            }

            System.out.println(str);

            // 더하기
            while (true) {
                char cur = str.charAt(idx);
                if(cur == '(' || cur == ')' || cur == '[' || cur == ']'){
                    if(sum != 0){
                        str = convert(str, start, end+1, sum);
                        idx = 0;
                    }
                }
                else{
                    if(sum == 0)
                        start = idx;
                    if(Character.isDigit(cur)){
                        sum += Character.getNumericValue(cur);
                        end = idx;
                    }
                }
            }
        }

        return Integer.parseInt(str.substring(1, str.length()-1));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();

        System.out.println(solution(str));
    }

}

/**
 * 링크 : https://www.acmicpc.net/problem/2504
 * 성공여부 : 대실패
 * 풀이시간 : 3h +
 * 
 * 시간복잡도 : 
 * 메모리 : KB
 * 소요 시간 : ms
 * 
 * 정규식으로 파싱하여 풀이하려 했으나 답없는 구현 복잡도에 포기
 * 삽질만 하고 시간날림
 * 구글링결과 stack 사용하는 것이였음 다시 풀자
 */