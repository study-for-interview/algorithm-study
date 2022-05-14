package 카카오.L3_파괴되지않은건물;

class Solution {

    public int solution(int[][] board, int[][] skill) {
        int n = board.length;
        int m = board[0].length;

        int[][] diff = new int[n+1][m+1];

        // 누적합을 할 배열 구하기
        for(int[] s : skill){
            int degree = s[0] == 1 ? -1*s[5] : s[5];
            int r1 = s[1];
            int c1 = s[2];
            int r2 = s[3];
            int c2 = s[4];

            diff[r1][c1] += degree;
            diff[r2+1][c2+1] += degree;
            diff[r1][c2+1] -= degree;
            diff[r2+1][c1] -= degree;
        }

        // 누적합
        for(int x=0; x<n; x++){
            for(int y=1; y<m; y++){
                diff[x][y] += diff[x][y-1];
            }
        }
        for(int y=0; y<m; y++){
            for(int x=1; x<n; x++){
                diff[x][y] += diff[x-1][y];
            }
        }

        // 빼기
        int count = 0;
        for(int x=0; x<n; x++){
            for(int y=0; y<m; y++){
                if(board[x][y] + diff[x][y] > 0){
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        // 10
        System.out.println(new Solution().solution(
            new int[][]{
                {5,5,5,5,5},
                {5,5,5,5,5},
                {5,5,5,5,5},
                {5,5,5,5,5}
            },
            new int[][]{
                {1,0,0,3,4,4},
                {1,2,0,2,3,2},
                {2,1,0,3,1,2},
                {1,0,1,3,3,1}
            }
        ));
        // 6
        System.out.println(new Solution().solution(
            new int[][]{
                {1,2,3},
                {4,5,6},
                {7,8,9}
            },
            new int[][]{
                {1,1,1,2,2,4},
                {1,0,0,1,1,2},
                {2,2,0,2,0,100}
            }
        ));
    }
}

/**
 * ================================================================================
 * 링크 : https://programmers.co.kr/learn/courses/30/lessons/92344
 * 날짜 : 220421
 * 성공여부 : 실패 (답 봄)
 * 풀이시간 : 1h
 * 
 * 시간복잡도 : 
 * ================================================================================
 * 
 * 누적합 개념을 어떻게 활용할지(아이디어) + 2차원배열에서 어떻게 구현할지에 대한 문제 => imos 방법
 * 
 * 효율성 고려 안하고 완탐하면 O(스킬수*N*M)으로 해결가능하지만
 * 고려한다면 엄청난 시간이 소요됨 -> O(250_000 * 1_000 * 1_000) = O(250_000_000_000) 
 * 
 * 따라서 누적합을 사용해서 O(스킬수 + N*M) 으로 줄여야한다.
 * 여기서 누적합을 어디에 어떻게 사용할지 생각해 내는게 키포인트였음
 *  -> 스킬 사용의 결과들을 모두 누적합 한 다음 기존 배열과 계산하는 것
 *  -> 그럼 어떻게 누적합하느냐?는 아래 해설에서...
 * 
 * https://tech.kakao.com/2022/01/14/2022-kakao-recruitment-round-1/
 * 
 */