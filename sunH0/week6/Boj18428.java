import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj18428 {

	static char[][] map;
	static int N;
	static ArrayList<Point> list = new ArrayList<>();

	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		StringTokenizer st;

		map = new char[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = st.nextToken().charAt(0);

				if (map[i][j] == 'T') {
					list.add(new Point(i, j));
				}
			}
		}
		
		dfs(0,0,0);
		System.out.println("NO");

	}
	
	public static void dfs(int cnt, int x, int y) {
		if(cnt==3) {
			if(isAvailable()) {
				System.out.println("YES");
				System.exit(0);
			}
			return;
		}
		for(int i=x;i<N;i++) {
			for(int j=y;j<N;j++) {
				if(map[i][j]=='X') {
					map[i][j]='O';
					dfs(cnt+1,i,j+1);
					map[i][j]='X';
				}
			}
			y=0;
		}
	}
	public static void print() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		
		System.out.println();
	}
	public static boolean isAvailable() {
		for (int i = 0; i < list.size(); i++) {
			Point tc = list.get(i);
			for(int d=0;d<di.length;d++) {
				int ni = tc.x;
				int nj = tc.y;
				
				while(true) {
					ni = ni + di[d];
					nj = nj + dj[d];
					
					if(ni<0||nj<0||ni>=N||nj>=N) break;
					
					if(map[ni][nj]=='O') break;
					
					if(map[ni][nj]=='S') return false;
					
				}
			}
		}
		
		return true;
	}

    static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}
}