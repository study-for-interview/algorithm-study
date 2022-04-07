import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class BJ_21941 {
    static int n;

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        HashMap<String, Integer> score = new HashMap<>();
        ArrayList<Character> stack = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String a = st.nextToken();
            int m = Integer.parseInt(st.nextToken());
            score.put(a, m);
        }


        int[] dp = new int[s.length()+1];
        // sdfg
        //기저값 설정
        dp[0] = 0;
        dp[1] = 1;
        stack.add(s.charAt(0));
        for (String key : score.keySet()) {
            if (key.equals(String.valueOf(s.charAt(0)))) {
                dp[1] = score.get(key);
            }
        }

        for (int i = 1; i < s.length(); i++) {
            char x = s.charAt(i);
            stack.add(x);

            dp[i+1] = dp[i] + 1;
            for (String key : score.keySet()) {
                if (key.charAt(key.length() - 1) == x) {
                    boolean ch = false;
                    int index = 0;
                    if (key.length() > stack.size()) continue;
                    for (int j = 0; j < key.length(); j++) {
                        if (key.charAt(key.length() - j - 1) != stack.get(stack.size() - j - 1)) {
                            ch = true;
                            break;
                        }
                        index = stack.size() - j -1;
                    }
                    if (!ch) {
                        dp[i+1] = Math.max(dp[i+1], dp[index] + score.get(key));
                    }
                }
            }

        }


        System.out.println(dp[dp.length-1]);

    }


}