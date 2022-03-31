package BOJ;

import java.io.*;
import java.util.*;
 
public class Boj15686_치킨배달 {
	private static int n, m;
	private static int[][] map;
	private static boolean[] visit;
	
	private static int result = Integer.MAX_VALUE;
	
	private static ArrayList<Location> home = new ArrayList();
	private static ArrayList<Location> chicken = new ArrayList();
	
	private static ArrayList<Location> chickenList = new ArrayList();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n+1][n+1];
		
		for (int i=1; i<=n; i++) {
			st = new StringTokenizer(reader.readLine());
			for (int j=1; j<=n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if (map[i][j] == 1) {
					home.add(new Location(i, j));
				} else if (map[i][j] == 2) {
					chicken.add(new Location(i, j));
				}
			}
		}
		
		visit = new boolean[chicken.size()];
		
		dfs(0,0);
		
		System.out.println(result);
		
	}//main
	
	private static void dfs(int start, int count) {
		
		if (count == m) {
			
			int sum = 0;
			for (int i=0; i<home.size(); i++) {
				int min = Integer.MAX_VALUE;
				for (int j=0; j<chickenList.size(); j++) {
					int distance = Math.abs(home.get(i).x - chickenList.get(j).x) + Math.abs(home.get(i).y - chickenList.get(j).y);

					if (min > distance) {
						min = distance;
					}
				}
				sum += min;
			}
			
			if (sum < result) {
				result = sum;
			}
			
		} else {
			
			for (int i=start; i<chicken.size(); i++) {
				if (!visit[i]) {
					visit[i] = true;
					chickenList.add(chicken.get(i));
					dfs(i+1, count+1);
					chickenList.remove(chickenList.size()-1);
					visit[i] = false;
				}
			}
			
		}
		
	}

	static class Location {
		int x, y;

		public Location(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
