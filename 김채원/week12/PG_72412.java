import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

class Solution {
    public int[] solution(String[] info, String[] query) {
        HashMap<String, ArrayList<Integer>> map = new HashMap<>();
        int[] answer = new int[query.length];

        for (String s2 : info) {
            String[] s = s2.split(" ");
            String[] language = {s[0], "-"};
            String[] jobGroup = {s[1], "-"};
            String[] career = {s[2], "-"};
            String[] soulFood = {s[3], "-"};
            int score = Integer.parseInt(s[4]);

            for (String value : language) {
                for (String item : jobGroup) {
                    for (String element : career) {
                        for (String s1 : soulFood) {
                            String keys = value + item + element + s1;
                            if (!map.containsKey(keys)) {
                                map.put(keys, new ArrayList<>());
                            }
                            map.get(keys).add(score);
                        }
                    }
                }
            }
        }

        for (String key : map.keySet()) {
            ArrayList<Integer> integers = map.get(key);
            Collections.sort(integers);
        }

        for (int i = 0; i < query.length; i++) {
            String[] question = query[i].split(" ");
            int isScore = Integer.parseInt(question[7]);
            String keys = question[0]+question[2]+question[4]+question[6];
            if (map.containsKey(keys)) {
                ArrayList<Integer> scores = map.get(keys);
                answer[i] = binarySearch(scores, isScore);
            } else {
                answer[i] = 0;
            }
        }

        return answer;
    }

    public int binarySearch(ArrayList<Integer> arr, int score) {

        int mid = 0;
        int end = arr.size();
        int start = 0;

        while (end > start) // end가 start보다 같거나 작아지면, 그 값이 Lower Bound이므로 반복을 종료한다.
        {
            mid = (start + end) / 2;
            if (arr.get(mid) >= score) // 중간값이 원하는 값보다 크거나 같을 경우, 끝값을 중간값으로 설정하여 다시 탐색한다.
                end = mid;
            else start = mid + 1; // 중간값이 원하는 값보다 작을 경우, 시작값을 중간값+1로 설정하여 다시 탐색한다.
        }
        return arr.size() - start;

    }

    public static void main(String[] args) {
        Solution T = new Solution();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] info = {"java backend junior pizza 150", "python frontend senior chicken 210", "python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80", "python backend senior chicken 50"};
        String[] query = {"java and backend and junior and pizza 100", "python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250", "- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150"};

        T.solution(info, query);
    }
}
