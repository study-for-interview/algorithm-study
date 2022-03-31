package PMS;

import java.util.ArrayList;
import java.util.Collections;


public class Pms_여행경로 {
    boolean[] visited;                    
    ArrayList<String> answers;
 
    public String[] solution(String[][] tickets) {
        visited = new boolean[tickets.length];    
        answers = new ArrayList<String>();
        int count = 0;    

        dfs(count, "ICN", "ICN",tickets);                
        Collections.sort(answers);  

        String[] answer = answers.get(0).split(" ");    
        return answer;
    }
    public void dfs(int count, String now, String answer, String[][]ticktes) {

        if(count == ticktes.length) {            
            answers.add(answer);                
            return;
        }
        for(int i = 0; i < ticktes.length; i++) {
            if(!visited[i] && ticktes[i][0].equals(now)) {        
                visited[i] = true;                                    
                dfs(count+1, ticktes[i][1],answer+" "+ticktes[i][1] , ticktes);
                visited[i] = false;
            }
        }
        return;
    }
}
