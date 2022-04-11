import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int testCaseQuantity = Integer.parseInt(bufferedReader.readLine());

        for (int testCase = 1; testCase <= testCaseQuantity; testCase++) {
            int userQuantity = Integer.parseInt(bufferedReader.readLine());
            int relationQuantity = Integer.parseInt(bufferedReader.readLine());

            int[] parent = new int[userQuantity];
            for (int number = 0; number < parent.length; number++) {
                parent[number] = number;
            }

            for (int relation = 0; relation < relationQuantity; relation++) {
                String[] input = bufferedReader.readLine().split(" ");

                int userA = Integer.parseInt(input[0]);
                int userB = Integer.parseInt(input[1]);

                union(userA, userB, parent);
            }

            System.out.println("Scenario " + testCase + ":");

            int solveQuantity = Integer.parseInt(bufferedReader.readLine());

            for (int solve = 0; solve < solveQuantity; solve++) {
                String[] input = bufferedReader.readLine().split(" ");
                int userA = Integer.parseInt(input[0]);
                int userB = Integer.parseInt(input[1]);

                int rootA = find(userA, parent);
                int rootB = find(userB, parent);

                if (rootA == rootB) {
                    System.out.println(1);
                } else {
                    System.out.println(0);
                }
            }
            System.out.println();
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
