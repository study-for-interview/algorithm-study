package 코테.오늘의집_2022_2.문제1;

import java.util.*;

class Solution {

    public static final String LEFT = "left";
    public static final String RIGHT = "right";

    public String[] solution(String path) {
        int n = path.length();
        char[] dir = path.toCharArray();
        List<String> messages = new ArrayList<>();

        int cur=0;
        Loop : while(cur < n){
            for(int i=1; i<=5; i++){
                int next = cur + i;
                if(next >= n){
                    continue;
                }
                if(dir[cur] != dir[next]){
                    int x = cur;
                    int y = (next - cur)*100;
                    String direction = getDirection(dir[cur], dir[next]);
                    String message = String.format("Time %d: Go straight %dm and turn %s", x, y, direction);
                    messages.add(message);
                    cur = next;
                    continue Loop;
                }
            }
            cur++;
        }

        String[] answer = new String[messages.size()];
        for(int i=0; i<messages.size(); i++){
            answer[i] = messages.get(i);
        }
        return answer;
    }

    public String getDirection(char cur, char next){
        if(cur == 'E'){
            if(next == 'N'){
                return LEFT;
            }
            if(next == 'S'){
                return RIGHT;
            }
        }
        else if(cur == 'N'){
            if(next == 'W'){
                return LEFT;
            }
            if(next == 'E'){
                return RIGHT;
            }
        }
        else if(cur == 'W'){
            if(next == 'S'){
                return LEFT;
            }
            if(next == 'N'){
                return RIGHT;
            }
        }
        if(next == 'E'){
            return LEFT;
        }
        else{
            return RIGHT;
        }
    }

    public static void main(String[] args){
        //
        System.out.println(new Solution().solution("EEESEEEEEENNNN"));
        //
        System.out.println(new Solution().solution("SSSSSSWWWNNNNNN"));

    }
}