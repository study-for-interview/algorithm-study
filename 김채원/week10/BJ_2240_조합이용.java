import java.io.*;
import java.util.*;

class Main {
    static int t, w;
    static int[] arr;
    static int[] combi;
    static ArrayList<Integer> change;
    static int answer;
    static int tmp;

    public static void combination(int L, int s) {
        if (L == w) {
            int sum = tmp;
            //기본위치가 1이므로 자리바꾸기전에 1이 있으면 더해주기
            for (int j = 0; j < combi[0]; j++) {
                if(arr[j] == 1 ) sum += 1;
            }

            for (int i = 0; i < w; i++) {
                if (combi[i] < change.size() - 1) { //change의 마지막 인덱스가 아니라면
                    sum += change.get(combi[i]+1) - change.get(combi[i]);
                } else {
                    sum += t - change.get(combi[i]);
                }
            }

            answer = Math.max(answer, sum);
        } else {
            for (int i = s; i < change.size(); i++) {
                combi[L] = i;
                combination(L+1,s+1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Main T = new Main();
        StringTokenizer st = new StringTokenizer(br.readLine());
        t = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        arr = new int[t];
        combi = new int[w];
        change = new ArrayList<>();
        answer = 0 ;
        tmp = 0 ;

        int x = 1 ;

        for (int i = 0; i < t; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            if(arr[i] != x ) change.add(i);
            x = arr[i];
        }

        answer += change.get(0);
        tmp += change.get(0);

        combination(0,0);
        System.out.println(answer);
    }
}