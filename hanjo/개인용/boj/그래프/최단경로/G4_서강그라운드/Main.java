package 그래프.최단경로.G4_서강그라운드;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());   // 정점 수
        int m = Integer.parseInt(st.nextToken());   // 수색범위
        int r = Integer.parseInt(st.nextToken());   // 간선 수

        int[] items = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++){
            items[i] = Integer.parseInt(st.nextToken());
        }

        int[][] edges = new int[r][3];
        for(int i=0; i<r; i++){
            st = new StringTokenizer(br.readLine());
            edges[i][0] = Integer.parseInt(st.nextToken());
            edges[i][1] = Integer.parseInt(st.nextToken());
            edges[i][2] = Integer.parseInt(st.nextToken());
        }
        
        System.out.println(solution(m, items, edges));
    }

    // 정점수^3
    public static final int INF = 100*100*100 + 1;

    public static int solution(int m, int[] items, int[][] edges){

        int n = items.length - 1;

        int[][] dist = new int[n+1][n+1];
        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                if(i == j){
                    dist[i][j] = 0;
                }
                else{
                    dist[i][j] = INF;
                }
            }
        }
        for(int[] edge : edges){
            dist[edge[0]][edge[1]] = edge[2];
            dist[edge[1]][edge[0]] = edge[2];
        }

        floydWarshall(n, dist);

        for(int[] d : dist){
            System.out.println(Arrays.toString(d));
        }

        int max = 0;
        for(int i=1; i<=n; i++){
            int sum = 0;
            for(int j=1; j<=n; j++){
                if(dist[i][j] <= m){
                    sum += items[j];
                }
            }
            if(sum > max){
                max = sum;
            }
        }
        
        return max;
    }

    public static void floydWarshall(int n, int[][] dist){
        
        for(int k=1; k<=n; k++){
            for(int i=1; i<=n; i++){
                for(int j=1; j<=n; j++){
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
    }
    
}

/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/14938
 * 성공여부 : 성공
 * 풀이시간 : 40m
 * 
 * 시간복잡도 : 플로이드와샬 - O(n^3)
 * 메모리 : 14348 KB
 * 소요 시간 : 140 ms
 * ================================================================================
 * 
 * 모든 정점에서의 최단경로를 구할수만 있다면 쉬운 문제
 * 위 풀이는 플로이드 와샬로 풀었는데, 간만에 다시보니 또 가물가물했다.
 * 
 * 1. 모든 최단경로를 구하고
 * 2. 해당 경로에서 수색범위 안쪽인 정점만 골라줌
 * 3. 그 정점의 아이템 수 합계가 최대가 되는 경로를 선택
 * 
 * < 플로이드 와샬 >
 * 
 * 1. dist 배열을 초기화해준다. (자기자신은 0, 연결된정점은 가중치, 나머지는 INF)
 *      => 이때 INF 범위를 잘 고려해야한다
 * 2. i -> j 경로와 i -> k -> j 경로 중 큰 것으로 업데이트해주기 (다이나믹 프로그래밍)
 * 
 */