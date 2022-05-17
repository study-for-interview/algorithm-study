package 백트래킹.G5_0만들기;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        
        for(int i=0; i<t; i++){
            int n = Integer.parseInt(br.readLine());
            System.out.println(solution(n));
        }

    }

    public static int N;
    public static int[] exp;
    public static StringBuilder sb;

    public static String solution(int n){
        N = n;
        exp = new int[n];
        sb = new StringBuilder();

        exp[0] = 2;
        dfs(0);

        return sb.toString();
    }
    
    public static void dfs(int depth){
        if(depth+1 == N){
            if(isZero()){
                for(int i=1; i<=N; i++){
                    sb.append(i);
                    if(i==N){
                        break;
                    }
                    if(exp[i] == 1){
                        sb.append(" ");
                    }
                    else if(exp[i] == 2){
                        sb.append("+");
                    }
                    else{
                        sb.append("-");
                    }
                }
                sb.append("\n");
            }
            return;
        }

        // 공백
        exp[depth+1] = 1;
        dfs(depth+1);
        // 더하기
        exp[depth+1] = 2;
        dfs(depth+1);
        // 빼기
        exp[depth+1] = 3;
        dfs(depth+1);
    }

    public static boolean isZero(){
        int sum = 0;
        int i=0;
        while(i<N){
            int cur = i;
            int num = i+1;
            // 바로 뒤 연산이 공백이면 뒤에 다합침
            for(int j=i+1; j<N; j++){
                if(exp[j] == 1){
                    num *= 10;
                    num += j+1;
                    i = j;
                }
                else{
                    break;
                }
            }
            // 더하기
            if(exp[cur] == 2){
                sum += num;
            }
            // 빼기
            else if(exp[cur] == 3){
                sum -= num;
            }
            i++;
        }

        return sum == 0;
    }
    
}

/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/7490
 * 날짜 : 220419
 * 성공여부 : 성공
 * 풀이시간 : 1h
 * 
 * 시간복잡도 : 
 * 메모리 : 14148 KB
 * 소요 시간 : 128 ms
 * ================================================================================
 * 
 * dfs 조합 문제
 * 
 * 모든 연산(3가지 종류)의 경우의수를 구하고 조건을 만족하는 경우에만 출력하면된다
 * 연산 종류는 int 배열로 구분하도록함 -> 1 공백 2 더하기 3 빼기
 * 배열 인덱스 0은 더하기로 고정시켜줌
 * 
 * 뒤에 공백이 나올경우 숫자를 합쳐주는 풀이를 생각해내는게 좀 어려웠음
 * 
 */