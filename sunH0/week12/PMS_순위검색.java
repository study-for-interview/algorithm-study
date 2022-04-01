package PMS;

import java.util.*;

public class PMS_순위검색 {
    public int[] solution(String[] info, String[] query) { 
        Map<String, ArrayList<Integer>> map = new HashMap<>(); 

        for (String i : info) { 
            String[] data = i.split(" "); 

            String[] languages = { data[0], "-" }; 
            String[] jobs = { data[1], "-" }; 
            String[] careers = { data[2], "-" }; 
            String[] foods = { data[3], "-" }; 
            int score = Integer.parseInt(data[4]);

            for (String lang : languages) 
                for (String job : jobs) 
                    for (String car : careers) 
                        for (String food : foods) { 
                            String[] keyArr = { lang, job, car, food }; 
                            String key = String.join(" ", keyArr); 

                            ArrayList<Integer> arr = map.getOrDefault(key, new ArrayList<Integer>()); 
                            arr.add(score); 
                            map.put(key, arr); 
                        }
        }

        for (List<Integer> scores : map.values()) {
            Collections.sort(scores);
        }


        int[] answer = new int[query.length];

        for(int i=0; i<query.length; i++) { 
            String[] data = query[i].replaceAll(" and ", "").split(" ");
            int score = Integer.parseInt(data[1]);

            if (map.containsKey(data[0])) continue;

            List<Integer> list = map.get(data[0]);

            int left = 0; 
            int right = list.size(); 


            while (left < right) {
                int mid = (left + right) / 2; 

                if (list.get(mid) >= score) {
                    right = mid; 
                }else left = mid + 1; 
            }

            answer[i] = list.size()-left;
        }

        return answer;
    }    
}

