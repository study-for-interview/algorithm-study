package 코테.라인_2022.문제1;

class Solution {

    public int solution(String[] logs) {

        int count = 0;

        Loop : for(String log : logs){
            // 100 초과 거르기
            if(log.length() > 100){
                count++;
                continue;
            }
            // prefix 오류 거르기
            if(!isValid(log)){
                count++;
                continue;
            }
            // 파싱
            log = log.replace("team_name : ", "");
            log = log.replace("application_name : ", "");
            log = log.replace("error_level : ", "");
            log = log.replace("message : ", "");
            String[] contents = log.split(" ");
            // 공백 포함 거르기
            if(contents.length != 4){
                count++;
                continue;
            }
            // 알파벳인지 확인
            for(String content : contents){
                for(char c : content.toCharArray()){
                    if(!Character.isAlphabetic(c)){
                        count++;
                        continue Loop;
                    }
                }
            }
        }

        return count;
    }

    public boolean isValid(String log){
        if(!log.contains("team_name")){
            return false;
        }
        if(!log.contains("application_name")) {
            return false;
        }
        if(!log.contains("error_level")) {
            return false;
        }
        if(!log.contains("message")) {
            return false;
        }
        return true;
    }



    public static void main(String[] args) {
        // 3
        System.out.println(new Solution().solution(new String[]{
            "team_name : db application_name : dbtest error_level : info message : test",
            "team_name : test application_name : I DONT CARE error_level : error message : x",
            "team_name : ThisIsJustForTest application_name : TestAndTestAndTestAndTest error_level : test message : IAlwaysTestingAndIWillTestForever",
            "team_name : oberervability application_name : LogViewer error_level : error"
        }));
        // 6
        System.out.println(new Solution().solution(new String[]{
            "team_name : MyTeam application_name : YourApp error_level : info messag : IndexOutOfRange",
            "no such file or directory",
            "team_name : recommend application_name : recommend error_level : info message : RecommendSucces11",
            "team_name : recommend application_name : recommend error_level : info message : Success!",
            "   team_name : db application_name : dbtest error_level : info message : test",
            "team_name     : db application_name : dbtest error_level : info message : test",
            "team_name : TeamTest application_name : TestApplication error_level : info message : ThereIsNoError"
        }));
    }

}