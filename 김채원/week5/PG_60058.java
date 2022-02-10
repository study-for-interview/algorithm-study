public class PG_60058 {


    public String solution(String p) {
        String answer = "";
        answer = recur(p);
        return answer;
    }

    String recur(String s) {
        if (s.length() == 0)
            return "";
        int left = 0;
        int right = 0;
        String u = "";
        String v = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(')
                left++;
            else
                right++;
            if (left == right) {
                u = s.substring(0, i + 1);
                v = i + 1 < s.length() ? s.substring(i + 1) : "";
                break;
            }
        }
        if (goodString(u))
            return u + recur(v);
        else {
            String empty = "(";
            empty += recur(v);
            empty += ")";
            for (int i = 1; i < u.length() - 1; i++)
                empty += u.charAt(i) == '(' ? ')' : '(';
            return empty;
        }
    }

    boolean goodString(String s) {
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(')
                left++;
            else {
                if (--left < 0)
                    return false;
            }
        }
        return true;
    }
}