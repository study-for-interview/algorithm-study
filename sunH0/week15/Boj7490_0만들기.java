package 구현;

import java.util.*;

public class Boj7490_0만들기 {
    

    static int target = 0;
	
	static void dfs(int sum,int sign, int num, int now, String c) {
		if(now == target) { 
			
			sum = sum + (num * sign); 
			if(sum == 0)
				System.out.println(c);
			return ;
		}

		dfs(sum, sign, num*10 + (now+1), now+1, c + " "+String.valueOf(now+1));
		dfs(sum + (sign* num), 1, now+1, now+1, c + "+" + String.valueOf(now+1));
		dfs(sum + (sign*num), -1, now+1, now+1, c + "-" + String.valueOf(now+1));

	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		sc.nextLine();
		
		for(int i=0;i<n;i++) {
			target = sc.nextInt();
			String c = "1";
			
			dfs(0,1,1,1,c); 
			
			System.out.println(" ");
		}
		
	}
}

}
