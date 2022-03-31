import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_16120 {
    static String solution(String s){
        String answer = "PPAP";
        ArrayList<Character> a = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            a.add(s.charAt(i));
            if(a.size() < 4 ) continue;

            while (true) {
                if(a.size()<4) break;
                if (a.get(a.size() - 1) == 'P' && a.get(a.size() - 2) == 'A' && a.get(a.size() - 3) == 'P') {
                    a.remove(a.size() - 1);
                    a.remove(a.size() - 1);
                    a.remove(a.size() - 1);
                } else {
                    break;
                }
            }
        }

        if (a.size() != 1) return "NP";
        if(a.get(0) != 'P') return "NP";
        return answer;
    }

    public static void main(String[] args) throws IOException {
        BJ_16120 T = new BJ_16120();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        System.out.println(solution(s));
    }
}