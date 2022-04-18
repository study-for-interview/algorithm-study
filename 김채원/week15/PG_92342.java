import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

class Score {
    int score;
    int cnt; //필요한 화살 갯수

    public Score(int score, int cnt) {
        this.score = score;
        this.cnt = cnt;
    }
}


public class Main {
    static ArrayList<Score> scores;
    static boolean[] ch;
    static int max;
    static int[] answer;
    static int N;
    static int[] Info;

    static void dfs(int s, int sum, int n,int totalLion, int totalApeach) {
        if (sum > n) return;

        if (totalLion - totalApeach >= max) {
            int lion = 0;
            int apeach = 0;
            int[] tmp = new int[11];
            for (int i = 0; i <= 10; i++) {
                if (ch[i]) {
                    lion += scores.get(i).score;
                    tmp[i] = scores.get(i).cnt; //화살갯수 넣어주기
                } else {
                    if (scores.get(i).cnt > 1) apeach += scores.get(i).score;
                    tmp[i] = 0; //화살갯수 넣어주기
                }
            }
            if (lion > apeach) {
                if (max == lion - apeach) { //값이 같을 경우 더 작은 숫자 많은걸로
                    for (int i = 10; i >= 0; i--) {
                        if(tmp[i] < answer[i]) break;
                        if (tmp[i] > answer[i]) {
                            copy(tmp);
                            break;
                        }
                    }
                }
                if (max < lion - apeach) {
                    max = lion - apeach;
                    copy(tmp);
                }

            }
        }

        for (int i = s; i < 11; i++) {
            if (!ch[i]) {
                ch[i] = true;
                int nextApeach = totalApeach;
                if(Info[i] < 1) nextApeach -= scores.get(i).score;
                dfs(s+1, sum + scores.get(i).cnt, n,totalLion+scores.get(i).score,nextApeach);
                ch[i] = false;
            }
        }
    }

    static void copy(int[] tmp) {
        for (int j = 0; j < 11; j++) {
            answer[j] = tmp[j]; // 복사
        }
    }

    public int[] solution(int n, int[] info) {
        N = n;
        Info = new int[info.length];
        System.arraycopy(info, 0, Info, 0, info.length);

        max = Integer.MIN_VALUE;
        answer = new int[11];
        Arrays.fill(answer, Integer.MAX_VALUE);
        ch = new boolean[11];
        scores = new ArrayList<>();

        int totalApeach = 0;
        for (int i = 10; i >= 0; i--) {
            scores.add(new Score(i, info[10 - i] + 1));
            if (info[10 - i] > 0) {
                totalApeach += i;
            }
        }

        dfs(0, 0,n,0,totalApeach);

        int total = 0;
        for (int i = 0; i < answer.length; i++) {
            total += answer[i];
        }
        if (total < n) {
            answer[answer.length-1] = n-total;
        }
        int[] ans = new int[1];
        if (answer[0] == Integer.MAX_VALUE) {
            ans[0] = -1;
            return ans;
        }

        System.out.println(Arrays.toString(answer));
        return answer;
    }


    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = 5;
        int[] info = {2,1,1,1,0,0,0,0,0,0,0};

//        int n = 9;
//        int[] info = {0, 0, 1, 2, 0, 1, 1, 1, 1, 1, 1};

//        int n = 1;
//        int[] info = {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

//        int n = 1;
//        int[] info = {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        T.solution(n, info);
    }
}