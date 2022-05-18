// package week19.비트마스킹_G4_게리맨더링;

// import java.io.*;
// import java.util.*;

// public class Main {

//     public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         StringTokenizer st;

//         int n = Integer.parseInt(br.readLine());

//         int[] population = new int[n];
//         st = new StringTokenizer(br.readLine());
//         for(int i=0; i<n; i++){
//             population[i] = Integer.parseInt(st.nextToken());
//         }

//         Map<Integer, List<Integer>> graph = new HashMap<>();
//         for(int i=0; i<n; i++){
//             graph.put(i, new ArrayList<>());
//             st = new StringTokenizer(br.readLine());
//             int m = Integer.parseInt(st.nextToken());
//             for(int j=0; j<m; j++){
//                 int v = Integer.parseInt(st.nextToken()) - 1;
//                 graph.get(i).add(v);
//             }
//         }
        
//         System.out.println(new Solution().solution(population, graph));
//     }


//     public static class Solution {

//         public int n;
//         public int[] population;
//         public Map<Integer, List<Integer>> graph;

//         public int solution(int[] population, Map<Integer, List<Integer>> graph){
//             n = population.length;
//             this.population = population;
//             this.graph = graph;

//             // 구역 나누기 -> 비트 마스크 조합 
//             int maxMask = (1 << n) - 1;
//             for(){

//             }

//             return 0;
//         }
//     }
    
// }


// /**
//  * ================================================================================
//  * 링크 : 
//  * 날짜 : 
//  * 성공여부 : 
//  * 풀이시간 : 
//  * 
//  * 시간복잡도 : 
//  * 메모리 : KB
//  * 소요 시간 : ms
//  * ================================================================================
//  * 
//  * 
//  */