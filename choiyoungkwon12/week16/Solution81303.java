package Programmers;

import java.util.Stack;

/**
 * https://programmers.co.kr/learn/courses/30/lessons/81303 표 편집
 */
public class Solution81303 {


    public static void main(String[] args) {
        Solution81303 s = new Solution81303();
        String solution = s.solution(8, 2, new String[]{"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z"});
        System.out.println(solution);
    }

    public String solution(int n, int k, String[] cmd) {

        Stack<Integer> stack = new Stack<>();
        int tableSize = n;
        for (String s : cmd) {
            char c = s.charAt(0);
            if (c == 'U') {
                int num = Integer.parseInt(s.substring(2));
                k -= num;
            } else if (c == 'D') {
                int num = Integer.parseInt(s.substring(2));
                k += num;
            } else if (c == 'C') {
                stack.push(k);
                tableSize--;
                if (k == tableSize) {
                    k--;
                }
            } else if (c == 'Z') {
                if (stack.pop() <= k) {
                    k++;
                }
                tableSize++;
            }
        }
        StringBuilder sb = new StringBuilder("O".repeat(n - stack.size()));
        while (!stack.isEmpty()){
            sb.insert(stack.pop(),"X");
        }

        return sb.toString();
    }

}
