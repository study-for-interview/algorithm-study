package 완전탐색.L2_카펫;

import java.util.*;

public class Solution {
	public static int[] solution(int brown, int yellow) {
		int[] answer = new int[2];
		
		int gridNum = brown + yellow;
		int firstX = (int)Math.ceil(Math.sqrt(gridNum));
		
		for(int x=firstX; x<=gridNum; x++) {
			if(gridNum%x==0) {
				int y = gridNum/x;
				if((x+(y-2))*2 == brown) {
					answer[0] = x;
					answer[1] = y;
					break;
				}
			}
		}
		return answer;
    }

	public static void main(String[] args) {
		int brown = 10;
		int yellow = 2;
		System.out.println(Arrays.toString(solution(brown, yellow)));
	}
}
