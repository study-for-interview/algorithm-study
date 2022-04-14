package 카카오.L2_순위검색;

import java.util.*;

class Try1 {
    
    public static class Category{
        public String lang;
        public String tech;
        public String career;
        public String food;
        public int score;
        
        public Category(String lang, String tech, String career, String food, int score){
            this.lang = lang;
            this.tech = tech;
            this.career = career;
            this.food = food;
            this.score = score;
        }

        public boolean checkCondition(Category condition){
            if(!condition.lang.equals("-") && !condition.lang.equals(lang)){
                return false;
            }
            if(!condition.tech.equals("-") && !condition.tech.equals(tech)){
                return false;
            }
            if(!condition.career.equals("-") && !condition.career.equals(career)){
                return false;
            }
            if(!condition.food.equals("-") && !condition.food.equals(food)){
                return false;
            }
            if(score < condition.score){
                return false;
            }
            return true;
        }
    }

    public int[] solution(String[] info, String[] query) {

        List<Integer> answer = new ArrayList<>();

        for(String row : query){
            String[] category = row.replace(" and ", " ").split(" ");
            Category condition = new Category(
                category[0], category[1], category[2], category[3], Integer.parseInt(category[4])
            );
            int count = 0;
            for(String row2 : info){
                String[] category2 = row2.split(" ");
                Category applicant = new Category(
                    category2[0], category2[1], category2[2], category2[3], Integer.parseInt(category2[4])
                );
                
                if(applicant.checkCondition(condition)){
                    count++;
                }
            }
            answer.add(count);
        }

        return answer.stream().mapToInt(v->v).toArray();
    }

    public static void main(String[] args){
        // [1,1,1,1,2,4]
        System.out.println(Arrays.toString(new Try1().solution(
            new String[]{
                "java backend junior pizza 150",
                "python frontend senior chicken 210",
                "python frontend senior chicken 150",
                "cpp backend senior pizza 260",
                "java backend junior chicken 80",
                "python backend senior chicken 50"
            },
            new String[]{
                "java and backend and junior and pizza 100",
                "python and frontend and senior and chicken 200",
                "cpp and - and senior and pizza 250",
                "- and backend and senior and - 150",
                "- and - and - and chicken 100",
                "- and - and - and - 150"
            }
        )));
    }
}

// 완전탐색 -> 당연히 시간초과