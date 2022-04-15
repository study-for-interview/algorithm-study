package 탐욕법.G4_PPAP;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String str = br.readLine();
        System.out.println(solution(str));
    }

    public static String solution(String str){

        StringBuilder ppap = new StringBuilder();

        for(String s : str.split("")){
            ppap.append(s);
            int n = ppap.length();
            if(n >= 4){
                if(ppap.substring(n-4, n).equals("PPAP")){
                    ppap.replace(n-4, n, "P");
                }
            }
        }

        if(ppap.toString().equals("P")){
            return "PPAP";
        }
        else{
            return "NP";
        }
    }
    
}

/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/16120
 * 날짜 : 220330
 * 성공여부 : 성공
 * 풀이시간 : 40m
 * 
 * 시간복잡도 : ?
 * 메모리 : 122964 KB
 * 소요 시간 : 620 ms
 * ================================================================================
 * 
 * 스택 + 탐욕법 문제 
 * 
 * 처음에 그냥 replace로 조졌다가 바로 시간초과
 * 스택을 써야겠구나 생각했는데 문자열을 스택처럼 써서 직관적으로 풀었다
 * 근데 메모리를 좀 많이쓰긴했다
 * 
 */