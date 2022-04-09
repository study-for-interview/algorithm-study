package 코테.오늘의집_2022_2.문제3;

import java.util.*;

public class Solution {
    
    public String solution(String tstring, String[][] variables){
        // key값 변환
        for(String[] variable : variables){
            variable[0] = "{" + variable[0] + "}";
        }

        
        // 변경사항 기록 리스트
        List<String> changeList = new ArrayList<>();
        changeList.add(tstring);

        // 문자열 변경
        while(true){
            boolean isChanged = false;
            for(String[] variable : variables){
                if(tstring.contains(variable[0])){
                    tstring = tstring.replace(variable[0], variable[1]);
                    isChanged = true;
                }
            }
            if(!isChanged){
                break;
            }
            if(changeList.contains(tstring)){
                tstring = changeList.get(changeList.size()-2);
                break;
            }
            changeList.add(tstring);
        }

        return tstring;
    }

    public static void main(String[] args){
        // this is string string is changed
        System.out.println(new Solution().solution(
            "this is {template} {template} is {state}", 
            new String[][]{
                {"template", "string"},
                {"state", "changed"}
            }
        ));
        // this is string string is string
        System.out.println(new Solution().solution(
            "this is {template} {template} is {state}", 
            new String[][]{
                {"template", "string"},
                {"state", "{template}"}
            }
        ));
        // this is {template} {template} is {state}
        System.out.println(new Solution().solution(
            "this is {template} {template} is {state}", 
            new String[][]{
                {"template", "{state}"},
                {"state", "{template}"}
            }
        ));
        // this is {templates} {templates} is {templates}
        System.out.println(new Solution().solution(
            "this is {template} {template} is {state}", 
            new String[][]{
                {"template", "{state}"},
                {"state", "{templates}"}
            }
        ));
        // d d d {d} {i}
        System.out.println(new Solution().solution(
            "{a} {b} {c} {d} {i}", 
            new String[][]{
                {"b", "{c}"},
                {"a", "{b}"},
                {"e", "{f}"},
                {"h", "i"},
                {"d", "{e}"},
                {"f", "{d}"},
                {"c", "d"}
            }
        ));
        // {a} {b} {f} {f} {f} {g}
        System.out.println(new Solution().solution(
            "{a} {b} {c} {d} {e} {g}", 
            new String[][]{
                {"a", "{b}"},
                {"b", "{a}"},
                {"c", "{f}"},
                {"d", "{c}"},
                {"e", "{d}"},
                {"g", "{b}"},
            }
        ));
    }
}
