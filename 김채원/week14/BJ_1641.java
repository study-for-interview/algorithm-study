import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_1641 {
    static int n, c;
    static int[] arr;
    static ArrayList<Integer> minus;
    static ArrayList<Integer> plus;
    static int answer;

    public int solution() {
        for (int i = 0; i < n; i++) {
            if (arr[i] < 0) minus.add(arr[i]);
            if (arr[i] > 0) plus.add(arr[i]);
        }

        Collections.sort(minus, Collections.reverseOrder());
        Collections.sort(plus);

        if (n == 1) {
            return Math.abs(arr[0]);
        }

        //플러스가 더큼
        int index1 = plus.size() - c - 1;
        int index2 = minus.size() - 1;

        if (minus.size() == 0) {
            answer += plus.get(plus.size() - 1);
        } else if (plus.size() == 0) {
            answer += Math.abs(minus.get(minus.size() - 1));
            index2 = minus.size() - c - 1;
        } else if (Math.abs(minus.get(minus.size() - 1)) > plus.get(plus.size() - 1)) { // 마이너스가 더 큼
            index2 = minus.size() - c - 1;
            index1 = plus.size() - 1;
            answer += Math.abs(minus.get(minus.size() - 1));
        } else {
            answer += plus.get(plus.size() - 1);
        }


        if (index1 >= 0) {
            for (int i = index1; i >= 0; i -= c) {
                answer += 2 * plus.get(i);
            }
        }

        if (index2 >= 0) {
            for (int i = index2; i >= 0; i -= c) {
                if (index2 < 0) break;
                answer += 2 * Math.abs(minus.get(i));
            }
        }


        return answer;
    }

    public static void main(String[] args) throws IOException {
        BJ_1641 T = new BJ_1641();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        arr = new int[n];
        answer = 0;

        minus = new ArrayList<>();
        plus = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(T.solution());
    }
}