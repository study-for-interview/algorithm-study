package 그래프.탐색.L3_여행경로;

import java.util.*;

public class Try1 {

    private final String START = "ICN";
    private HashMap<String, Integer> isVisited;
    private Map<String, List<String>> graph;
    private int n;
    private List<List<String>> routes = new ArrayList<>();

    public String[] solution(String[][] tickets) {
        isVisited = new HashMap<>();
        graph = new HashMap<>();

        for(String[] ticket : tickets){
            // 인접리스트 초기화
            if(!graph.containsKey(ticket[0])){
                graph.put(ticket[0], new ArrayList<>());
            }
            if(!graph.containsKey(ticket[1])){
                graph.put(ticket[1], new ArrayList<>());
            }
            graph.get(ticket[0]).add(ticket[1]);
            // 방문체크 초기화
            isVisited.put(ticket[0], 0);
            isVisited.put(ticket[1], 0);
        }

        System.out.println(isVisited);

        n = isVisited.size();

        // dfs
        isVisited.replace(START, 1);
        dfs(START, 1, List.of(START));

        for(var route : routes){
            System.out.println(route);
        }


        String[] answer = {};
        return answer;
    }

    public void dfs(String cur, int depth, List<String> route){
        if(route.size() == n){
            routes.add(route);
            // List<Map.Entry<String, Integer>> route = new LinkedList<>(isVisited.entrySet());
            // routes.add(
            //     route.stream()
            //         .sorted((o1, o2) -> o1.getValue() - o2.getValue())
            //         .map(v -> v.getKey())
            //         .collect(Collectors.toList())
            // );
        }
        
        for(String next : graph.get(cur)){
            if(isVisited.get(next) == 0){
                List<String> temp = new LinkedList<>(route);
                temp.add(next);

                isVisited.replace(next, depth+1);
                dfs(next, depth+1, temp);
                isVisited.replace(next, 0);
            }
        }
    }

    public static void main(String[] args){
        var run = new Try1();
        // ["ICN", "JFK", "HND", "IAD"]
        System.out.println(Arrays.toString(
            run.solution(new String[][]{
                {"ICN", "JFK"},
                {"HND", "IAD"},
                {"JFK", "HND"}
        })));
        // ["ICN", "ATL", "ICN", "SFO", "ATL", "SFO"]
        System.out.println(Arrays.toString(
            run.solution(new String[][]{
                {"ICN", "SFO"},
                {"ICN", "ATL"},
                {"SFO", "ATL"},
                {"ATL", "ICN"},
                {"ATL","SFO"}
        })));
    }
}
