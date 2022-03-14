package 코테.sk_2022.문제2;

import java.util.Arrays;

public class Solution {

    public static int[] dx;
    public static int[] dy;

    public static int[][] solution(int n, boolean clockwise){
        int[][] map = new int[n][n];

        // 시계
        if(clockwise){
            dx = new int[]{0, 1, 0, -1};
            dy = new int[]{1, 0, -1, 0};
            fill(0,0,0,map);
            fill(0,n-1,1,map);
            fill(n-1,n-1,2,map);
            fill(n-1,0,3,map);
        }
        // 반시계
        else{
            dx = new int[]{1, 0, -1, 0};
            dy = new int[]{0, 1, 0, -1};
            fill(0,0,0,map);
            fill(n-1,0,1,map);
            fill(n-1,n-1,2,map);
            fill(0,n-1,3,map);
        }
        return map;
    }

    public static void fill(int x, int y, int dir, int[][] map){
        int n = map.length;
        int count = 1;
        int padding = 1;
        while(true){
            // 채우기
            map[x][y] = count;
            // 안쪽까지 왔을 때 종료
            if(n%2==0){
                if((x == n/2-1 && y == n/2-1) || (x == n/2-1 && y == n/2) ||
                (x == n/2 && y == n/2-1) || (x == n/2 && y == n/2)){
                    break;
                }
            }
            else{
                if(x == n/2 && y == n/2){
                    break;
                }
            }
            // 여백 검사 -> 방향 변경
            if(dir%4 <= 1){
                if(n - 1 - x == padding || n - 1 - y == padding){
                    padding++;
                    dir++;
                }
            }
            else{
                if(x == padding || y == padding){
                    padding++;
                    dir++;
                }
            }
            // 이동
            x += dx[dir%4];
            y += dy[dir%4]; 
            count++;
        }
    }

    public static void main(String args[]){

        for(int[] arr : solution(5, true)){
            System.out.println( Arrays.toString(arr));
        }

        for(int[] arr : solution(6, false)){
            System.out.println( Arrays.toString(arr));
        }

        for(int[] arr : solution(9, false)){
            System.out.println( Arrays.toString(arr));
        }
    }
}

// 그냥 개빡구현 문제;