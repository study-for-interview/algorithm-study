package 카카오.L2_오픈채팅방;

import java.util.*;

public class Solution {
    public static String[] solution(String[] record) {
        Map<String, String> idMap = new HashMap<>();
        List<String[]> splitedList = new ArrayList<>();

        for(String status : record){
            String[] splited = status.split(" ");
            if(!"Leave".equals(splited[0])){
                idMap.put(splited[1], splited[2]);
            }
            if(!"Change".equals(splited[0])){
                splitedList.add(splited);
            }
        }

        int length = splitedList.size();
        String[] answer = new String[length];

        for(int i=0; i<length; i++){
            if("Enter".equals(splitedList.get(i)[0])){
                answer[i] = idMap.get(splitedList.get(i)[1]) + "님이 들어왔습니다.";
            }
            else{
                answer[i] = idMap.get(splitedList.get(i)[1]) + "님이 나갔습니다.";
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};
        System.out.println(Arrays.toString(solution(record)));
    }
}
