package 구현.L2_프린터;

import java.util.*;

public class Solution {
	public static int solution(int[] priorities, int location) {
		
		// 배열 -> arraylist
		List<int[]> list = new ArrayList<>();
		// 우선순위를 내림차순으로 정렬
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		// 리스트와 큐 초기화
		for(int i=0; i<priorities.length; i++) {
			list.add(new int[]{i, priorities[i]});
			pq.add(priorities[i]);
		}
		
		int count = 0;
		while(pq.size() > 0) {
			count ++;
			int highestPriority = pq.poll();
			while(true) {
				int[] temp = list.remove(0);
				int tempLocation = temp[0];
				int tempPriority = temp[1];
				
				if(tempPriority == highestPriority) {
					if(tempLocation == location) {
						return count;
					}
					break;
				}
				else {
					list.add(new int[]{tempLocation, tempPriority});
				}
			}
			
		}
		return count;
    }

	public static void main(String[] args) {
		int[] priorities = {2, 1, 3, 2};
		int location = 2;
		System.out.println(solution(priorities, location));
	}
}