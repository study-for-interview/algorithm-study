import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Solution {

    public static int[] solution(String[] info, String[] query) {

        ArrayList<Person> people = new ArrayList<>();
        HashMap<String, List<Integer>> langs = new HashMap<>();
        langs.put("cpp", new ArrayList<>());
        langs.put("java", new ArrayList<>());
        langs.put("python", new ArrayList<>());
        langs.put("-", new ArrayList<>());
        HashMap<String, List<Integer>> fileds = new HashMap<>();
        fileds.put("backend", new ArrayList<>());
        fileds.put("frontend", new ArrayList<>());
        fileds.put("-", new ArrayList<>());
        HashMap<String, List<Integer>> levels = new HashMap<>();
        levels.put("junior", new ArrayList<>());
        levels.put("senior", new ArrayList<>());
        levels.put("-", new ArrayList<>());
        HashMap<String, List<Integer>> foods = new HashMap<>();
        foods.put("chicken", new ArrayList<>());
        foods.put("pizza", new ArrayList<>());
        foods.put("-", new ArrayList<>());

        for (int i = 0; i < info.length; i++) {
            String[] input = info[i].split(" ");
            String language = input[0];
            String filed = input[1];
            String level = input[2];
            String food = input[3];

            langs.get(language).add(i);
            langs.get("-").add(i);
            fileds.get(filed).add(i);
            fileds.get("-").add(i);
            levels.get(level).add(i);
            levels.get("-").add(i);
            foods.get(food).add(i);
            foods.get("-").add(i);

            people.add(new Person(input[0], input[1], input[2], input[3], Integer.parseInt(input[4])));
        }

        int[] answer = new int[query.length];
        for (int i = 0; i < query.length; i++) {
            String[] input = query[i].split(" ");

            String language = input[0];
            String filed = input[2];
            String level = input[4];
            String food = input[6];
            int score = Integer.parseInt(input[7]);

            List<Integer> tmp = compareList(langs.get(language), fileds.get(filed));
            tmp = compareList(tmp, levels.get(level));
            tmp = compareList(tmp, foods.get(food));

            int cnt = 0;
            for (Integer integer : tmp) {
                if (people.get(integer).score >= score) {
                    cnt++;
                }
            }
            answer[i] = cnt;
        }

        return answer;
    }

    public static List<Integer> compareList(List<Integer> arrA, List<Integer> arrB) {
        ArrayList<Integer> cross = new ArrayList<>();
        if (arrA.size() == 0 || arrB.size() == 0) {
            return cross;
        }
        for (Integer peopleIdx : arrA) {
            if (arrB.contains(peopleIdx)) {
                cross.add(peopleIdx);
            }
        }
        return cross;
    }

    static class Person {

        String lang;
        String field;
        String level;
        String food;
        int score;

        public Person(String lang, String field, String level, String food, int score) {
            this.lang = lang;
            this.field = field;
            this.level = level;
            this.food = food;
            this.score = score;
        }
    }
}
