package 코테.오늘의집_2022_1.문제1;

import java.util.*;

public class Solution {

    public final List<Character> list = List.of('a', 'b', 'c', 'd');
    
    public int solution(char[][] rounds){
        final int n = rounds.length;
        final int m = 4;

        boolean[][] isCoupled = new boolean[n][m];
        int count = 0;

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                // 사람과 그 사람이 가리킨 사람
                char cur = list.get(j);
                char curPoint = rounds[i][j];
                // 이전 커플 또 지목한 경우
                if(i!=0){
                    if(isCoupled[i-1][j] && curPoint == rounds[i-1][j]){
                        count++;
                        continue;
                    }
                }
                // 자기 자신 지목한 경우
                if(curPoint == cur){
                    count++;
                    continue;
                }
                // 커플 여부 업데이트
                char nextPoint = rounds[i][list.indexOf(curPoint)];
                if(cur == nextPoint){
                    isCoupled[i][j] = true;
                }
            }
        }
        return count;
    }


    public static void main(String[] args){
        // 2
        System.out.println(new Solution().solution(
            new char[][]{
                {'b', 'a', 'a', 'd'},
                {'b', 'c', 'a', 'c'},
                {'b', 'a', 'd', 'c'}
            }
        ));
        // 4
        System.out.println(new Solution().solution(
            new char[][]{
                {'b', 'a', 'd', 'c'},
                {'b', 'a', 'c', 'd'}
            }
        ));
        // 0
        System.out.println(new Solution().solution(
            new char[][]{
                {'b', 'a', 'd', 'c'},
                {'d', 'c', 'b', 'a'},
                {'b', 'a', 'd', 'c'},
            }
        ));
        // 2
        System.out.println(new Solution().solution(
            new char[][]{
                {'d', 'a', 'a', 'a'},
                {'c', 'a', 'a', 'a'},
                {'b', 'a', 'a', 'a'},
            }
        ));
    }
}
