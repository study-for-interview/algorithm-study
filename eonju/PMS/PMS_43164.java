import java.util.ArrayList;
import java.util.Collections;

public class Solution2 {

    static boolean[] ticketUsed;
    static ArrayList<String> answer;

    public String[] solution(String[][] tickets) {
        ticketUsed = new boolean[tickets.length];
        answer = new ArrayList<>();

        dfs("ICN", "ICN", 0, tickets);

        Collections.sort(answer);

        return answer.get(0).split(" ");
    }

    static void dfs(String now, String nodes, int count, String[][] tickets) {
        if (count == tickets.length) {
            answer.add(nodes);
            return;
        }

        for (int i = 0; i < tickets.length; i++) {
            if (!ticketUsed[i] && tickets[i][0].equals(now)) {
                ticketUsed[i] = true;
                dfs(tickets[i][1], nodes + " " + tickets[i][1], count + 1, tickets);
                ticketUsed[i] = false;
            }
        }
    }
}
