package week10.카카오_3_브라이언의고민;

import java.util.*;

class Solution {

    public static final String INVALID = "invalid";

    public static String solution(String sentence) {
        int n = sentence.length();
        char[] chars = sentence.toCharArray();

        LinkedList<String> result = new LinkedList<>();
        HashSet<Character> lowers = new HashSet<>();

        int leftU = 0;
        int cur = 0;
        while(true){
            if(cur >= n){
                result.add(String.valueOf(Arrays.copyOfRange(chars, leftU, n)));
                break;
            }
            // System.out.println(leftU + " " + cur);
            // System.out.println(result);

            // 대문자라면 계속감
            if(Character.isUpperCase(chars[cur])){
                leftU = cur;
                cur++;
                continue;
            }

            // 
            int leftL = cur;
            int rightL = cur;
            int count = 0;
            if(lowers.contains(chars[cur])){
                return INVALID;
            }
            lowers.add(chars[cur]);
            // 오른쪽 포인터 이동
            for(int i=leftL; i<n; i++){
                if(Character.isLowerCase(chars[i])){
                    if(chars[i] == chars[cur]){
                        rightL = i;
                        count++;
                    }
                    else{
                        break;
                    }
                }
            }
            
            // 규칙 2 
            if(count == 2){
                // 쌓이던 대문자 저장
                result.add(String.valueOf(Arrays.copyOfRange(chars, leftU, Math.max(0,cur))));
                result.add(removeLower(leftL, rightL, chars[leftL], chars));
                cur = rightL + 1;
            }
            // 규칙 1
            else{
                // 안쌓이고 바로 오면..?
                if(leftU != 0 && leftU==cur){
                    return INVALID;
                }
                // 쌓이던 대문자 저장
                result.add(String.valueOf(Arrays.copyOfRange(chars, leftU, Math.max(0,cur-1))));
                // if(countleftL-1 == 0){
                //     return INVALID;
                // }
                result.add(removeLower(leftL-1, rightL+1, chars[leftL], chars));
                cur = rightL + 2;
            }
            leftU = cur;

        }
        // System.out.println(result);

        String answer = "";
        for(String str : result){
            if(!str.equals("")){
                answer += str + " ";
            }
            
        }
        

        return answer;
    }

    public static String removeLower(int left, int right, char lower, char[] chars){
        // char 배열 자르기 -> str 변환
        String str = String.valueOf(Arrays.copyOfRange(chars, left, right+1));
        // lower(특수문자) 제거
        return str.replace(String.valueOf(lower), "");
    }

    public static void main(String args[]){
        // HELLO WORLD
        System.out.println(solution("HaEaLaLaObWORLDb"));
        // SIGONG J O A
        System.out.println(solution("SpIpGpOpNpGJqOqA"));
        // invalid
        System.out.println(solution("AxAxAxAoBoBoB"));
        // I AM
        System.out.println(solution("aIaAM"));
        // AAA B A BBBB C BBBB C BB GG G G G RRRRRR
        System.out.println(solution("AAAaBaAbBBBBbCcBdBdBdBcCeBfBeGgGGjGjGRvRvRvRvRvR"));
        // invalid
        System.out.println(solution("aaA"));
        // invalid
        System.out.println(solution("Aaa"));
        // invalid
        System.out.println(solution("HaEaLaLaOWaOaRaLaD"));
        // HELLOWORLD
        System.out.println(solution("aHELLOWORLDa"));
        // HELL O WORLD
        System.out.println(solution("HaEaLaLObWORLDb"));
        // HELLO WORLD
        System.out.println(solution("HaEaLaLaObWORLDb"));
        // HELLO WORLD
        System.out.println(solution("aHbEbLbLbOacWdOdRdLdDc"));
        // invalid
        System.out.println(solution("HELLO WORLD"));
        // invalid
        System.out.println(solution("xAaAbAaAx"));
        // invalid
        System.out.println(solution("AbAaAbAaCa"));
        // invalid
        System.out.println(solution("AbAaAbAaC"));
    }
}