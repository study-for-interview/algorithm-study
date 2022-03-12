package 구현.L2_더맵게;

import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
       
        PriorityQueue<Integer> pq = new PriorityQueue<>();
		for(int s : scoville)
			pq.add(s);
        
        while(true) {
        	if(pq.peek() >= K)
        		break;
    		if(pq.size() == 1)
    		{
    			answer = -1;
    			break;
    		}
    		pq.add(pq.poll()+2*pq.poll());
    		answer++;
    	}
        return answer;
    }

	public static void main(String[] args) {
	}
}
