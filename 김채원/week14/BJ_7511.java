import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m, t;
    static int[] parent;
    static boolean[] answer;

    static int find(int x) {
        if (parent[x] == x) // 배열 인덱스와 값이 같다면 해당 값 리턴
            return x;
        return parent[x] = find(parent[x]); // 배열의 값을 인덱스로 갖는 값 리턴
    }

    static void merge(int x, int y) {

        x = find(x);
        y = find(y); // 각각 find연산을 통해 루트 노드를 가짐
        if (x == y) return; // x와 y가 같다면 이미 연결되어있는 것
        parent[y] = x; // 배열의 y인덱스에 x값을 저장

    }

    static boolean isUnion(int x, int y) { // 두 노드가 연결되어있는지 판별하는 함수
        x = find(x);
        y = find(y);
        if (x == y) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            n = Integer.parseInt(br.readLine()); //사람수
            parent = new int[n];
            for (int s = 0; s < n; s++) {
                parent[s] = s;
            } // 배열 초기화 과정

            int userCount  = Integer.parseInt(br.readLine()); // 연결된 사람수
            for (int j = 0; j < userCount; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                merge(a,b);
            }
            int result  = Integer.parseInt(br.readLine());
            answer = new boolean[result];
            for (int j = 0; j < result; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                answer[j] = isUnion(a,b);
            }
            int tmp = i+1;
            System.out.println("Scenario " + tmp + ":");
            for (int j = 0; j < answer.length; j++) {
                if(answer[j]) System.out.println(1);
                else System.out.println(0);
            }
            System.out.println();
        }
    }
}