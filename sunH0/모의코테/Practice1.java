package 기타;

import java.util.*;

public class Practice1 {

    public static void main(String[] args) {
        String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};
        solution(record);
    }

    public static String[] solution(String[] record) {

        List<UserLog> userLogs = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        
        for (String str : record) {
            String[] arr = str.split(" ");

            if(!arr[0].equals("Leave")){
                
                if(map.containsKey(arr[1])) {
                    map.replace(arr[1], arr[2]);
                }else{
                    map.put(arr[1], arr[2]);
                }
            }
            
            if(arr[0].equals("Change")) continue;

            userLogs.add(new UserLog(arr[1], LogState.valueOf(arr[0])));
        }

        String[] answer = new String[userLogs.size()];

        for(int i=0;i<userLogs.size();i++){
            StringBuilder sb = new StringBuilder();
            UserLog log = userLogs.get(i);
            
            sb.append(map.get(log.id)).append("님이 ").append(log.state.getStr());
            answer[i] = sb.toString();
        }

        
        return answer;
    }

    static class UserLog {

        String id;
        LogState state;

        public UserLog(String id, LogState state){
            this.id =id;
            this.state = state;
        }

    }

    
}

enum LogState {
    Enter("들어왔습니다."),
    Leave("나갔습니다.");

    private final String str;

    LogState(String str) { this.str = str; }

    public String getStr() { return str; }

}
