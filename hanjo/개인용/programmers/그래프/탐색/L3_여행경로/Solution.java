package 그래프.탐색.L3_여행경로;

import java.util.*;

public class Solution {

    private final String START = "ICN";
    private List<List<String>> routes = new LinkedList<>();

    public String[] solution(String[][] tickets) {
        // 배열을 리스트로
        LinkedList<String[]> graph = new LinkedList<>();
        for(String[] ticket : tickets){
            graph.add(ticket);
        }

        // dfs
        dfs(START, graph, new LinkedList<>());

        for(var r : routes){
            System.out.println(r);
        }

        // 문자열 정렬
        Collections.sort(routes, (List<String> r1, List<String> r2) -> {
            for(int i=0; i< r1.size(); i++){
                String s1 = r1.get(i);
                String s2 = r2.get(i);
                if (!s1.equals(s2)) {
                    return s1.compareTo(s2);
                }
            }
            return 0;
        });

        return routes.get(0).stream().toArray(String[]::new);
    }

    public void dfs(String cur, List<String[]> tickets, List<String> route){
        List<String> nextRoute = new LinkedList<>(route);
        nextRoute.add(cur);

        if(tickets.isEmpty()){
            routes.add(nextRoute);
        }

        for(String[] ticket : tickets){
            if(cur.equals(ticket[0])){
                List<String[]> nextTickets = new LinkedList<>(tickets);
                nextTickets.remove(ticket);
                dfs(ticket[1], nextTickets, nextRoute);
            }
        }
    }

    public static void main(String[] args){
        // ["ICN", "JFK", "HND", "IAD"]
        System.out.println(Arrays.toString(
            new Solution().solution(new String[][]{
                {"ICN", "JFK"},
                {"HND", "IAD"},
                {"JFK", "HND"}
        })));

        // ["ICN", "ATL", "ICN", "SFO", "ATL", "SFO"]
        System.out.println(Arrays.toString(
            new Solution().solution(new String[][]{
                {"ICN", "SFO"},
                {"ICN", "ATL"},
                {"SFO", "ATL"},
                {"ATL", "ICN"},
                {"ATL","SFO"}
        })));
    }
}

/**
 * ================================================================================
 * 링크 : https://programmers.co.kr/learn/courses/30/lessons/43164?language=java
 * 날짜 : 220323
 * 성공여부 : 실패 (1시간초과)
 * 풀이시간 : 2h
 * 
 * 시간복잡도 : 
 * 테케1 : (158.04ms, 184MB)
 * ================================================================================
 * 
 * DFS 문제
 * 자바라서 정렬이나 자료구조 정하는게 매우 힘들었다
 * 
 * DFS하면서 티켓 소진시키기 + 경로를 저장하기를 해야돼서 인접리스트 없이 그냥 풀어야했음
 * 
 * 그리고 정렬이 너무 어려웠다
 * 람다식 파라미터로 제네릭 붙은놈이 오면 타입을 꼭 써줘야함
 * 
 * 그리고 Try1에서 티켓을 지우면서 가는 조건을 빼먹어서 삽질함..
 * 
 */