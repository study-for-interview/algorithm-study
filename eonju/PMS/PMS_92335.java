class Solution {

    private static int answer = 0;

    public static void main(String[] args) {
        int answer1 = solution(437674, 3);
        int answer2 = solution(110011, 10);
        System.out.println(answer1);
        System.out.println(answer2);
    }

    public static int solution(int n, int k) {
        String tmp = convert(n, k);

        if (!tmp.contains("0")) {
            if(isDecimal(Integer.parseInt(tmp))){
                answer++;
            }
        } else {
            for (String target : tmp.split("0")) {
                if (target.length() == 0) {
                    continue;
                }
                int number = Integer.parseInt(target);
                if (isDecimal(number)) {
                    answer++;
                }
            }
        }

        return answer;
    }

    public static boolean isDecimal(int number) {
        if (number <= 1) {
            return false;
        } else if (number == 2) {
            return true;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static String convert(int number, int notation) {
        StringBuilder sb = new StringBuilder();
        while (number > 0) {
            sb.insert(0, number % notation);
            number /= notation;
        }
        return String.valueOf(sb);
    }
}
