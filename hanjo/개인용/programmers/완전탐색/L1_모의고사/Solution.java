// package 완전탐색.L1_모의고사;

// import java.util.*;

// public class Solution {
// 	static public ArrayList<Integer> solution(int[] answers) {
//         int[] pattern1 = {1,2,3,4,5};
//         int[] pattern2 = {2, 1, 2, 3, 2, 4, 2, 5};
//         int[] pattern3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
//         int[][] p_list = {pattern1, pattern2, pattern3};
//         Map<Integer, Integer> num = new HashMap<Integer, Integer>();

//         int p_ind = 1;
//         for(int[] p : p_list)
//         {
//         	int cnt=0;
//         	for(int i=0; i<answers.length; i++)
//         	{
//         		if(p[i % p.length] == answers[i])
//         			cnt++;
//         	}
//         	num.put(p_ind, cnt);
//         	p_ind++;
//         }
        
//         int max_v = num.entrySet().stream()
//         		.max(
//         				(left, right) ->{
//         					if(left.getValue() > right.getValue())
//         						return 1;
//         					else if(left.getValue() == right.getValue())
//         						return 0;
//         					else
//         						return -1;
//         				}
//         				).get().getValue();
        
//         List<Integer> answer = new ArrayList<Integer>();
//         for(Map.Entry<Integer, Integer> e : num.entrySet())
//         {
//         	if(e.getValue() == max_v)
//         		answer.add(e.getKey());
//         }
//         return answer;
//     }

// 	public static void main(String[] args) {
// 		int[] answers = {1,2,3,4,5};
// 		int[] answers2 = {1,3,2,4,2};
// 		System.out.println(solution(answers));
// 		System.out.println(solution(answers2));
// 	}
// }
