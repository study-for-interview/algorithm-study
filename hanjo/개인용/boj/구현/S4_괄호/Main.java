package 구현.S4_괄호;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String[] arr = new String[n];
        for(int i=0; i<n; i++){
            arr[i] = br.readLine();
        }
        
        for(String str : arr){
            System.out.println(solution(str));
        }
    }

    public static String solution(String str){

        Stack<Character> stack = new Stack<>();
        
        for(char c : str.toCharArray()){
            if(!stack.isEmpty() && stack.peek() == '(' && c == ')'){
                stack.pop();
            }
            else{
                stack.add(c);
            }
        }

        if(stack.isEmpty()){
            return "YES";
        }
        else{
            return "NO";
        }
    }
}


/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/9012
 * 날짜 : 220330
 * 성공여부 : 성공
 * 풀이시간 : 10m
 * 
 * 시간복잡도 : O(N)
 * 메모리 : 14380 KB
 * 소요 시간 : 140 ms
 * ================================================================================
 * 
 * 전형적인 스택 문제
 * stack의 peek에서 볼 게 딱 하나밖에 없어서 쉬운문제
 * 
 */