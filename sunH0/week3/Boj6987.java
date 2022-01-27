public class Boj6987 {

	static int win[],lose[],draw[]''
    static int arr1[],arr2[];
	static HashMap<String, String> map;
	static boolean game;

    public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cnt=0;
		arr1 = new int[15];		
		arr2 = new int[15];
		for(int i=0;i<5;i++) {
			for(int j=i+1;j<6;j++) {
				arr1[cnt]=i;
				arr2[cnt++]=j;
			}
		}
		for(int i=0;i<4;i++) {
			String s = br.readLine();
			StringTokenizer st = new StringTokenizer(s);
			win = new int[6];		
			lose = new int[6];		
			draw = new int[6];	
			game=false;			
			int w=0,l=0,d=0;
			for(int j=0;j<6;j++) {
				w += win[j] = Integer.parseInt(st.nextToken());
				d += draw[j] = Integer.parseInt(st.nextToken());
				l += lose[j] = Integer.parseInt(st.nextToken());
			}
			if(w+d+l!=30)
				game=false;
			else
				dfs(0);
			if(game) System.out.print(1+" ");
			else System.out.print(0+" ");
		}
	}
	
	static void dfs(int idx) {
		if(game) return;
		if(idx==15) {
			game=true;
			return;
		}
		int a = arr1[idx];
		int b= arr2[idx];

		if(win[a]>0 && lose[b]>0) {
			win[a]--;
			lose[b]--;
			dfs(idx+1);
			win[a]++;
			lose[b]++;
		}

		if(draw[a]>0 && draw[b]>0) {
			draw[a]--;
			draw[b]--;
			dfs(idx+1);
			draw[a]++;
			draw[b]++;
		}

		if(lose[a]>0 && win[b]>0) {
			lose[a]--;
			win[b]--;
			dfs(idx+1);
			lose[a]++;
			win[b]++;
		}
	}
	

}