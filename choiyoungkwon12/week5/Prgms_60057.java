package Programmers;

/**
 * https://programmers.co.kr/learn/courses/30/lessons/60057 2020 KAKAO BLIND RECRUITMENT 문자열 압축
 */
public class Prgms_60057 {

    public static void main(String[] args) {
        Prgms_60057 s = new Prgms_60057();
        int result = s.solution("aabbbbbbbbbbbb");
        System.out.println("result = " + result);
    }

    public int solution(String s) {
        int answer = s.length();

        for (int i = 1; i <= s.length() / 2; i++) {
            int count = 1;
            String substring = s.substring(0, i);
            StringBuilder result = new StringBuilder();

            for (int j = i; j <= s.length(); j += i) {
                String nextString = s.substring(j, Math.min(j + i, s.length()));

                if (substring.equals(nextString)){
                    count++;
                }else{
                    result.append(count != 1 ? count : "").append(substring);
                    substring = nextString;
                    count = 1;
                }
            }

            result.append(count != 1 ? count : "").append(substring);
            answer = Math.min(answer, result.length());
        }

        return answer;
    }
}
