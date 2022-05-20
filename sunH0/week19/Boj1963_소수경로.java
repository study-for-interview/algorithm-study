package 소수;

import java.io.*;
import java.util.*;

public class Boj1963_소수경로 {

    static boolean[] isPrime = new boolean[100001];

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
        int t = Integer.parseInt(br.readLine());
        checkPrime();

        for(int i=0; i<t; i++) {
			st = new StringTokenizer(br.readLine());
			int now = Integer.parseInt(st.nextToken());
			int target = Integer.parseInt(st.nextToken());
			
            int ans = bfs(now,target);
            if(ans==-1) System.out.println("Impossible");
			else System.out.println(ans);
		}

    }

    static int bfs(int now, int target){
        Queue<Prime> q = new LinkedList<>();
        Set<Integer> set = new HashSet<>();

        set.add(now);
		q.add(new Prime(now, 0));

        while(!q.isEmpty()) {
            Prime prime = q.poll();

            int[] pNum = {prime.num/1000, (prime.num/100)%10, (prime.num/10)%10, prime.num%10}; 

            if(prime.num == target) {
				return prime.cnt;
			}

            for(int i=0; i<4; i++) {
				for(int j=0; j<10; j++) {
                    if(i==0 && j==0) continue;
                    
                    int tmp = pNum[i];
					pNum[i] = j;
                    int next = changePassword(pNum);
                    pNum[i] = tmp;

                    if(isPrime[next]) continue;		
                    if(!set.contains(next)) {
						q.add(new Prime(next, prime.cnt+1));
                        set.add(next);
					}			
				}
			}

        }

        return -1;
    }

    static void checkPrime() {
		for(int i=2; i<10000; i++) {
			if(!isPrime[i]) {
				for(int j=i*i; j<10000; j+=i) {
					isPrime[j] = true;
				}	
			}
			
		}
	}

    static int changePassword(int[] pNum) {
		int num =0;
		for(int i=0; i<4; i++) {
			num += pNum[i]*(Math.pow(10, 3-i));
		}
		return num;
		
	}

    static class Prime{
        int num;
        int cnt;

        public Prime(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }

        
    }

    
}
