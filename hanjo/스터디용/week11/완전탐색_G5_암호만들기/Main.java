package week11.완전탐색_G5_암호만들기;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int l = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        String[] arr = new String[c];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<c; i++){
            arr[i] = st.nextToken();
        }

        solution(l, arr);
    }

    public static int L;
    public static int C;
    public static String[] strings;
    public static List<String> comb;
    public static final List<String> vowels = List.of("a", "e", "i", "o", "u");

    public static void solution(int l, String[] arr){
        L = l;
        C = arr.length;
        strings = arr;
        Arrays.sort(arr);
        
        comb = new ArrayList<>();
        combination(0, "", 0, 0);

        for(String str : comb){
            System.out.println(str);
        }
    }

    public static void combination(int cur, String str, int vowel, int consonant){        
        if(str.length() == L){
            if(vowel == 1 && consonant == 2){
                comb.add(str);
            }
            return;
        }
        if(cur == C){
            return;
        }

        // 건너뛰기
        combination(cur+1, str, vowel, consonant);
        
        // 추가하기
        if(isVowel(strings[cur])){
            if(vowel < 1){
                vowel++;
            }
        }
        else{
            if(consonant < 2){
                consonant++;
            }
        }
        combination(cur+1, str + strings[cur], vowel, consonant);
    }

    public static boolean isVowel(String str){
        if(vowels.contains(str)){
            return true;
        }
        return false;
    }
    
}

/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/1759
 * 날짜 : 220321
 * 성공여부 : 실패 (1시간 초과)
 * 풀이시간 : 1h 30m
 * 
 * 시간복잡도 : 
 * 메모리 : 17352 KB
 * 소요 시간 : 180 ms
 * ================================================================================
 * 
 * 단순 dfs 완전탐색 조합 문제
 * 일반 조합과 차이점은 조합시 조건이 붙어있다는 점밖에 없고 동작원리는 똑같다.
 * 
 * 근데 자음도 2개이상이어야 한다는 조건을 못봐서 삽질했음. 문제 제대로 읽자
 * 
 */