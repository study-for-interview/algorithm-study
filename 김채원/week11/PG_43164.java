import java.util.Collections;
import java.util.LinkedList;



class Solution {
    private boolean[] visited; // 방문한지 안한지를 체크하는 visited 배열
    private LinkedList<String> result;
    public String[] solution(String[][] tickets) {
        visited = new boolean[tickets.length];
        result = new LinkedList<String>();
        dfs(0, "ICN", "ICN", tickets);
        Collections.sort(result); // 답들 중 가장 알파벳순이 빠른 배열들로 정렬
        String[] answer = result.poll().split(" "); // 가장 빠른 배열을 뽑아서 String형 배열로 만듬
        return answer;
    }

    public void dfs(int count, String start, String end, String[][] tickets) {
        if (count == tickets.length) { // 모든 도시를 방문 했다면
            result.add(end);
            return;
        }
        for (int i = 0; i < tickets.length; i++) {
            if (!visited[i] && tickets[i][0].equals(start)) { // start와 같고 들리지 않은 공항을 찾고
                visited[i] = true; // 해당 도시를 방문 한 것으로 표시
                dfs(count + 1, tickets[i][1], end + " " + tickets[i][1], tickets); // 카운트를 하나 올리고 도착 공항을 start로 넣어주고
                // end를 도착 도시로
                visited[i] = false;
            }
        }
        return;
    }
}