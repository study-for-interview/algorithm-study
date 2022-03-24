package 코테.쏘카_2022_1.문제1;

import java.util.*;

public class Solution {

    public static int solution(String s){
        Stack<Character> stack = new Stack<>();
        char[] brackets = s.toCharArray();
        for(char bracket : brackets){
            if(!stack.isEmpty() && isMatch(stack.peek(),bracket)){
                stack.pop();
            }
            else{
                stack.add(bracket);
            }
        }
        System.out.println(stack);
        
        return 0;
    }

    public static boolean isMatch(char left, char right){
        if(left == '(' && right == ')'){
            return true;
        }
        if(left == '[' && right == ']'){
            return true;
        }
        if(left == '{' && right == '}'){
            return true;
        }
        return false;
    }

    public static void main(String args[]) {
        // 3
        System.out.println(solution("[]([[]){}"));
        // 4
        System.out.println(solution("{([()]))}"));
        // 7
        System.out.println(solution("(()()()"));
    }
}
