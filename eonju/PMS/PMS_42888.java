import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

class Solution {

    public static void main(String[] args) {
        String[] answer = solution(
            new String[]{"Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo",
                "Change uid4567 Ryan"});

        System.out.println(Arrays.toString(answer));
    }

    public static String[] solution(String[] record) {
        HashMap<String, String> names = new HashMap<>();

        for (int i = 0; i < record.length; i++) {
            String[] input = record[i].split(" ");
            String command = input[0];
            String uid = input[1];

            if (command.equals("Enter") || command.equals("Change")) {
                names.put(uid, input[2]);
            }
        }

        ArrayList<String> answer = new ArrayList<>();

        for (int i = 0; i < record.length; i++) {
            String[] input = record[i].split(" ");
            String command = input[0];
            String uid = input[1];

            String name = names.get(uid);

            if (command.equals("Enter")) {
                answer.add(name + "님이 들어왔습니다.");
            } else if (command.equals("Leave")) {
                answer.add(name + "님이 나갔습니다.");
            }
        }

        return answer.toArray(new String[0]);
    }
}
