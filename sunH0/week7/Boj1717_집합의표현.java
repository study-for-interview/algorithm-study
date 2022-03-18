import java.util.Scanner;

public class Boj1717_집합의표현 {
    public static int[] parent;
	
    public static int find(int x) {
        if(x == parent[x])
            return x;
        else 
            return parent[x] = find(parent[x]);
    }
    public static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if(x != y) {
            parent[y] = x;
        }
    }
    public static void isSameParent(int x, int y) {
        x = find(x);
        y = find(y);
        if(x == y) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        parent = new int[n+1];
        for(int i = 0; i <= n; i++) {
            parent[i] = i;
        }
        for(int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int x = sc.nextInt();
            int y = sc.nextInt();
            if(a == 0) {
                union(x,y);
            } else {
                isSameParent(x, y);
            }
        }
    }
}