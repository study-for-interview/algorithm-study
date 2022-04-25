class Solution {
    public int solution(String s) {
        String answer = s.replaceAll("one", "1");
        answer = answer.replaceAll("two", "2");
        answer = answer.replaceAll("three", "3");
        answer = answer.replaceAll("four", "4");
        answer = answer.replaceAll("five", "5");
        answer = answer.replaceAll("six", "6");
        answer = answer.replaceAll("seven", "7");
        answer = answer.replaceAll("eight", "8");
        answer = answer.replaceAll("nine", "9");
        answer = answer.replaceAll("zero", "0");

        return Integer.parseInt(answer);
    }
}
