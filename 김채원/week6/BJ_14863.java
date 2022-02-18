import java.io.*;
import java.util.*;

class Area {
    int walkTime;
    int walkMoney;
    int bicycleTime;
    int bicycleMoney;

    public Area(int walkTime, int walkMoney, int bicycleTime, int bicycleMoney) {
        this.walkTime = walkTime;
        this.walkMoney = walkMoney;
        this.bicycleTime = bicycleTime;
        this.bicycleMoney = bicycleMoney;
    }
}


public class BJ_14863 {
    public static int n;
    public static int m;
    public static int[] combi;
    public static Area[] areas;
    public static int totalTime;
    public static int answer;

    public static void combination(int depth) {
        if (depth == n) {
            totalTime = 0;
            for (int i = 0; i < n; i++) {
                if (combi[i] == 0) totalTime += areas[i].walkTime;
                else totalTime += areas[i].bicycleTime;
            }
            if (totalTime <= m) {
                int totalMoney = travel();
                maxMoney(totalMoney);
            }

            return;
        }

        for (int i = 0; i < 2; i++) {
            combi[depth] = i;
            combination(depth + 1);
        }

    }

    public static int travel() {
        int totalMoney = 0;
        for (int i = 0; i < n; i++) {
            if (combi[i] == 0) totalMoney += areas[i].walkMoney;
            else totalMoney += areas[i].bicycleMoney;
        }
        return totalMoney;
    }

    public static void maxMoney(int totalMoney) {
        answer = Math.max(answer, totalMoney);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        combi = new int[n];
        areas = new Area[n];
        answer = 0;

        for (int i = 0; i < n; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int walkTime = Integer.parseInt(st2.nextToken());
            int walkMoney = Integer.parseInt(st2.nextToken());
            int bicycleTime = Integer.parseInt(st2.nextToken());
            int bicycleMoney = Integer.parseInt(st2.nextToken());
            areas[i] = new Area(walkTime, walkMoney, bicycleTime, bicycleMoney);
        }

        combination(0);
        System.out.println(answer);

        bw.flush();
        bw.close();
        br.close();
    }

}