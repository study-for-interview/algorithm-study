import java.util.*;

//https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=hhj732&logNo=221972863697
class SpecialWord {
    int cnt;
    int index;

    public SpecialWord(int cnt, int index) {
        this.cnt = cnt;
        this.index = index;
    }
}


class PG_1830 {
    static char tmp;
    static char preWord;

    public String solution(String sentence) {
        String answer = "";
        tmp = ' ';
        preWord = ' ';
        boolean check = false;
        HashMap<Character, SpecialWord> map = new HashMap<>();
        for (int i = 0; i < sentence.length(); i++) {
            char word = sentence.charAt(i);
            //공백이 있으면 옳바른게 아님
            if(sentence.contains(" ")) {
                return "invalid";
            }
            if (Character.isUpperCase(word)) {
                if (Character.isUpperCase(preWord) && check) {
                    answer += " ";
                    check = false;
                }
                answer += word;
            }
            if (Character.isLowerCase(word)) {
                if (map.containsKey(word)) {

                    int previousIndex = map.get(word).index;
                    tmp = word;
                    map.put(word, new SpecialWord(map.get(word).cnt + 1, i));
                    // cnt가 두개 이상이면 두번째 규칙아님
                    if (map.get(word).cnt > 2 && i - previousIndex > 2) return "invalid";
                    if(map.get(word).cnt > 1) check = true;

                } else {
                    map.put(word, new SpecialWord(1, i));
                    if (map.containsKey(tmp)) {
                        if(check && map.get(tmp).cnt > 1) answer += " ";
                    }

                }
            }
            preWord = word;
        }

        return answer;
    }


    public static void main(String[] args) {
        Solution T = new Solution();
        Scanner kb = new Scanner(System.in);

        String sentence = "HaEaLaLaObWORLDb";
        System.out.println(T.solution(sentence));
    }
}
