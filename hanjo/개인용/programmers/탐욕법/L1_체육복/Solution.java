package 탐욕법.L1_체육복;

class Solution {
    public static int solution(int n, int[] lost, int[] reserve) {
    	for(int i=0; i<lost.length; i++) {
    		for(int j=0; j<reserve.length; j++) {
    			if(lost[i] == reserve[j]) {
    				lost[i] = -1;
    				reserve[j] = -1;
    			}
    		}
    	}
    	for(int i=0; i<lost.length; i++) {
    		for(int j=0; j<reserve.length; j++) {
    			if(lost[i]-1 == reserve[j] || lost[i]+1 == reserve[j]) {
    				lost[i] = -1;
    				reserve[j] = -1;
    			}
    		}
    	}
    	int answer = n;
    	for(int l : lost) {
    		if(l != -1)
    			answer--;
    	}
        return answer;
    }
    
	public static void main(String[] args) {
		int n= 5;	
		int[] lost = {2, 4};
		int[] reserve = {1, 3, 5};
		System.out.println(solution(n, lost, reserve));
		
	}
}
