package 코테.데브코스_2021.문제1;

public class Solution {
	
	public static int solution(int[] d, int m) {
		
		int boxNum = 1;
		int boxCount = 0;
		int boatCount = 0;
		
		for(int i=0; i<d.length; i++) {
			if(d[i] >= boxNum) {
				boxCount += boxNum;
				boxNum *= 2;
			}
			else {
				boxNum = 1;
			}
			if(boxCount >= m) {
				boatCount = i+1;
				break;
			}
		}
				
		if(boxCount < m) {
			return -1;
		}
		else {
			return boatCount;
		}
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] d = {1,3,2,5,4};
		int m = 6;
		System.out.println(solution(d, m));
		
		d = new int[]{2,2,4,3};
		m = 6;
		System.out.println(solution(d, m));
		
		d = new int[]{2,2,4,3};
		m = 8;
		System.out.println(solution(d, m));
	}

}
