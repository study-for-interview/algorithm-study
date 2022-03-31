package Programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * https://programmers.co.kr/learn/courses/30/lessons/43164?language=java 여행경로
 */
public class Prgms_43164 {

    static boolean[] visited;
    static List<String> list = new ArrayList<>();

    public static void main(String[] args) {
        Prgms_43164 s = new Prgms_43164();
        String[] solution = s.solution(
            new String[][]{{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL", "SFO"}});
        /*String[] solution = s.solution(new String[][]{{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}});*/
        System.out.println("solution = " + Arrays.toString(solution));

    }

    public String[] solution(String[][] tickets) {
        visited = new boolean[tickets.length];
        dfs(0, "ICN", "ICN", tickets);
        Collections.sort(list);
        return list.get(0).split(" ");
    }

    private void dfs(int cnt, String start, String route, String[][] tickets) {
        if (cnt == tickets.length) {
            list.add(route);
        } else {
            for (int i = 0; i < tickets.length; i++) {
                if (!visited[i] && tickets[i][0].equals(start)) {
                    visited[i] = true;
                    dfs(cnt + 1, tickets[i][1], route + " " + tickets[i][1], tickets);
                    visited[i] = false;
                }
            }
        }
    }
}
