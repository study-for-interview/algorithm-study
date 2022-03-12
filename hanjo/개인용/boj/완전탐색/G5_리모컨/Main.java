package 완전탐색.G5_리모컨;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int channel = Integer.parseInt(br.readLine());   // 채널
        int m = Integer.parseInt(br.readLine());   // 고장난 버튼 수

        if(m == 0){
            System.out.println(Math.min(Math.abs(100 - channel), Integer.toString(channel).length()));
            return;
        }

        int[] broken = new int[m];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<m; i++){
            broken[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(channel, broken));
    }

    public static int min = Integer.MAX_VALUE;

    public static int solution(int channel, int[] broken){

        min = Math.min(min, Math.abs(100 - channel));
        permutation("", channel, broken);

        return min;
    }

    public static void permutation(String str, int channel, int[] broken){

        if(str.length() > Integer.toString(channel).length()+1){
            return;
        }

        if(!str.equals("")){
            min = Math.min(min, Math.abs(Integer.parseInt(str) - channel) + Integer.toString(Integer.parseInt(str)).length());
        }
        
        LOOP : for(int i=0; i<=9; i++){
            for(int num : broken){
                if(num == i){
                    continue LOOP;
                }
            }
            permutation(str + Integer.toString(i), channel, broken);
        }
    }
}

/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/1107
 * 성공여부 : 실패 (1시간 초과)
 * 풀이시간 : 1h 30m
 * 
 * 시간복잡도 : ?
 * 메모리 : 312320 KB
 * 소요 시간 : 1552 ms
 * ================================================================================
 * 
 * 그냥 완전탐색문제.
 * 채널은 0 ~ 50만까지 존재함
 * 만약 목표 채널이 50만이라면 100에서 50만으로 가는것보다, 65만에서 50만으로 가는게 효율적이므로 
 * 누를수 있는 채널은 100만까지 잡아도 된다.
 * 
 * 물론 0~100만까지의 모든 채널을 완전탐색하면서 효율적인 거리를 계산해도 되지만 
 * 이번엔 dfs로 순열을 사용해서 풀어보고 싶었음
 * 근데 String과 int 간 캐스팅이 너무 많아서 타입선택에 실패했다고 생각..
 * 
 * 그리고 반례가 너무나도 많았음. 결국 초기 풀이에서 반례때문에 계속 코드가 더러워져서 기분나쁜 풀이를함..
 * (반례 참고 : https://www.acmicpc.net/board/view/46120)
 * 
 */