package 완전탐색.L1_로또의최고순위와최저순위;

public class Solution {
	
	public static int convertRank(int num) {
		int rank = 6 - num + 1;
		if(rank >= 6) {
			rank = 6;
		}
		return rank;
	}

	public static int[] solution(int[] lottos, int[] win_nums) {

		int matchNum = 0;
		int additionalNum = 0;
		
		
		for(int my_num : lottos) {
			if(my_num == 0) {
				additionalNum ++;
			}
			for(int win_num : win_nums) {
				if(win_num == my_num) {
					matchNum ++;
				}
			}
		}

		int[] answer = {convertRank(matchNum+additionalNum), convertRank(matchNum)};
		return answer;
	}
}