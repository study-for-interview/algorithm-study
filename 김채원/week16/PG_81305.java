import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

class Solution {
    static int l[] = new int[10005]; // 왼쪽 자식 노드 번호
    static int r[] = new int[10005]; // 오른쪽 자식 노드 번호
    static int x[] = new int[10005]; // 시험장의 응시 인원
    static int p[] = new int[10005]; // 부모 노드 번호
    static int n; // 노드의 수
    static int root; // 루트
    static int cnt = 0;

    static int dfs(int cur, int sum) {
        int left = 0;
        if (l[cur] != -1) left = dfs(l[cur],sum);
        int right = 0;
        if (r[cur] != -1) right = dfs(r[cur],sum);

        if(x[cur] + left + right  <= sum) return x[cur] + left + right;
        if (x[cur] + Math.min(left, right) <= sum) {
            cnt++;
            return (x[cur] + Math.min(left, right));
        }
        cnt += 2;
        return x[cur];
    }

    static int isDivided(int sum) {
        cnt = 0;
        dfs(root, sum);
        cnt++;
        return cnt;
    }


    public int solution(int k, int[] num, int[][] links) {
        n  = num.length;
        for(int i = 0; i < n; i++) p[i] = -1;
        for (int i = 0; i < n; i++) {
            l[i] = links[i][0];
            r[i] = links[i][1];
            x[i] = num[i];
            if(l[i]!= -1) p[l[i]] = i;
            if(r[i]!= -1) p[r[i]] = i;
        }
        //루트노드 찾기
        for (int i = 0; i < n; i++) {
            if (p[i] == -1) {
                root = i;
                break;
            }
        }

        int st = x[0];
        for(int i = 0; i < n; i++) st = Math.max(st, x[i]);
        int en = (int)1e8;
        while(st < en){
            int mid = (st+en)/2;
            if(isDivided(mid) <= k) en = mid;
            else st = mid+1;
        }
        System.out.println(st);
        return st;
    }


    public static void main(String[] args) {
        Solution T = new Solution();
        int n = 3;
        int[] num = {12, 30, 1, 8, 8, 6, 20, 7, 5, 10, 4, 1};
        int[][] links = {{-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {8, 5}, {2, 10}, {3, 0}, {6, 1}, {11, -1}, {7, 4}, {-1, -1}, {-1, -1}};

        T.solution(n, num, links);

    }
}
