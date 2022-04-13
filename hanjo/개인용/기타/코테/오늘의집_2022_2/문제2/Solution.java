package 코테.오늘의집_2022_2.문제2;

import java.util.*;

class Solution {
    public String solution(String call) {

        int n = call.length();
        int max = call.length()/2;
        Map<String, Integer> count = new HashMap<>();

        for(int window=1; window<=max; window++){
            for(int i=0; i<n; i++){
                int end = i+window;
                if(end >= n){
                    break;
                }
                String sub = call.substring(i, end);
                count.put(sub, 0);
            }
        }

        for(String key : count.keySet()){
            int cnt = getContainCnt(call, key);
            count.replace(key, count.get(key)+cnt);
        }

        System.out.println(count);



        String answer = "";
        return answer;
    }

    public int getContainCnt(String str, String target){
        int diff = str.length() - str.replace(target, "").length();
        return diff/target.length();
    }
}
