package 카카오.L2_튜플;

import java.util.*;

public class Solution {
    public static int[] solution(String s) {
        Map<Integer, Integer> countMap = new HashMap<>();
        String[] splited = s.substring(2, s.length()-2).split("[}],[{]");

        for(String numSet : splited){
            for(String numStr : numSet.split(",")){
                int num = Integer.parseInt(numStr);
                if(!countMap.containsKey(num)){
                    countMap.put(num, 1);
                }
                else{
                    countMap.replace(num, countMap.get(num)+1);
                }
            }
        }
        List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(countMap.entrySet());
        Collections.sort(entryList, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        int[] answer = new int[entryList.size()];
        for(int i=0; i<entryList.size(); i++){
            answer[i] = entryList.get(i).getKey();
        }
        return answer;
    }

    public static void main(String[] args) {
        List<String> test_case = new ArrayList<>();
        test_case.add("{{2},{2,1},{2,1,3},{2,1,3,4}}");
        test_case.add("{{1,2,3},{2,1},{1,2,4,3},{2}}");
        test_case.add("{{20,111},{111}}");
        test_case.add("{{123}}");
        test_case.add("{{4,2,3},{3},{2,3,4,1},{2,3}}");
        for(String s : test_case){
            System.out.println(Arrays.toString(solution(s)));
        }
    }
}
