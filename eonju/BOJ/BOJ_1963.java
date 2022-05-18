import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


class Main {

    static int testCase;
    static boolean[] primeNumber = new boolean[10000];
    static int answer;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        testCase = sc.nextInt();
        makePrimeNumber();
        int a, b;

        while (testCase-- > 0) {
            answer = Integer.MAX_VALUE;
            a = sc.nextInt();
            b = sc.nextInt();
            System.out.println(solve(a, b) == Integer.MAX_VALUE ? "Impossible" : answer);
        }
    }

    public static void makePrimeNumber() {
        for (int i = 2; i <= 9999; i++) {
            if (primeNumber[i] == false) {
                for (int j = 2 * i; j <= 9999; j += i) {
                    primeNumber[j] = true;
                }
            }
        }
    }

    public static int solve(int a, int b) {
        boolean[] visited = new boolean[10000];
        visited[a] = true;
        Queue<Prime> q = new LinkedList<>();
        q.add(new Prime(a, 0));
        while (!q.isEmpty()) {
            Prime p = q.poll();

            if (p.number == b) {
                answer = Math.min(answer, p.cnt);
            }

            for (int i = 0; i <= 9; i++) {
                if (i != p.get4()) {
                    int next = i * 1000 + p.get3() * 100 + p.get2() * 10 + p.get1();
                    if (1000 <= next && !primeNumber[next] && !visited[next]) {
                        visited[next] = true;
                        q.add(new Prime(next, p.cnt + 1));
                    }
                }
                if (i != p.get3()) {
                    int next = p.get4() * 1000 + i * 100 + p.get2() * 10 + p.get1();
                    if (1000 <= next && !primeNumber[next] && !visited[next]) {
                        visited[next] = true;
                        q.add(new Prime(next, p.cnt + 1));
                    }
                }
                if (i != p.get2()) {
                    int next = p.get4() * 1000 + p.get3() * 100 + i * 10 + p.get1();
                    if (1000 <= next && !primeNumber[next] && !visited[next]) {
                        visited[next] = true;
                        q.add(new Prime(next, p.cnt + 1));
                    }
                }
                if (i != p.get1()) {
                    int next = p.get4() * 1000 + p.get3() * 100 + p.get2() * 10 + i;
                    if (1000 <= next && !primeNumber[next] && !visited[next]) {
                        visited[next] = true;
                        q.add(new Prime(next, p.cnt + 1));
                    }
                }
            }
        }

        return answer;
    }


    static class Prime {

        int number;
        int cnt;

        public Prime(int number, int cnt) {
            this.number = number;
            this.cnt = cnt;
        }

        public int get1() {
            return number % 10;
        }

        public int get2() {
            return (number % 100) / 10;
        }

        public int get3() {
            return (number % 1000) / 100;
        }

        public int get4() {
            return (number) / 1000;
        }
    }
}
