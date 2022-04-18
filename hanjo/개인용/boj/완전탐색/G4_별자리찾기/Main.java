package 완전탐색.G4_별자리찾기;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int m = Integer.parseInt(br.readLine());
        int[][] target = new int[m][2];
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            target[i][0] = Integer.parseInt(st.nextToken());
            target[i][1] = Integer.parseInt(st.nextToken());
        }

        int n = Integer.parseInt(br.readLine());
        int[][] photo = new int[n][2];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            photo[i][0] = Integer.parseInt(st.nextToken());
            photo[i][1] = Integer.parseInt(st.nextToken());
        }
        
        solution(target, photo);
    }

    public static void solution(int[][] target, int[][] photo){
        int m = target.length;
        int n = photo.length;

        // target 좌표에서 photo 모든 좌표로의 거리를 구함 
        ArrayList<ArrayList<int[]>> dist = new ArrayList<>();
        for(int i=0; i<m; i++){
            dist.add(new ArrayList<>());
            for(int j=0; j<n; j++){
                int x = photo[j][0] -target[i][0];
                int y = photo[j][1] -target[i][1];
                dist.get(i).add(new int[]{x, y});
            }
        }

        // 거리 리스트에서 모두 등장하는 거리를 구함
        for(int[] dist1 : dist.get(0)){
            int count = 1;
            for(int i=1; i<m; i++){
                boolean isExist = false;
                for(int[] dist2 : dist.get(i)){
                    if(dist1[0] == dist2[0] && dist1[1] == dist2[1]){
                        isExist = true;
                        break;
                    }
                }
                if(isExist){
                    count++;
                }
            }
            if(count == m){
                System.out.println(dist1[0] + " " + dist1[1]);
                return;
            }
        }
    }
}

/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/5588
 * 성공여부 : 실패 (1시간 초과)
 * 풀이시간 : 2h
 * 
 * 시간복잡도 : O(n*m) + O(m^2)
 * 메모리 : 34616KB
 * 소요 시간 : 1148ms
 * ================================================================================
 * 
 * 구현이 살짝 까다로운 완전탐색 문제. 삽질하는 바람에 2시간걸림
 * target 좌표를 움직여서 photo에 딱 맞을때를 찾으면 된다.
 * 
 * 현재 풀이는 target 좌표에서 photo 모든 좌표로의 거리를 구하고, 거리 리스트에서 공통된 거리를 찾아낸다
 * 근데 이렇게 나눠서 두번 탐색하니까 소요시간이 오래걸림
 * 따라서 거리를 구할때 
 * 1. 첫번째 for문에서 첫번째 target좌표와 photo 좌표들간의 거리를 구하고
 * 2. 두번째 for문에서 나머지 target좌표를 순회하면서 1번에서 구한만큼의 거리를 이동시켜보면 될듯
 * 
 * Try1에서 dfs로 target을 싹다 움직여서 찾으려고 했는데, 
 * dfs 종료 조건이 명확하지 않아서 엄청난 경우의 수를 찾게 됨.....
 * 
 */