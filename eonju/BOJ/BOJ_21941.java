import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String target = bufferedReader.readLine();
        int quantity = Integer.parseInt(bufferedReader.readLine());

        HashMap<String, Integer> removeSentence = new HashMap<>();
        for (int i = 0; i < quantity; i++) {
            String[] input = bufferedReader.readLine().split(" ");
            removeSentence.put(input[0], Integer.valueOf(input[1]));
        }

        int answer = 0;

        boolean flag = true;
        while (flag) {
            flag = false;
            for (String key : removeSentence.keySet()) {
                if (target.contains(key)) {
                    answer += removeSentence.get(key);
                    target = target.replaceFirst(key, "");
                    flag = true;
                }
            }

        }

        System.out.println(answer + target.length());
    }
}
