import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int testCaseQuantity = Integer.parseInt(stringTokenizer.nextToken());

        for (int testCase = 1; testCase <= testCaseQuantity; testCase++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int userQuantity = Integer.parseInt(stringTokenizer.nextToken());
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int relationQuantity = Integer.parseInt(stringTokenizer.nextToken());

            int[] parent = new int[userQuantity + 1];
            for (int number = 0; number < parent.length; number++) {
                parent[number] = number;
            }

            for (int relation = 0; relation < relationQuantity; relation++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                int userA = Integer.parseInt(stringTokenizer.nextToken());
                int userB = Integer.parseInt(stringTokenizer.nextToken());

                union(userA, userB, parent);
            }

            StringBuilder sb = new StringBuilder();
            sb.append("Scenario ");
            sb.append(testCase);
            sb.append(":\n");


            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int solveQuantity = Integer.parseInt(stringTokenizer.nextToken());

            for (int solve = 0; solve < solveQuantity; solve++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                int userA = Integer.parseInt(stringTokenizer.nextToken());
                int userB = Integer.parseInt(stringTokenizer.nextToken());

                int rootA = find(userA, parent);
                int rootB = find(userB, parent);

                if (rootA == rootB) {
                    sb.append("1\n");
                } else {
                    sb.append("0\n");
                }
            }

            System.out.println(sb);
        }
    }

    public static int[] union(int userA, int userB, int[] parent) {
        int rootA = find(userA, parent);
        int rootB = find(userB, parent);

        if (rootA != rootB) {
            if (userA < userB) {
                parent[rootB] = userA;
            } else {
                parent[rootA] = userB;
            }
        }

        return parent;
    }

    public static int find(int user, int[] parent) {
        if (parent[user] == user) {
            return user;
        }

        return parent[user] = find(parent[user], parent);
    }
}
