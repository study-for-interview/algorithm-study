import java.io.BufferedReader;
import java.io.InputStreamReader;

class Boj16472_고냥이 {
	public static void main(String[] args)throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		String str = br.readLine();
		
		int max=0, len = 0;
		int[] cnt = new int[26];
		int kind=0, fst=0, snd=0;
		
		while(fst<=snd && snd<str.length()) {

			while(snd<str.length() && kind<=N) {
				int cur = str.charAt(snd++)-'a';
                
				if(cnt[cur]==0)kind++;
				cnt[cur]++;

				len++;
				if(kind<=N)max=Math.max(max, len);
			}
			
			while(fst<=snd && kind>N) {
				int cur = str.charAt(fst++)-'a';
				cnt[cur]--;
				if(cnt[cur]==0)kind--;
				len--;
			}
			
		}
		System.out.println(max);
	}

	
}