import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

class Main {

    private static HashMap<Integer, List<Integer>> city;
    private static int N;
    private static int[] people;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(bufferedReader.readLine());
        people = new int[N + 1];
        city = new HashMap<>();

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 1; i <= N; i++) {
            city.put(i, new ArrayList<>());
            people[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        for (int i = 0; i < N - 1; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int cityA = Integer.parseInt(stringTokenizer.nextToken());
            int cityB = Integer.parseInt(stringTokenizer.nextToken());

            city.get(cityA).add(cityB);
            city.get(cityB).add(cityA);
        }

        dp = new int[N + 1][2];
        dfs(1, 0);
        System.out.println(Math.max(dp[1][0], dp[1][1]));
    }

    public static void dfs(int n, int parent) {
        for (int linked : city.get(n)) {
            if (linked != parent) {
                dfs(linked, n);
                dp[n][0] += Math.max(dp[linked][0], dp[linked][1]);
                dp[n][1] += dp[linked][0];
            }
        }

        dp[n][1] += people[n];
    }
}
