package 그래프.최단경로.G3_파티;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        int[][] edges = new int[m][3];
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            edges[i][0] = Integer.parseInt(st.nextToken());
            edges[i][1] = Integer.parseInt(st.nextToken());
            edges[i][2] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(n, x, edges));
    }

    public static final int INF = 1000*1000*1000+1;

    public static int solution(int n, int x, int[][] edges){

        // dist 배열 초기화
        int[][] dist = new int[n+1][n+1];
        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                if(i==j){
                    dist[i][j] = 0;
                }
                else{
                    dist[i][j] = INF;
                }
            }
        }
        for(int[] edge : edges){
            dist[edge[0]][edge[1]] = edge[2];
        }

        // 플로이드 와샬
        floydWarshall(n, dist);

        // 최단경로로 왕복 중 가장 큰 값  
        int max = 0;
        for(int i=1; i<=n; i++){
            // 갈때 + 올때
            max = Math.max(max, dist[i][x] + dist[x][i]);
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
 * 링크 : https://www.acmicpc.net/problem/1238
 * 성공여부 : 성공
 * 풀이시간 : 15m
 * 
 * 시간복잡도 : O(n^3)
 * 메모리 : 20752 KB
 * 소요 시간 : 2236 ms
 * ================================================================================
 * 
 * 단방향 그래프 + 모든 정점에서 모든 정점의 최단경로 문제
 * 따라서 플로이드와샬 알고리즘을 사용
 * 최단경로 배열 dist에 모든 정보가 있으므로, dist만 구하면 매우 쉬움
 * 
 */