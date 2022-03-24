package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj1759_암호만들기 {

	static boolean[] visit;
	static String[] pws;
	static int l, c;
    static String ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		l = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		pws = br.readLine().split("\\s");

	
		visit = new boolean[c];
		Arrays.sort(pws);
		 
		dfs(0, 0);
	}

	private static void dfs(int start, int depth) {

		if(depth == l) {
            StringBuilder sb = new StringBuilder();
			int vow = 0, 
			cons = 0; 

			for(int i = 0; i < c; i++) {
				if(visit[i]) {
					ans += pws[i];

					if(pws[i].equals("a") || pws[i].equals("e") || pws[i].equals("i") || pws[i].equals("o") || pws[i].equals("u")) vow++;
					else cons++;
				}
			}

			if(vow >= 1 && cons >= 2) System.out.println(ans);
			return;
		}
		
		for(int i = start; i < c; i++) {
			visit[i] = true;
			dfs(i + 1, depth + 1);
			visit[i] = false;
		}	
	}

}