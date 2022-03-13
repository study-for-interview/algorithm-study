package 구현.L1_완주하지못한선수;

import java.util.*;

public class Solution {
	//
	public static String solution(String[] participant, String[] completion)
	{
		String answer = "";
		Arrays.sort(participant);
		Arrays.sort(completion);
		for(int i=0; i<participant.length; i++)
		{
			answer = participant[i];
			if (i == participant.length-1)
				break;
			else if(!participant[i].equals(completion[i]))
				break;
		}
		return answer;
	}
	//
	public static String solution2(String[] participant, String[] completion) {
		String answer = "";
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		for (String p : participant) {
			if (!hm.containsKey(p))
				hm.put(p, 1);
			else {
				int n = hm.get(p) + 1;
				hm.put(p, n);
			}
		}
		for (String c : completion) {
			int n = hm.get(c) - 1;
			hm.put(c, n);
		}
		for (String s : hm.keySet()) {
			if (hm.get(s) != 0)
				answer = s;
		}
		return answer;
	}

	public static void main(String[] args) {
		String[] participant = { "marina", "josipa", "nikola", "vinko", "filipa" };
		String[] completion = { "josipa", "filipa", "marina", "nikola" };
		System.out.println(solution(participant, completion));
	}
}
