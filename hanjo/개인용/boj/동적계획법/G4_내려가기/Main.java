package 동적계획법.G4_내려가기;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][3];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            map[i][0] = Integer.parseInt(st.nextToken());
            map[i][1] = Integer.parseInt(st.nextToken());
            map[i][2] = Integer.parseInt(st.nextToken());
        }

        solution(n, map);
    }

    public static void solution(int n, int[][] map){
        // dp 초기화
        int[] maxDp = new int[]{map[0][0], map[0][1], map[0][2]};
        int[] minDp = new int[]{map[0][0], map[0][1], map[0][2]};

        for(int i=1; i<n; i++){
            // max
            int maxLeft = Math.max(maxDp[0], maxDp[1]);
            int maxRight = Math.max(maxDp[1], maxDp[2]);
            int maxAll = Math.max(maxLeft, maxRight);
            maxDp[0] = map[i][0] + maxLeft;
            maxDp[1] = map[i][1] + maxAll;
            maxDp[2] = map[i][2] + maxRight;
            // min
            int minLeft = Math.min(minDp[0], minDp[1]);
            int minRight = Math.min(minDp[1], minDp[2]);
            int minAll = Math.min(minLeft, minRight);
            minDp[0] = map[i][0] + minLeft;
            minDp[1] = map[i][1] + minAll;
            minDp[2] = map[i][2] + minRight;
        }

        int max = Math.max(maxDp[0], Math.max(maxDp[1], maxDp[2]));
        int min = Math.min(minDp[0], Math.min(minDp[1], minDp[2]));
        System.out.println(max + " " + min);
    }
}

/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/2096
 * 성공여부 : 실패 (구글링함)
 * 풀이시간 : 30m
 * 
 * 시간복잡도 : 
 * 메모리 : 45564 KB
 * 소요 시간 : 444 ms
 * ================================================================================
 * 
 * 그냥 DP 문제.
 *
 * 문제를 처음 봤을때는 dfs로 완전탐색을 해야하나? 라는 생각이 들었다.
 * 근데 N의 최대값이 100_000이므로 O(N^2) -> 10_000_000_000 ... 
 * 이럴땐 절대 완전탐색을 생각하면 안된다. 바로 탐욕법이나 DP라는것을 알아차려야 한다. 
 * 근데 도저히 어떻게 dp를 구현할지 떠오르지 않아서 찾아봄.
 * 
 * 역시 창의력이 중요한것 같다. (아니면 경험이 많던가)
 * 
 * - 우선 첫줄을 dp[3] 배열에 저장한다 -> 첫번째줄은 이미 거쳐갔다고 생각함
 * - 두번째줄부터 무조건 거쳐야하는 정점인 것이다. 그럼 두번째 row[0]은 어디서 올수 있는지 생각해본다.
 * - 문제에서 주어진것처럼 0과 1에서 올수 있다. 
 * - 그럼 dp[0]과 dp[1] 중에서 큰 수를 선택하고, row[0]을 방문했다 생각하며 dp[0]을 row[0] + 큰값으로 업데이트시킨다.
 * - 이런식으로 반복하면 맨마지막 dp는 가장 큰 경로들로 채워진다.
 * 
 * 참고 : https://yhwan.tistory.com/14
 * 
 */