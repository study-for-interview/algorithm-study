public class Boj7573 {

	static int size,length,N,ans;
	static int[][] map;
	static ArrayList<Point> fishs = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st  =new StringTokenizer(br.readLine());

		size = Integer.parseInt(st.nextToken());
		length = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			fishs.add(new Point(x, y));
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 1; k < length/2; k++) {
					search(i,j,k,length/2-k);
				}
			}
		}
		System.out.println(ans);
	}
	
	private static void search(int i, int j, int lx, int ly) {
		int temp=0;
		for (int k = 0; k < N; k++) {
			if(fishs.get(i).x<=fishs.get(k).x && fishs.get(k).x<=fishs.get(i).x+lx&&
					fishs.get(j).y<=fishs.get(k).y && fishs.get(k).y<=fishs.get(j).y+ly) {				
				temp++;
			}
		}
		ans = ans>temp?ans:temp;
	}

	static class Point{
		int x;
		int y;
		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}