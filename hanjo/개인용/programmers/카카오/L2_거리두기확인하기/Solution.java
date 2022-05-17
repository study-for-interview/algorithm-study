package 카카오.L2_거리두기확인하기;

import java.util.Arrays;

class Solution {

    public final int n = 5;
    public final int[] dx = {-1, 0, 1, 0};
    public final int[] dy = {0, 1, 0, -1};

    public int[] solution(String[][] places) {
        int[] answer = new int[n];
        for(int i=0; i<n; i++){
            char[][] place = convert(places[i]);
            answer[i] = isValid(place);
        }
        return answer;
    }

    public char[][] convert(String[] arr){
        char[][] place = new char[n][n];
        for(int i=0; i<n; i++){
            place[i] = arr[i].toCharArray();
        }
        return place;
    }

    public int isValid(char[][] place){
        for(int x=0; x<n; x++){
            for(int y=0; y<n; y++){
                if(place[x][y] != 'P'){
                    continue;
                }
                for(int i=0; i<4; i++){
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if(isOut(nx, ny) || place[nx][ny] == 'X'){
                        continue;
                    }
                    if(place[nx][ny] == 'P'){
                        return 0;
                    }
                    for(int j=0; j<4; j++){
                        int nnx = nx + dx[j];
                        int nny = ny + dy[j];
                        if(isOut(nnx, nny) || (i+2)%4 == j){
                            continue;
                        }
                        if(place[nnx][nny] == 'P'){
                            return 0;
                        }
                    }
                }
            }
        }
        return 1;
    }

    public boolean isOut(int x, int y){
        return x < 0 || x >= n || y < 0 || y >= n;
    }

    public static void main(String[] args){
        // [1, 0, 1, 1, 1]
        System.out.println(Arrays.toString(new Solution().solution(
            new String[][]{
                {"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
                {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
                {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
                {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
                {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}
            }
        )));
    }
}

/**
 * ================================================================================
 * 링크 : https://programmers.co.kr/learn/courses/30/lessons/81302
 * 날짜 : 220422
 * 성공여부 : 성공
 * 풀이시간 : 50m
 * 
 * 시간복잡도 : 케이스 순회 5 / 문자열 변환 5*5 / 검사 (5*5)*4*3
 * ================================================================================
 * 
 * 단순 구현문제
 * 
 * 1. 일단 문자열을 char 배열로 변환
 * 2. P인 좌표에서 상하좌우를 검사 
 *      -> X는 노상관, P이면 붙어있으므로 격리안됨
 * 3. 만약 O라면 여기서 상하좌우 중 세칸을 검사
 *      -> 단 2번에서의 P는 제외해야 함
 *      -> 여기서 P가 나오면 맨해튼 거리에서 격리 안된것
 * 
 */