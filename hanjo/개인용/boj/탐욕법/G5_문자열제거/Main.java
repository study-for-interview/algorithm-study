package 탐욕법.G5_문자열제거;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String s = br.readLine();
        int m = Integer.parseInt(br.readLine());

        String[] a = new String[m];
        int[] x = new int[m];
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            a[i] = st.nextToken();
            x[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(s, m, a, x));
    }

    public static class Word implements Comparable<Word>{
        public String target;
        public String replace;
        public int score;
        public int value;

        public Word(String target, int score){
            this.target = target;
            this.score = score;
            replace = convert(target);
            value = score/target.length();
        }

        @Override
        public int compareTo(Word o){
            return o.value - this.value;
        }
    }

    public static int solution(String s, int m, String[] a, int[] x){
        // 변환 문자열 리스트 초기화
        List<Word> words = new ArrayList<>();
        for(int i=0; i<m; i++){
            // 가치가 1 미만인 경우는 저장 X
            if(a[i].length() <= x[i]){
                words.add(new Word(a[i], x[i]));
            }
        }
        Collections.sort(words);

        // 문자열 모두 변환할때까지 순회
        String end = convert(s);
        int score = 0;
        Loop : while(!s.equals(end)){
            // 우선순위대로 순회하면서 하나씩 변환
            for(Word word : words){
                if(s.contains(word.target)){
                    s = s.replaceFirst(word.target, word.replace);
                    score += word.score;
                    continue Loop;
                }
            }
            // 잔챙이들만 남을 경우
            s = s.replace("_", "");
            score += s.length();
            break;
        }

        return score;
    }

    public static String convert(String str){
        String result = "";
        for(int i=0; i<str.length(); i++){
            result += "_";
        }
        return result;
    }
}

/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/21941
 * 날짜 : 220405
 * 성공여부 : 성공
 * 풀이시간 : 40m
 * 
 * 시간복잡도 : 
 * 메모리 : 17776 KB
 * 소요 시간 : 212 ms
 * ================================================================================
 * 
 * 문자열 파싱 + 탐욕법 문제
 * 
 * 문제를 잘읽고 그대로 구현하면 됐음
 * 근데 두가지 조건에서 시행착오 남
 * 
 * 1. 교체 후보를 점수로 정렬하는게 아닌 문자 하나의 가치(점수/문자열길이) 순서로 내림차순해야함
 *      -> ex) abc : 6 / a : 5 일때 a가 우선순위
 * 2. 점수가 교체 후보 문자열 길이보다 작으면 교체 대상 X
 *      -> 가치가 1미만이 되는 경우
 * 
 */