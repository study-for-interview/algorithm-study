public class PG_60057 {
    public int solution(String s) {
        int answer = 1000;
        if (s.length() < 2) {
            return s.length();
        }
        for (int i = 1; i < s.length() / 2 + 2; i++) { // subStr 사이즈 : 압축사이즈
            String subStr = s.substring(0, i);
            String output = "";
            int count = 1;
            int lastIndex = 0;
            for (int j = i; j + i <= s.length(); j += i) { // 비교 대상 String
                String strToCompare = s.substring(j, j + i); // 텍스트가 연속되어 압축 가능한 경우
                if (subStr.equals(strToCompare)) {
                    count++; // 연속되지 않는 경우
                } else {
                    if (count > 1) {
                        output += count;
                        count = 1;
                    }
                    output += subStr;
                    subStr = strToCompare;
                }
                lastIndex = j + i;
            } // 마지막 round에서 기록된 값 업데이트
            if (count > 1) output += count;
            output += subStr; // String을 끝까지 확인하지 않고 끝난 경우
            if (lastIndex < s.length()) {
                output += s.substring(lastIndex);
            } // 더 짧은 output 길이로 업데이트
            answer = Math.min(answer, output.length());
        }
        return answer;
    }
}

