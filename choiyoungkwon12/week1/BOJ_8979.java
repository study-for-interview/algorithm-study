package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/8979 올림픽
 */
public class BOJ_8979 {

    private static int targetGold;
    private static int targetSilver;
    private static int targetBronze;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        int countryCount = Integer.parseInt(stringTokenizer.nextToken());
        int target = Integer.parseInt(stringTokenizer.nextToken());

        List<String> strings = new ArrayList<>();

        for (int i = 1; i <= countryCount; i++) {

            String info = br.readLine();
            strings.add(info);
            StringTokenizer targetSt = new StringTokenizer(info);

            int num = Integer.parseInt(targetSt.nextToken());

            if (num == target) {
                targetGold = Integer.parseInt(targetSt.nextToken());
                targetSilver = Integer.parseInt(targetSt.nextToken());
                targetBronze = Integer.parseInt(targetSt.nextToken());
            }
        }

        int answer = 1;

        for (int i = 0; i < strings.size(); i++) {
            if (checkedMedal(strings.get(i))) {
                answer += 1;
            }
        }

        System.out.println(answer);
    }

    private static boolean checkedMedal(String s) {
        StringTokenizer stringTokenizer = new StringTokenizer(s);
        int gold = Integer.parseInt(stringTokenizer.nextToken());
        int silver = Integer.parseInt(stringTokenizer.nextToken());
        int bronze = Integer.parseInt(stringTokenizer.nextToken());

        if (gold > targetGold) {
            return true;
        } else if (gold == targetGold && silver > targetSilver) {
            return true;
        } else
            return gold == targetGold && silver == targetSilver && bronze > targetBronze;
    }

}
