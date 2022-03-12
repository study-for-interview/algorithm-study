package 그래프.최단경로.G5_호석이두마리치킨;

import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());   // 정점 수
        int m = Integer.parseInt(st.nextToken());   // 간선 수

        int[][] edges = new int[m][2];
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            edges[i][0] = Integer.parseInt(st.nextToken());
            edges[i][1] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(n, edges));
    }

    public static final int INF = 100*100*100 + 1;

    public static String solution(int n, int[][] edges){

        // 정점간 최단거리 배열 초기화
        int[][] dist = new int[n+1][n+1];
        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                if(i == j)
                    dist[i][j] = 0;
                else
                    dist[i][j] = INF;
            }
        }
        for(int[] edge : edges){
            dist[edge[0]][edge[1]] = 1;
            dist[edge[1]][edge[0]] = 1;
        }

        // 최단거리 구하기
        floydWarshall(n, dist);

        // 두 정점을 정한 후 나머지 정점의 최소비용 구하기
        int[] vertices = new int[2];
        int shortestPath = INF;
        for(int i=1; i<=n; i++){
            for(int j=i+1; j<=n; j++){
                int sum = 0;
                for(int k=1; k<=n; k++){
                    // 두 치킨집 중 하나까지의 최소비용
                    if(k!=i && k!=j){
                        sum += Math.min(dist[k][i], dist[k][j]);
                    }
                }
                // 정점이 작은것이 우선이므로 같을땐 변경X
                if(sum < shortestPath){
                    shortestPath = sum;
                    vertices[0] = i;
                    vertices[1] = j;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        return sb.append(vertices[0]).append(" ")
                .append(vertices[1]).append(" ")
                .append(shortestPath * 2)
                .toString();
    }

    public static void floydWarshall(int n, int[][] dist){
        // k - 거쳐야하는 정점
        // i - 출발 정점
        // j - 도착 정점
        for(int k=1; k<=n; k++){
            for(int i=1; i<=n; i++){
                for(int j=1; j<=n; j++){
                    // 기존 i->j 비용과 i->k->j 비용을 비교
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
    }
}

/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/21278
 * 성공여부 : 실패
 * 풀이시간 : 2H
 * 
 * 시간복잡도 : 플로이드와샬 - O(V^3) / 완전탐색 - O(V^3)
 * 메모리 : 15480 KB
 * 소요 시간 : 220 ms
 * ================================================================================
 * 
 * 최단경로(최소비용) + 완전탐색 문제임
 * 플로이드 와샬을 사용해서 정점별 최단경로를 모두 구한 후 해당 정보를 가지고 완전탐색하면됨
 * 
 * 최소비용까지 구하는건 쉬웠는데 그 정보를 가지고 어떻게 구현할지를 떠올리는게 어려웠음,..
 * 
 */