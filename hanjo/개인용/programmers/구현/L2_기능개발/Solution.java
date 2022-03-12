package 구현.L2_기능개발;

import java.util.*;

class Solution {
	public static ArrayList<Integer> solution(int[] progresses, int[] speeds) {
		ArrayList<Integer> pro = new ArrayList<>();
		ArrayList<Integer> sp = new ArrayList<>();
		for(int i=0; i<progresses.length; i++)
		{
			pro.add(progresses[i]);
			sp.add(speeds[i]);
		}
		ArrayList<Integer> answer = new ArrayList<>();
		while(pro.size()!=0) {
			for(int i=0; i<pro.size(); i++)
			{
				pro.set(i, pro.get(i) + sp.get(i));
			}
			int cnt=0;
			while(pro.size() != 0) {
				if(pro.get(0)<100)
					break;
				pro.remove(0);
				sp.remove(0);
				cnt++;
			}
			if(cnt != 0) {
				answer.add(cnt);
			}
		}
		return answer;
    }

	public static void main(String[] args) {
		int[] progresses = {95, 90, 100, 99, 80, 99};
		int[] speeds = {1, 1, 1, 1, 1, 1};
		System.out.println(solution(progresses, speeds));

	}
}
