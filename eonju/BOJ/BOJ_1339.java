import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;

public class Main2 {

    private static HashMap<Integer, String> words;
    private static HashMap<String, Integer> alphabet;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());

        words = new HashMap<>();
        alphabet = new HashMap<>();

        HashSet<String> set = new HashSet<>();

        for (int i = 0; i < N; i++) {
            String word = bufferedReader.readLine();
            words.put(i, word);
            for (String character : word.split("")) {
                set.add(character);
            }
        }

        for (String str : set) {
            alphabet.put(str, -1);
        }


    }

    public static void findMax() {
        int maxLength = 0;
        for (Integer key : words.keySet()) {
            String word = words.get(key);

        }

    }

}
