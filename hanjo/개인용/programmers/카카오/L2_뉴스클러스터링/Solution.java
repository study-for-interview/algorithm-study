package 카카오.L2_뉴스클러스터링;

import java.util.*;
import java.util.regex.Pattern;

public class Solution {
    public static Map<String, Integer> splitStr(String str) {
        str = str.toLowerCase();
        Map<String, Integer> strCount = new HashMap<>();
        for (int i = 0; i < str.length() - 1; i++) {
            String sub = str.substring(i, i + 2);
            if(!Pattern.matches("^[a-z]*$", sub)){
                continue;
            }
            // merge(key, default-value, (원래v, default-value) -> 정의)
            strCount.merge(sub, 1, (originV, defaultV) -> originV + defaultV);
        }
        return strCount;
    }

    public static int solution(String str1, String str2) {
        var strCount1 = splitStr(str1);
        var strCount2 = splitStr(str2);

        Set<String> allStr = new HashSet<>();
        allStr.addAll(strCount1.keySet());
        allStr.addAll(strCount2.keySet());

        float union = 0;
        float intersection = 0;
        for(String key : allStr){
            if(strCount1.containsKey(key) && strCount2.containsKey(key)){
                union += Math.max(strCount1.get(key), strCount2.get(key));
                intersection += Math.min(strCount1.get(key), strCount2.get(key));
            }
            else{
                if(strCount1.containsKey(key)){
                    union += strCount1.get(key);
                }
                else{
                    union += strCount2.get(key);
                }
            }
        }
        int jacquard = 65536;
        if(union != intersection){
            jacquard *= intersection/union;
        }
        return jacquard;
    }

    public static void main(String[] args) {
        List<String[]> test_case = new ArrayList<>();
        test_case.add(new String[]{"France", "french"});
        test_case.add(new String[]{"handshake", "shake hands"});
        test_case.add(new String[]{"aa1+aa2", "AAAA12"});
        test_case.add(new String[]{"E=M*C^2", "e=m*c^2"});
        for (String[] str : test_case) {
            System.out.println(solution(str[0], str[1]));
        }
    }
}
