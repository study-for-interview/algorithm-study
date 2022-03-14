package 코테.데브코스_2021.문제2;

import java.util.*;

public class Solution {
	
	public static int[] solution(int[] deposit) {
		
		Stack<Integer> log = new Stack<>();
		
		for(int money : deposit) {
			if(money < 0) {
				int diff = money;
				while(diff < 0) {
					// 차액 구하기
					diff += log.pop();
					// 차액이 양수라면 그걸 스택에 넣어줌
					if(diff > 0) {
						log.push(diff);
					}
 				}
			}
			else {
				log.push(money);
			}
		}
		
		int[] answer = new int[log.size()];
		for(int i=0; i<log.size(); i++) {
			answer[i] = log.get(i);
		}
		
        return answer;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] deposit = {500, 1000, -300, 200, -400, 100, -100};
		System.out.println(Arrays.toString(solution(deposit)));
		deposit = new int[]{500, 1000, 2000, -1000, -1500, 500};
		System.out.println(Arrays.toString(solution(deposit)));
	}

}
