package 카카오.L1_비밀지도;

class Answer {

    public String[] solution(int n, int[] arr1, int[] arr2) {
        int[] container = new int[n];

        for (int i = 0; i < n; i++) {
            container[i] = arr1[i] | arr2[i];
        }

        String[] answer = new String[n];

        for (int i = 0; i < n; i++) {
            String ans = "";
            int remainder = container[i];

            for (int j = 0; j < n; j++) {
                if (remainder % 2 == 1) {
                    ans = "#" + ans;
                } else {
                    ans = " " + ans;
                }
                remainder = remainder / 2;
            }

            answer[i] = ans;
        }
        return answer;
    }

}