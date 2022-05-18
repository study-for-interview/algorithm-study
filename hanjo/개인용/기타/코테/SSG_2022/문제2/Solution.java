package 코테.SSG_2022.문제2;

import java.util.*;

public class Solution {

    public String[] solution(String[] logs){

        Map<String, Map<Integer, Integer>> students = new HashMap<>();
        for(String log : logs){
            // 파싱
            String[] splited = log.split(" ");
            String student = splited[0];
            int problem = Integer.parseInt(splited[1]);
            int score = Integer.parseInt(splited[2]);

            // 학생 map
            if(!students.containsKey(student)){
                students.put(student, new TreeMap<>());
            }

            // 문제 map
            Map<Integer, Integer> problems = students.get(student);
            if(!problems.containsKey(problem)){
                problems.put(problem, score);
            }
            else{
                problems.replace(problem, score);
            }
        }

        // 완전탐색으로 검사
        List<String> keys = new ArrayList<>(students.keySet());
        int n = keys.size();
        Set<String> answer = new HashSet<>();
        for(int i=0; i<n; i++){
            for(int j=i+1; j<n; j++){
                String s1 = keys.get(i);
                String s2 = keys.get(j);
                if(isCheated(students.get(s1), students.get(s2))){
                    answer.add(s1);
                    answer.add(s2);
                }
            }
        }

        if(answer.isEmpty()){
            return new String[]{"None"};
        }
        else{
            return answer.stream().sorted().toArray(String[]::new);
        }
    }

    public boolean isCheated(Map<Integer, Integer> p1, Map<Integer, Integer> p2){
        List<Integer> p1List = new ArrayList<>(p1.keySet());
        List<Integer> p2List = new ArrayList<>(p2.keySet());
        // 1
        if(p1List.size() != p2List.size()){
            return false;
        }
        int n = p1List.size();
        if(n < 5){
            return false;
        }
        // 2
        for(int i=0; i<n; i++){
            int num1 = p1List.get(i);
            int num2 = p2List.get(i);
            if(num1 != num2){
                return false;
            }
            // 3
            if(p1.get(num1) != p2.get(num2)){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // 0001 0002
        System.out.println(Arrays.toString(new Solution().solution(
            new String[]{"0001 3 95", "0001 5 90", "0001 5 100", "0002 3 95", "0001 7 80", "0001 8 80", "0001 10 90", "0002 10 90", "0002 7 80", "0002 8 80", "0002 5 100", "0003 99 90"}
        )));
        // 1101 1102 1901 1902 1903
        System.out.println(Arrays.toString(new Solution().solution(
            new String[]{"1901 1 100", "1901 2 100", "1901 4 100", "1901 7 100", "1901 8 100", "1902 2 100", "1902 1 100", "1902 7 100", "1902 4 100", "1902 8 100", "1903 8 100", "1903 7 100", "1903 4 100", "1903 2 100", "1903 1 100", "1101 1 95", "1101 2 100", "1101 4 100", "1101 7 100", "1101 9 100", "1102 1 95", "1102 2 100", "1102 4 100", "1102 7 100", "1102 9 100"}
        )));
        // None
        System.out.println(Arrays.toString(new Solution().solution(
            new String[]{"1901 10 50", "1909 10 50"}
        )));
    }
}
