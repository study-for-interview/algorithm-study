package algorithm.자주쓰는거;

public class 조합 {
    public static void main(String[] args) {
        int n = 4;
        int[] arr = {1, 2, 3, 4};
        boolean[] visited = new boolean[n];

        for (int i = 1; i <= n; i++) {
            System.out.println("\n" + n + " 개 중에서 " + i + " 개 뽑기");
            reculsive(arr, visited, 0, n, i);
        }

        for (int i = 1; i <= n; i++) {
            System.out.println("\n" + n + " 개 중에서 " + i + " 개 뽑기");
            backtracking(arr, visited, 0, n, i);
        }
    }

    // 백트래킹 사용
    // 사용 예시 : combination(arr, visited, 0, n, r)
    static void backtracking(int[] arr, boolean[] visited, int start, int n, int r) {
        if (r == 0) {
            print(arr, visited, n);
            return;
        }

        for (int i = start; i < n; i++) {
            visited[i] = true;
            backtracking(arr, visited, i + 1, n, r - 1);
            visited[i] = false; 
        }  
    }

    // 재귀 사용
    // 사용 예시 : comb(arr, visited, 0, n, r)
    static void reculsive(int[] arr, boolean[] visited, int depth, int n, int r) {
        if (r == 0) {
            print(arr, visited, n);
            return;
        }
        
        if (depth == n) {
            return;
        }

        visited[depth] = true;
        reculsive(arr, visited, depth + 1, n, r - 1);

        visited[depth] = false;
        reculsive(arr, visited, depth + 1, n, r);
    }

    // 배열 출력
    static void print(int[] arr, boolean[] visited, int n) {
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                System.out.print(arr[i] + " ");
            }
        }
        System.out.println();
    }
}